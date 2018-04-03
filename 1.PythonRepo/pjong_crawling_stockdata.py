# -*- coding: utf-8 -*-
"""
Created on Tue Apr  3 11:13:27 2018

@author: user
"""

# -*- coding: utf-8 -*-

import pandas as pd
import time

from modules.pjong_googleFinance import get_price_data
from modules.pjong_yahooFinance import getStockDataYahoo

symbol_info=pd.read_csv("C:/0.bigdata/4.web/Triple_Core/0.DataRepo/0.LocalStorage/symbol_info.csv")

def collectData(q,x,startDate="2016-01-01"):
    s = getStockDataYahoo(q + '.' + x, start=startDate)
    s.to_pickle('Stocks/' + q + '.' + x )

def set_param(q,x):
    param = {
            'q': q,
            'i': "86400",
            'x': x,
            'p': "2Y"
    }
    return param

def add_ZeroSymbol(symbol,n):
    adj_symbol = str(symbol).rjust(n, "0")
    return adj_symbol

def get_StockData_google(param):#트라이 캐치 할 것
    stock = get_price_data(param)
    stock.to_pickle('Stocks/' + param['q'] + "." + param['x'])
exchange_match={"서울":"KRX","KOSDAQ":"KOSDAQ","심천":"SHE","상하이":"SHA","홍콩":"HKG","대만":"TPE","일본":"TYO","TPEX":"TWO","싱가포르":"SI","멕시코":"MX"}
def get_Data():
    startTime = time.time()
    symbol_info=pd.read_csv("C:/0.bigdata/4.web/Triple_Core/0.DataRepo/0.LocalStorage/symbol_info.csv")
    for list in symbol_info.values:
        if list[2] == '서울':
            q= add_ZeroSymbol(list[1],6)
            x='KRX'
            get_StockData_google(set_param(q,x))
            print('1')
        elif list[2] =='KOSDAQ':
            q= add_ZeroSymbol(list[1],6)
            x='KOSDAQ'
            get_StockData_google(set_param(q,x))
            print('2')
        elif list[2] =='심천':
            q= add_ZeroSymbol(list[1],6)
            x='SHE'
            get_StockData_google(set_param(q,x))
            print('3')
        elif list[2] =='상하이':
            q= add_ZeroSymbol(list[1],6)
            x='SHA'
            get_StockData_google(set_param(q,x))
            print('4')
        elif list[2] =='홍콩':
            q= add_ZeroSymbol(list[1],4)
            x='HKG'
            get_StockData_google(set_param(q,x))
            print('5')
        elif list[2] =='대만':
            q= add_ZeroSymbol(list[1],4)
            x='TPE'
            get_StockData_google(set_param(q,x))
            print('6')
        elif list[2] =='일본':
            q= add_ZeroSymbol(list[1],4)
            x='TYO'
            get_StockData_google(set_param(q,x))
            print('7')
        else :
            if list[2] == 'TPEX':
                q= add_ZeroSymbol(list[1],4)
                x='TWO'
                collectData(q,x)
            elif list[2] == '싱가포르':
                q= add_ZeroSymbol(list[1],4)
                x='SI'
                collectData(q,x)
            elif list[2] == '멕시코':
                q= add_ZeroSymbol(list[1],4)
                x='MX'
                collectData(q,x)
                
    endTime = time.time() - startTime
    print('실행에 소용된 시간=', endTime, '초')
    if endTime / 60 > 1:
        print( endTime / 60, '분')
            
get_Data()
