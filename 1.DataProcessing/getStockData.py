# -*- coding: utf-8 -*-
"""
Created on Wed Apr  4 13:56:23 2018

@author: user
"""

import pandas as pd
import numpy as np
import time

from modules.googleFinance import get_price_data
from modules.yahooFinance import getStockDataYahoo


aggregated_raw_data_path = "C:/0.bigdata/4.web/Triple_Core/0.DataSet/0.RawData/[AggregatedRawData]"
initial_date = "20180406"
initial_raw_data = "[initial]raw_data_utf8.csv"

update_date = "20180409"
update_raw_data = "[" + update_date + "]" + "raw_data_utf8.csv"

processed_data_path = "C:/0.bigdata/4.web/Triple_Core/0.DataSet/1.ProcessedData"
initial_validated_data = "[initial]validated_data_utf8.csv"
old_validated_data = "old_validated_data_utf8.csv"

exchange_info = pd.read_json("C:/0.bigdata/4.web/Triple_Core/0.DataSet/1.ProcessedData/exchange_info.json", encoding="UTF-8")

## data_info => [ "Name", "Symbol", "Exchange", "Sector", "Industry", "Listing_status", "Delisting_date", "New_listing_date" ]
## exchange_info => [ "Nation","Acronym","Name","Google","Yahoo","Symbol_adj","Currency","WebSite" ]

    
def getStockData(data_info):
    startTime = time.time()
    
    stock_columns = ["Sector", "Industry", "Exchange", "Symbol", "Name", "Date", "Currency", "Open", "High", "Low", "Close", "Rtn", "Volume"]
    error_columns = [ "Name", "Symbol", "Exchange", "Sector", "Industry", "Listing_status", "Delisting_date", "New_listing_date" ]
    
    param = {}
    param["q"] = ""
    param["x"] = ""
    param["i"] = str(60*60*24)
    param["p"] = "2Y"
    param["c"] = ""
    
    stock = pd.DataFrame()
    stock_total = pd.DataFrame()
    
    error_log = pd.DataFrame()
    
     # not existed on google/yahoo 	- 베트남(Ho Chi Minh,하노이), 필리핀
	  # not matched with symbol 		- 인도(NSE,BSE), 싱가포르
    
    available_exchange_info = { key : [ exchange_info[key][3:] ]  for key in exchange_info.keys() if exchange_info[key][8] == "O" }
    count = 0
    for row in data_info.values:
        count += 1
        try:
            
            exchange_name = row[2]
            if exchange_name in available_exchange_info.keys():
                
                Listing_status = row[-3]
                if Listing_status == "O" or Listing_status == "New":
                    
                    symbol_adj = int(exchange_info[exchange_name][5])
                    quote_symbol = str(row[1]).rjust(symbol_adj, "0")
                    currency = exchange_info[exchange_name][6]
                    
                    param["q"] = quote_symbol
                    param["x"] = exchange_info[exchange_name][3] ## google
        #            param["x"] = exchange_info[exchange_name][4] ## yahoo
                    param["c"] = currency
                    
                    stock = get_price_data(param)
                    #print(stock)
                    if not stock.empty:
                        stock["Name"] = row[0]
                        stock["Sector"] = row[3]
                        stock["Industry"] = row[4]
                        stock["Symbol"] = quote_symbol
                        stock["Exchange"] = exchange_name
                        stock["Currency"] = currency
                        stock["Rtn"] = np.log(stock["Close"]) - np.log(stock["Close"].shift(1))
                        stock = stock.dropna()
                        
                        #stock = pd.DataFrame(stock, columns = stock_columns)
                        stock_total = stock_total.append(stock)
                        print("Google] " + quote_symbol + " success")
            
                    elif stock.empty:
                        quote_symbol = quote_symbol + exchange_info[exchange_name][4]
                        print(quote_symbol)
                        
                        stock = getStockDataYahoo(quote_symbol)
                        #print(stock)
                        
                        if not stock.empty:
                            stock["Name"] = row[0]
                            stock["Sector"] = row[3]
                            stock["Industry"] = row[4]
                            stock["Symbol"] = quote_symbol
                            stock["Exchange"] = exchange_name
                            stock["Currency"] = currency
                            stock["Rtn"] = np.log(stock["Close"]) - np.log(stock["Close"].shift(1))
                            stock = stock.dropna()
                            
                            #stock = pd.DataFrame(stock, columns = stock_columns)
                            
                            stock_total = stock_total.append(stock)
                            print("Yahoo] " + quote_symbol + " success")
                        
                        else:
                            print("Both] " + quote_symbol + " fail")
                            row = pd.DataFrame(row, index = error_columns)
                            error_log = error_log.append(row.T)
                else:
#                    print("Exchange not available] " + exchange_name)
                    row = pd.DataFrame(row, index = error_columns)
                    error_log = error_log.append(row.T)

        except:
            print("Exchange not registered] " + exchange_name)
            row = pd.DataFrame(row, index = error_columns)
            error_log = error_log.append(row.T)
        
        if count == 9000:
            print(count)
            stock_total = stock_total.dropna()    
            stock_total = pd.DataFrame(stock_total, columns = stock_columns)
            stock_total.to_csv("C:/0.bigdata/4.web/Triple_Core/0.DataRepo/0.TestData_Papa/test_stock01.csv", index=False, encoding="UTF-8")
            error_log = pd.DataFrame(error_log, columns = error_columns)
            error_log.to_csv("C:/0.bigdata/4.web/Triple_Core/0.DataRepo/0.TestData_Papa/test_error_log01.csv", index=False, encoding="UTF-8")
            continue
        
        elif count == 18000:
            print(count)
            stock_total = stock_total.dropna()    
            stock_total = pd.DataFrame(stock_total, columns = stock_columns)
            stock_total.to_csv("C:/0.bigdata/4.web/Triple_Core/0.DataRepo/0.TestData_Papa/test_stock02.csv", index=False, encoding="UTF-8")
            error_log = pd.DataFrame(error_log, columns = error_columns)
            error_log.to_csv("C:/0.bigdata/4.web/Triple_Core/0.DataRepo/0.TestData_Papa/test_error_log02.csv", index=False, encoding="UTF-8")
            continue
            
        elif count == len(data_info.values):
            print(count)
            stock_total = stock_total.dropna()    
            stock_total = pd.DataFrame(stock_total, columns = stock_columns)
            stock_total.to_csv("C:/0.bigdata/4.web/Triple_Core/0.DataRepo/0.TestData_Papa/test_stock03.csv", index=False, encoding="UTF-8")
            error_log = pd.DataFrame(error_log, columns = error_columns)
            error_log.to_csv("C:/0.bigdata/4.web/Triple_Core/0.DataRepo/0.TestData_Papa/test_error_log03.csv", index=False, encoding="UTF-8")
            break
        
        else:
            continue
        
    endTime = time.time() - startTime
    print("실행에 소용된 시간=", endTime, "초")
    if endTime / 60 > 1:
        print( endTime / 60, "분")
        


#symbol_info = pd.read_csv("C:/0.bigData/4.web/Triple_Core/0.DataRepo/0.LocalStorage/symbol_info_utf8_sample.csv", encoding="UTF-8")
processed_data_info = pd.read_csv("C:/0.bigdata/4.web/Triple_Core/0.DataSet/1.ProcessedData/old_validated_data_utf8.csv", encoding="UTF-8")
processed_data_info_df = pd.DataFrame(processed_data_info)


getStockData(processed_data_info_df)
