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
symbol_info = pd.DataFrame(symbol_info)
exchange_info = pd.read_json("C:/0.bigdata/4.web/Triple_Core/0.DataRepo/0.LocalStorage/exchange_info.json", encoding="UTF-8")

    
def getStockData(symbol_info):
    startTime = time.time()
    
    columns = ['Sector', 'Exchange', 'Symbol', 'Name', 'Date', 'Currency', 'Open', 'High', 'Low', 'Close', 'Volume']
    param = {}
    param['q'] = ""
    param['x'] = ""
    param['i'] = str(60*60*24)
    param['p'] = "1Y"
    param['c'] = ""
    
    stocks = pd.DataFrame()
    stock = pd.DataFrame()
    
    for row in symbol_info.values:
        try:
            exchange_name = row[2]
            symbol_adj = int(exchange_info[exchange_name][5])
            quote_symbol = str(row[1]).rjust(symbol_adj, "0")
            currency = exchange_info[exchange_name][6]
            
            param['q'] = quote_symbol
            param['x'] = exchange_info[exchange_name][3] ## google
    #        param['x'] = exchange_info[exchange_name][4] ## yahoo
            param['c'] = currency
            
            print(param)
            
                
            stock = get_price_data(param)
            if not stock.empty:
                stock['Name'] = row[0]
                stock['Sector'] = row[3]
                stock['Symbol'] = quote_symbol
                stock['Exchange'] = exchange_name
                stock['Currency'] = currency
                
                stock = pd.DataFrame(stock, columns = columns)
                stocks = stocks.append(stock)
                print("Google] " + quote_symbol + " success")
    
            elif stock.empty:
                quote_symbol = quote_symbol + exchange_info[exchange_name][4]
                print(quote_symbol)
                
                stock = getStockDataYahoo(quote_symbol)
                
                if not stock.empty:
                    stock['Name'] = row[0]
                    stock['Sector'] = row[3]
                    stock['Symbol'] = quote_symbol
                    stock['Exchange'] = exchange_name
                    stock['Currency'] = currency
                    
                    stock = pd.DataFrame(stock, columns = columns)
                    
                    stocks = stocks.append(stock)
                    print("Yahoo] " + quote_symbol + " success")
                
                else:
                    print("Both] " + quote_symbol + " fail")
                
        except:
            print("No Data " + quote_symbol)
            pass
        
    stocks.to_csv("C:/0.bigdata/4.web/Triple_Core/0.DataRepo/0.TestData_Papa/test_sample01.csv" , index=False)
            
    endTime = time.time() - startTime
    print('실행에 소용된 시간=', endTime, '초')
    if endTime / 60 > 1:
        print( endTime / 60, '분')
        

getStockData(symbol_info)
