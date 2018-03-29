# -*- coding: utf-8 -*-

import requests
from bs4 import BeautifulSoup
from urllib.request import urlopen
import json
import os
import pandas as pd



# 필요한 링크들의 주소가 나옴, 링크만 뽑는 함수 이용해서 뽑고,
#리스트에 담은 다음 format 이용해서 for문 돌리면 다 가져올 수 있음.

#print(mainsoup.find_all('a'))



BASE_DIR = os.path.dirname(os.path.abspath(__file__))

#
#with open(os.path.join(BASE_DIR, 'result.json'), 'w+') as json_file:
#    json.dump(data, json_file)
    
req = requests.get("https://tradingeconomics.com/japan/stock-market")
soup = BeautifulSoup(req.text, 'html.parser')

symbol = soup.select('td > a > b')
columns = soup.select('td > a')
components = []


# 홀수번째
for i in range(len(columns)):
    if not i % 2 == 0:
        components.append(columns[i].text.strip())
        if columns[i].text.strip() == 'NIKKEI 225':
            break


stocks = {}
for x, y in zip(symbol, components):
    stocks[x.text.strip()] = y

with open(os.path.join(BASE_DIR, 'TYO.json'), 'w+') as json_file:
    json.dump(stocks, json_file)

