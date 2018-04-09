# -*- coding: utf-8 -*-
"""
Created on Mon Apr  9 16:40:36 2018

@author: user
"""

import requests
from datetime import datetime
import pandas as pd


#k_param = {
#        'q': "105560",
#        'i': "86400",
#        'x': "KRX",
#        'p': "1Y"
#          }


def get_price_data(query):
    for i in range(0, 2):
        try:
            r = requests.get("https://finance.google.com/finance/getprices", params=query)
            lines = r.text.splitlines()
            data = []
            basetime = 0
            stock = pd.DataFrame()
            for price in lines:
                cols = price.split(",")
                if cols[0][0] == 'a':
                    basetime = int(cols[0][1:])
                    data.append([str(datetime.fromtimestamp(basetime)), float(cols[4]), float(cols[2]), float(cols[3]), float(cols[1]), int(cols[5])])
                elif cols[0][0].isdigit():
                    date = basetime + (int(cols[0])*int(query['i']))
                    data.append([str(datetime.fromtimestamp(date)), float(cols[4]), float(cols[2]), float(cols[3]), float(cols[1]), int(cols[5])])
            
            stock = pd.DataFrame(data, columns = ['Date', 'Open', 'High', 'Low', 'Close', 'Volume'])
            break
        
        except:
            print("%s not collected (%d)" % (query['q'], i + 1))
            
    return stock 