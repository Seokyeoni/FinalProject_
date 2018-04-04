# -*- coding: utf-8 -*-
"""
Created on Wed Apr  4 13:56:23 2018

@author: user
"""

import pandas as pd
import numpy as np
import time

from modules.pjong_googleFinance import get_price_data
from modules.pjong_yahooFinance import getStockDataYahoo



sample = pd.read_csv("C:/0.bigData/4.web/Triple_Core/0.DataRepo/0.LocalStorage/symbol_info.csv")
sample = pd.DataFrame(sample)
exchange_info = pd.read_csv("C:/0.bigdata/4.web/Triple_Core/0.DataRepo/0.LocalStorage/exchange_info.csv")

#print(exchange_info['Exchange'] == '상하이')

def set_param(q,x,c):
    param = {'q': q,'i': "86400",'x': x,'p': "2Y",'c': c}
    return param

def getSHA(symbol):
    param = {'i': "86400", 'x': "SHA", 'p': "1Y", 'c': "CNY"}
    param['q'] = symbol.rjust(6, "0")
    stock = get_price_data(param)
    if stock.empty:
        stock = getStockDataYahoo(param['q'])
    return param, stock


def getStock(symbol) :
    
    param = {'i': "86400", 'p': "1Y"}
    exchange = sample.loc[sample['Symbol'] == symbol,'Exchange']
        
    param_x = exchange_info.loc[exchange_info['Exchange'] == str(exchange)]
    param_x = param_x.values.tolist()
    print(param_x)
    param['c'] = exchange_info.loc[exchange_info['Exchange'] == str(param['x']),'Currency']
    
    
    lists_4 = ['HKG','TPE','TYO','TPEX']
    lists_6 = ['KRX','KOSDAQ','SHA','SHE']
    a = [ p for p in param.values() if symbol not in lists_4 ]
    b = [ p for p in param.values() if symbol not in lists_6 ]
    if len(a) != 0 :
        n = 4
    elif len(b) != 0 : 
        n = 6   
    param['q'] = str(symbol).rjust(n, "0")
    stock = get_price_data(param)
#    print(stock)
    print("google")
    if stock.empty:
        print("yahoo")
        param['x'] = exchange_info.loc[exchange_info['Exchange'] == str(exchange),'Yahoo']
        symbolData = param['q'] + param['x']
        stock = getStockDataYahoo(symbolData)
    return param, stock
    


def getStockData(data):
    startTime = time.time()
    param = {}
    columns = ['Sector', 'Exchange', 'Symbol', 'Name', 'Date', 'Currency', 'Open', 'High', 'Low', 'Close', 'Volume']
    stocks = pd.DataFrame()
    stock = pd.DataFrame()
    for d in data.values:
        symbol = str(d[1])
      #  print(exchange)
        
        param, stock = getStock(symbol)
        
        if not stock.empty:
            stock['Sector'] = d[3]
            stock['Name'] = d[0]
            stock['Symbol'] = param['q']
            stock['Exchange'] = param['x']
            stock['Currency'] = param['c']
            
            stock = pd.DataFrame(stock, columns = columns)
            stocks = stocks.append(stock)
            #stock.to_csv("C:/0.bigdata/4.web/Triple_Core/0.DataRepo/0.TestData_Papa/" + param['q'] + "." + param['x'], index=False)
            print(str(param['q']) + "." + str(param['x']) + " success")
        else:
            print(str(param['q']) + "." + str(param['x']) + " fail")
    print(stocks)
    stocks.to_csv("C:/0.bigdata/4.web/Triple_Core/0.DataRepo/0.TestData_Papa/test_sample.csv" , index=False)       
    endTime = time.time() - startTime
    print('실행에 소용된 시간=', endTime, '초')
    if endTime / 60 > 1:
        print( endTime / 60, '분')
        

getStockData(sample)
