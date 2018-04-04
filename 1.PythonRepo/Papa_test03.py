# -*- coding: utf-8 -*-

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

#q = symbol, i = interval, x = exchange, p=d
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

def getSHE(symbol):
    param = {'i': "86400", 'x': "SHE", 'p': "1Y", 'c': "CNY"}
    param['q'] = symbol.rjust(6, "0")
    
    stock = get_price_data(param)
    return param, stock

def getHKG(symbol):
    param = {'i': "86400", 'x': "HKG", 'p': "1Y", 'c': "HKD"}
    param['q'] = symbol.rjust(4, "0")
    stock = get_price_data(param)
    return param, stock

def getTPE(symbol):
    param = {'i': "86400", 'x': "TPE", 'p': "1Y", 'c': "TWD"}
    param['q'] = symbol.rjust(4, "0")
    stock = get_price_data(param)
    return param, stock

def getTYO(symbol):
    param = {'i': "86400", 'x': "TYO", 'p': "1Y", 'c': "JPY"}
    param['q'] = symbol.rjust(4, "0")
    stock = get_price_data(param)
    return param, stock

def getKRX(symbol):
    param = {'i': "86400", 'x': "KRX", 'p': "1Y", 'c': "KRW"}
    param['q'] = symbol.rjust(6, "0")
    stock = get_price_data(param)
    return param, stock

def getKOSDAQ(symbol):
    param = {'i': "86400", 'x': "KOSDAQ", 'p': "1Y", 'c': "KRW"}
    param['q'] = symbol.rjust(6, "0")
    stock = get_price_data(param)
    return param, stock

def get
    param = {'i': "86400", 'x': x, 'p': "1Y", 'c': c}
    param['q'] = symbol.rjust(6, "0")
    stock = get_price_data(param)
    return param, stock
    


def getStockData(data):
    startTime = time.time()
    param = {}
    columns = ['Sector', 'Exchange', 'Symbol', 'Name', 'Date', 'Currency', 'Open', 'High', 'Low', 'Close', 'Volume']
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
        elif exchange == "대만":
             param, stock = getTPE(symbol)
        elif exchange == "일본":
             param, stock = getTYO(symbol)
        elif exchange == "서울":
             param, stock = getKRX(symbol)
        elif exchange == "KOSDAQ":
             param, stock = getKOSDAQ(symbol)
        else :
            if exchange == "TPEX":
                param, stock = getTWO(symbol)
            elif exchange == "싱가포르":
                param, stock = getSI(symbol)
            elif exchange == "멕시코":
                param, stock = getMX(symbol)
            
        
        
        
        
        if not stock.empty:
            stock['Sector'] = d[3]
            stock['Name'] = d[0]
            stock['Symbol'] = str(symbol)
            stock['Exchange'] = d[2]
            stock['Currency'] = param['c']
            
            stock = pd.DataFrame(stock, columns = columns)
            stocks = stocks.append(stock)
            #stock.to_csv("C:/0.bigdata/4.web/Triple_Core/0.DataRepo/0.TestData_Papa/" + param['q'] + "." + param['x'], index=False)
            print(d[1] + "." + d[2] + " success")
        else:
            print(d[1] + "." + d[2] + " fail")
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


