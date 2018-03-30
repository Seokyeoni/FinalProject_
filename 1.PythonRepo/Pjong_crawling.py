import pandas as pd
import numpy as np
from modules.googleFinance import get_price_data
from modules.yahooFinance import getStockDataYahoo
import time

import requests
from bs4 import BeautifulSoup


def addZeroSymbol(symbols):
    adj_symbols = []
    for symbol in symbols:
        adj_symbol = str(symbol).rjust(6, "0")
        adj_symbols.append(adj_symbol)
    return adj_symbols

def get_symbol():
    req = requests.get('https://tradingeconomics.com/stocks')
    html = req.text
    soup = BeautifulSoup(html, 'html.parser')
    exchanges = soup.select('td.datatable-item-first > a')
    
    info_pair={}
    for exchange in exchanges:
        req = requests.get('https://tradingeconomics.com'+ exchange.get('href'))
        html = req.text
        soup = BeautifulSoup(html, 'html.parser')
        sub_symbols = soup.select('td.hidden-xs > a > b')
        
        symbol_list=[]
        for sub_symbol in sub_symbols:
            symbol_list.append(sub_symbol.text)
            
        info_pair[exchange.text.strip()]=symbol_list
    return info_pair

param = {
       'q': "", 
		'i': "86400",
		'x': "",
		'p': "1Y"
      	}
    
def getStockData(data):
    startTime = time.time()
    
    for i in range(len(info_pair)-1):
        symbols = info_pair.keys()
        
        for symbol in symbols:
            
    
    
    
    
    
    
    
    symbols = data["기호"]
    
    if 
        k_adj_symbols = addZeroSymbol(symbols)
    
    for symbol in adj_symbols:
        param['q'] = str(symbol)
        stock = get_price_data(param)
        stock["기호"] = symbol
        stock.to_pickle('StockData/' + param['q'] + "." + param['x'])
        
    endTime = time.time() - startTime
    print('실행에 소용된 시간=', endTime, '초')
    if endTime / 60 > 1:
        print( endTime / 60, '분')