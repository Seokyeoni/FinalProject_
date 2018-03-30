# -*- coding: utf-8 -*-

import pandas as pd
import numpy as np
import time

from modules.googleFinance import get_price_data
from modules.yahooFinance import getStockDataYahoo



k_util = pd.read_csv("C:/0.bigdata/4.web/KPC_FinalProject/0.DataRepo/0.TestData_Papa/k_util.csv")
k_util = pd.DataFrame(k_util)


def addZeroSymbol(symbols):
    adj_symbols = []
    for symbol in symbols:
        adj_symbol = str(symbol).rjust(6, "0")
        adj_symbols.append(adj_symbol)
    return adj_symbols
        

#k_param = {
#		'q': "105560",
#		'i': "86400",
#		'x': "KRX",
#		'p': "1Y"
#      	}
#get_price_data(k_param)
    
#        symbol = d["기호"]
#        name = d["종목"]
#        exchange = d["거래소"]
#        sector = d["부문"]
#        industry = d["산업"]

k_param = {
		'i': "86400",
		'x': "KRX",
		'p': "1Y"
      	}

def getStockData(data):
    startTime = time.time()
    columns = ['거래소', '기호', '부문', '산업', '종목', 'Open', 'High', 'Low', 'Close', 'Volume']
        
    for d in data.values:
        symbol = str(d[1])
        symbol = symbol.rjust(6, "0")
        k_param['q'] = str(symbol)
        stock = get_price_data(k_param)
        stock['종목'] = d[0]
        stock['기호'] = str(symbol)
        stock['거래소'] = d[2]
        stock['부문'] = d[3]
        stock['산업'] = d[4]
        
        stock = pd.DataFrame(stock, index = stock.index, columns = columns)
        if not stock.empty:
            stock.to_pickle('StockData/' + k_param['q'] + "." + k_param['x'])
            
    endTime = time.time() - startTime
    print('실행에 소용된 시간=', endTime, '초')
    if endTime / 60 > 1:
        print( endTime / 60, '분')

getStockData(k_util)



#test1 = k_util.iloc[:2]
#col1 = k_util.columns
#
#test_param = {
#		'q': "105560",
#		'i': "86400",
#		'x': "KRX",
#		'p': "1Y"
#      	}
#
#test2 = get_price_data(test_param)
#col2 = test2.columns


        


