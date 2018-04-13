# -*- coding: utf-8 -*-
"""
Created on Wed Apr  4 13:56:23 2018

@author: user
"""

import os

import pandas as pd
import numpy as np
import time

from modules.googleFinance import get_price_data
from modules.yahooFinance import getStockDataYahoo



processed_data_path = "C:/0.bigdata/4.web/Triple_Core/0.DataSet/1.ProcessedData"
result_data_path = "C:/0.bigdata/0.data/Triple_Core/1.DataProcessing/result"

old_validated_data = "old_validated_data_utf8.csv"
processed_data_info = pd.read_csv("C:/0.bigdata/4.web/Triple_Core/0.DataSet/1.ProcessedData/old_validated_data_utf8.csv", encoding="UTF-8")
processed_data_info_df = pd.DataFrame(processed_data_info)

old_validated_data_by_sector_path = processed_data_path + "/by_sector"
old_validated_data_list = os.listdir(old_validated_data_by_sector_path)


exchange_info = pd.read_json("C:/0.bigdata/4.web/Triple_Core/0.DataSet/1.ProcessedData/exchange_info.json", encoding="UTF-8")


## data_info => [ "Name", "Symbol", "Exchange", "Code", "Sector", "Industry", "Listing_status", "Delisting_date", "New_listing_date" ]
## exchange_info => [ "Nation","Acronym","Name","Google","Yahoo","Symbol_adj","Currency","WebSite", "Available" ]


def getStockDataBySector(data_info_list):
    for csv_file in data_info_list:
        dir_name = csv_file.split("_")[3] # s01
        if not os.path.isdir(result_data_path + "/" + dir_name):
            os.mkdir(result_data_path + "/" + dir_name)
        if not os.path.isfile(result_data_path + "/" + dir_name + "/" + "test_stock.csv"):
            csv_df = pd.read_csv(old_validated_data_by_sector_path + "/" + csv_file, encoding="UTF-8")
            csv_df.columns = ["Name", "Symbol", "Exchange", "Code", "Sector", "Industry", "Listing_status", "Delisting_date", "New_listing_date"]
            getStockData(csv_df, dir_name)
        

    
def getStockData(data_info, dir_name):
    startTime = time.time()
    
    stock_columns = [ "Name", "Symbol", "Exchange", "Code", "Sector", "Industry", "Date", "Currency", "Open", "High", "Low", "Close", "Rtn", "Volume" ]
    error_columns = [ "Name", "Symbol", "Exchange", "Code", "Sector", "Industry", "Listing_status", "Delisting_date", "New_listing_date" ]
    
    param = {}
    param["q"] = ""
    param["x"] = ""
    param["i"] = str(60*60*24)
    param["p"] = "1Y"
    param["c"] = ""
    
    stock = pd.DataFrame()
    stock_total = pd.DataFrame()
    
    error_log = pd.DataFrame()
    
     # not existed on google/yahoo     - 베트남(Ho Chi Minh,하노이), 필리핀
      # not matched with symbol         - 인도(NSE,BSE), 싱가포르
    
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
#                    param["x"] = exchange_info[exchange_name][4] ## yahoo
                    param["c"] = currency
                    
                    stock, google_empty = get_price_data(param)
#                    print(stock)                    
                    
                    if not stock.empty:
                        stock["Name"] = row[0]
                        stock["Symbol"] = quote_symbol
                        stock["Exchange"] = exchange_name
                        stock["Code"] = row[3]
                        stock["Sector"] = row[4]
                        stock["Industry"] = row[5]
                        stock["Currency"] = currency
                        stock["Rtn"] = np.log(stock["Close"]) - np.log(stock["Close"].shift(1))
                        stock.loc[ (np.isinf(stock["Rtn"]) ) | (np.isnan(stock["Rtn"])), "Rtn"] = None
                        
#                        stock.loc[ (stock["Rtn"] == np.inf)  | (stock["Rtn"] == -(np.inf)), "Rtn"] = None
                        
                        stock = stock.dropna()
                        
                        #stock = pd.DataFrame(stock, columns = stock_columns)
                        stock_total = stock_total.append(stock)
                        print("Google] " + quote_symbol + " success")
            
                    elif stock.empty and google_empty:
                        quote_symbol = quote_symbol + exchange_info[exchange_name][4] ## yahoo
#                        print(quote_symbol)
                        
                        stock = getStockDataYahoo(quote_symbol)
#                        print(stock)
                        
                        if not stock.empty:
                            stock["Name"] = row[0]
                            stock["Symbol"] = quote_symbol
                            stock["Exchange"] = exchange_name
                            stock["Code"] = row[3]
                            stock["Sector"] = row[4]
                            stock["Industry"] = row[5]
                            stock["Currency"] = currency
                            stock["Rtn"] = np.log(stock["Close"]) - np.log(stock["Close"].shift(1))
                            stock.loc[ (np.isinf(stock["Rtn"]) ) | (np.isnan(stock["Rtn"])), "Rtn"] = None
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
        
        if count == 1:
            print(count)
            stock_total = stock_total.dropna()    
            stock_total = pd.DataFrame(stock_total, columns = stock_columns)
            stock_total.to_csv(result_data_path + "/" + dir_name + "/" + "test_stock01.csv", index=False, encoding="UTF-8")
            error_log = pd.DataFrame(error_log, columns = error_columns)
            error_log.to_csv(result_data_path + "/" + dir_name + "/" + "test_error_log01.csv", index=False, encoding="UTF-8")
            continue
        
        elif count == 1000:
            print(count)
            stock_total = stock_total.dropna()    
            stock_total = pd.DataFrame(stock_total, columns = stock_columns)
            stock_total.to_csv(result_data_path + "/" + dir_name + "/" + "test_stock02.csv", index=False, encoding="UTF-8")
            error_log = pd.DataFrame(error_log, columns = error_columns)
            error_log.to_csv(result_data_path + "/" + dir_name + "/" + "test_error_log02.csv", index=False, encoding="UTF-8")
            continue
        
        elif count == 2000:
            print(count)
            stock_total = stock_total.dropna()    
            stock_total = pd.DataFrame(stock_total, columns = stock_columns)
            stock_total.to_csv(result_data_path + "/" + dir_name + "/" + "test_stock03.csv", index=False, encoding="UTF-8")
            error_log = pd.DataFrame(error_log, columns = error_columns)
            error_log.to_csv(result_data_path + "/" + dir_name + "/" + "test_error_log03.csv", index=False, encoding="UTF-8")
            continue
            
        elif count == len(data_info.values):
            print(count)
            stock_total = stock_total.dropna()    
            stock_total = pd.DataFrame(stock_total, columns = stock_columns)
            stock_total.to_csv(result_data_path + "/" + dir_name + "/" + "test_stock.csv", index=False, encoding="UTF-8")
            error_log = pd.DataFrame(error_log, columns = error_columns)
            error_log.to_csv(result_data_path + "/" + dir_name + "/" + "test_error_log.csv", index=False, encoding="UTF-8")
            break
        
        else:
            continue
        
    endTime = time.time() - startTime
    print("실행에 소용된 시간=", endTime, "초")
    if endTime / 3600 > 1:
        print( endTime / 3600, "시간")
    else:
        print( endTime / 60, "분")
        


#symbol_info = pd.read_csv("C:/0.bigData/4.web/Triple_Core/0.DataRepo/0.LocalStorage/symbol_info_utf8_sample.csv", encoding="UTF-8")



#getStockData(processed_data_info_df)
getStockDataBySector(old_validated_data_list)
