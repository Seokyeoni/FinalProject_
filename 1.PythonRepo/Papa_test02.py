# -*- coding: utf-8 -*-
12

import pandas as pd
import numpy as np
import time

from modules.googleFinance import get_price_data
from modules.yahooFinance import getStockDataYahoo


exchanges = ['SHA','SHE','HKG']

sample = pd.read_csv("C:/0.bigdata/4.web/Triple_Core/0.DataRepo/0.TestData_Papa/sample2.csv")
sample = pd.DataFrame(sample)


#def addZeroSymbol(symbols):
#    adj_symbols = []
#    for symbol in symbols:
#        adj_symbol = str(symbol).rjust(6, "0")
#        adj_symbols.append(adj_symbol)
#    return adj_symbols

def getSHA(symbol):
    param = {'i': "86400", 'x': "SHA", 'p': "1Y", 'c': "CNY"}
    adj_symbol = symbol.rjust(6, "0")
    param['q'] = adj_symbol
    stock = get_price_data(param)
    return param, stock

def getSHE(symbol):
    param = {'i': "86400", 'x': "SHE", 'p': "1Y", 'c': "CNY"}
    adj_symbol = symbol.rjust(6, "0")
    param['q'] = adj_symbol
    stock = get_price_data(param)
    return param, stock

def getHKG(symbol):
    param = {'i': "86400", 'x': "HKG", 'p': "1Y", 'c': "HKD"}
    adj_symbol = symbol.rjust(4, "0")
    param['q'] = adj_symbol
    stock = get_price_data(param)
    return param, stock

def getStockData(data):
    startTime = time.time()
    param = {}
    columns = ['Sector', 'Exchange', 'Symbol', 'Name', 'Date', 'Currency', 'Open', 'High', 'Low', 'Close', 'Rtn', 'Volume']
    stocks = pd.DataFrame()
    
    for d in data.values:
        symbol = str(d[1])
        exchange = d[2]
        print(exchange)
        if exchange == "상하이":
            param, stock = getSHA(symbol)
        elif exchange == "심천":
            param, stock = getSHE(symbol)
        elif exchange == "홍콩":
            param, stock = getHKG(symbol)
        
        if not stock.empty:
            stock['Sector'] = d[3]
            stock['Name'] = d[0]
            stock['Symbol'] = str(symbol)
            stock['Exchange'] = d[2]
            stock['Currency'] = param['c']
            stock['Rtn'] = np.log(stock['Close']) - np.log(stock['Close'].shift(1))
            stock = stock.dropna()
            stock = pd.DataFrame(stock, columns = columns)
            stocks = stocks.append(stock)
            #stock.to_csv("C:/0.bigdata/4.web/Triple_Core/0.DataRepo/0.TestData_Papa/" + param['q'] + "." + param['x'], index=False)
            print("success")
        else:
            print("fail")
    print(stocks)
    stocks.to_csv("C:/0.bigdata/4.web/Triple_Core/0.DataRepo/0.TestData_Papa/test_sample.csv" , index=False)       
    endTime = time.time() - startTime
    print('실행에 소용된 시간=', endTime, '초')
    if endTime / 60 > 1:
        print( endTime / 60, '분')
        

getStockData(sample)
#getStockData("JPN", j_util)

#test_param = {
#		'q': "005930",
#		'i': "86400",
#		'x': "KRX",
#		'p': "1M"
#      	}
#
#test = get_price_data(test_param)
#col = test.columns

pivot = pd.read_csv("C:/0.bigdata/4.web/Triple_Core/0.DataRepo/0.TestData_Papa/test_sample.csv")

