# -*- coding: utf-8 -*-

import pandas as pd
import numpy as np
import time

from modules.googleFinance import get_price_data
from modules.yahooFinance import getStockDataYahoo

all_param = {
            'KOR': {'i': "86400", 'x': "KRX", 'p': "1Y", 'c': "KRW"},
            'JPN': {'i': "86400", 'x': "TYO", 'p': "1Y", 'c': "JPY"}
            }

k_util = pd.read_csv("C:/0.bigdata/4.web/Triple_Core/0.DataRepo/0.TestData_Papa/k_util.csv")
k_util = pd.DataFrame(k_util)
k_util.name = "KOR"

j_util = pd.read_csv("C:/0.bigdata/4.web/Triple_Core/0.DataRepo/0.TestData_Papa/j_util.csv")
j_util.name = "JPN"


#def addZeroSymbol(symbols):
#    adj_symbols = []
#    for symbol in symbols:
#        adj_symbol = str(symbol).rjust(6, "0")
#        adj_symbols.append(adj_symbol)
#    return adj_symbols


def getStockData(nation, data):
    startTime = time.time()
    param = all_param[nation]
    print(param)
    columns = ['Exchange', 'Symbol', 'Name', 'Date', 'Currency', 'Open', 'High', 'Low', 'Close', 'Volume']
    
    for d in data.values:
        symbol = str(d[1])
        
        if data.name == "KOR":
            symbol = symbol.rjust(6, "0")
            #print(d[2])
            if d[2] == "서울":
                param['x'] = "KRX"
            elif d[2] == "KOSDAQ":
                param['x'] = "KOSDAQ"
            else:
                param['x'] = "KONEX"
                
        param['q'] = str(symbol)
        #print(param)
        
        stock = get_price_data(param)

        if not stock.empty:
            stock['Name'] = d[0]
            stock['Symbol'] = str(symbol)
            stock['Exchange'] = d[2]
            stock['Currency'] = param['c']
            
            stock = pd.DataFrame(stock, columns = columns)
            stock.to_csv("C:/0.bigdata/4.web/Triple_Core/0.DataRepo/0.TestData_Papa/" + param['q'] + "." + param['x'], index=False)
            print("success")
        else:
            print("fail")
           
    endTime = time.time() - startTime
    print('실행에 소용된 시간=', endTime, '초')
    if endTime / 60 > 1:
        print( endTime / 60, '분')
        

getStockData("KOR", k_util)
#getStockData("JPN", j_util)

test_param = {
		'q': "NTPC",
		'i': "86400",
		'x': "NSE",
		'p': "1Y"
      	}

test = get_price_data(test_param)
col = test.columns


