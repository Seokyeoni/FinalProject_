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



symbol_info = pd.read_csv("C:/0.bigData/4.web/Triple_Core/0.DataRepo/0.LocalStorage/symbol_info_utf8_sample.csv", encoding="UTF-8")
#symbol_info = pd.read_csv("C:/0.bigData/4.web/Triple_Core/0.DataRepo/0.LocalStorage/symbol_info_utf8.csv", encoding="UTF-8")
symbol_info = pd.DataFrame(symbol_info)
exchange_info = pd.read_json("C:/0.bigdata/4.web/Triple_Core/0.DataRepo/0.LocalStorage/exchange_info.json", encoding="UTF-8")



## symbol_info => ['Name', 'Symbol', 'Exchange', 'Sector', 'Industry']
## exchange_info => ['Nation', 'Acronym', 'Name', 'Google', 'Yahoo', 'Symbol_adj','Currency', 'WebSite']



    
def getStockData(symbol_info):
    startTime = time.time()
    
    stock_columns = ['Sector', 'Industry', 'Exchange', 'Symbol', 'Name', 'Date', 'Currency', 'Open', 'High', 'Low', 'Close', 'Rtn', 'Volume']
    error_columns = ['Name', 'Symbol', 'Exchange', 'Sector', 'Industry']
    
    param = {}
    param['q'] = ""
    param['x'] = ""
    param['i'] = str(60*60*24)
    param['p'] = "1Y"
    param['c'] = ""
    
    stock = pd.DataFrame()
    stocks = pd.DataFrame()
    
    error_log = pd.DataFrame()
    
    for row in symbol_info.values:
        #print(row)
        try:
            exchange_name = row[2]
            symbol_adj = int(exchange_info[exchange_name][5])
            quote_symbol = str(row[1]).rjust(symbol_adj, "0")
            currency = exchange_info[exchange_name][6]
            
            param['q'] = quote_symbol
            param['x'] = exchange_info[exchange_name][3] ## google
#            param['x'] = exchange_info[exchange_name][4] ## yahoo
            param['c'] = currency
            
#            print(param)
            
                
            stock = get_price_data(param)
            #print(stock)
            if not stock.empty:
                stock['Name'] = row[0]
                stock['Sector'] = row[3]
                stock['Industry'] = row[4]
                stock['Symbol'] = quote_symbol
                stock['Exchange'] = exchange_name
                stock['Currency'] = currency
                stock['Rtn'] = np.log(stock['Close']) - np.log(stock['Close'].shift(1))
                stock = stock.dropna()
                
                #stock = pd.DataFrame(stock, columns = stock_columns)
                stocks = stocks.append(stock)
                print("Google] " + quote_symbol + " success")
    
            elif stock.empty:
                quote_symbol = quote_symbol + exchange_info[exchange_name][4]
#                print(quote_symbol)
                
                stock = getStockDataYahoo(quote_symbol)
                #print(stock)
                
                if not stock.empty:
                    stock['Name'] = row[0]
                    stock['Sector'] = row[3]
                    stock['Industry'] = row[4]
                    stock['Symbol'] = quote_symbol
                    stock['Exchange'] = exchange_name
                    stock['Currency'] = currency
                    stock['Rtn'] = np.log(stock['Close']) - np.log(stock['Close'].shift(1))
                    stock = stock.dropna()
                    
                    #stock = pd.DataFrame(stock, columns = stock_columns)
                    
                    stocks = stocks.append(stock)
                    print("Yahoo] " + quote_symbol + " success")
                
                else:
                    print("Both] " + quote_symbol + " fail")
                    row = pd.DataFrame(row, index = error_columns)
                    error_log = error_log.append(row.T)
                
        except:
            print("Exchange not registered] " + exchange_name)
            row = pd.DataFrame(row, index = error_columns)
            error_log = error_log.append(row.T)
            pass
        
    stocks = stocks.dropna()    
    stocks = pd.DataFrame(stocks, columns = stock_columns)
    stocks.to_csv("C:/0.bigdata/4.web/Triple_Core/0.DataRepo/0.TestData_Papa/test_stocks01.csv", index=False, encoding="UTF-8")
    error_log = pd.DataFrame(error_log, columns = error_columns)
    error_log.to_csv("C:/0.bigdata/4.web/Triple_Core/0.DataRepo/0.TestData_Papa/test_error_log01.csv", index=False, encoding="UTF-8")
        
    endTime = time.time() - startTime
    print('실행에 소용된 시간=', endTime, '초')
    if endTime / 60 > 1:
        print( endTime / 60, '분')
        

getStockData(symbol_info)
