# -*- coding: utf-8 -*-

import pandas as pd
import os
import datetime
#DB에 저장된 정보를 CSV로 하나 유지(모든 파일의 통합) 미리 생성
#csv는 미리 존재해야함

path_csv = "C:/0.bigdata/4.web/Triple_Core/0.DataRepo/0.TestData_Pjong/csv_data/"
path_info = "C:/0.bigdata/4.web/Triple_Core/0.DataRepo/0.TestData_Pjong/"
csv_list = os.listdir(path_csv)
info_list = os.listdir(path_info)


def check_symbol_info():
    check_exist_info = [i for i in info_list if i.endswith('.csv')]
    if 'symbol_info_update.csv' not in check_exist_info:
        print("새로 뽑음")
        symbol_info = pd.DataFrame()
        for file in csv_list:
            symbol_new = pd.read_csv("C:/0.bigdata/4.web/Triple_Core/0.DataRepo/0.TestData_Pjong/csv_data/"+ file)  
            symbol_info=symbol_info.append(symbol_new)
        symbol_info.to_csv("C:/0.bigdata/4.web/Triple_Core/0.DataRepo/0.TestData_Pjong/symbol_info.csv", index=False, sep=",", encoding='‘utf-8’')
        
        
        
#존재하면 o입력,(없어졌으면 x 유지) 
def listing_validation():
    check_symbol_info()
    symbol_info = pd.read_csv("C:/0.bigdata/4.web/Triple_Core/0.DataRepo/0.TestData_Pjong/symbol_info.csv")
    symbol_info["listing"]='x'
    info_vals = symbol_info['symbol'].values
    for file in csv_list:
        symbol_new = pd.read_csv("C:/0.bigdata/4.web/Triple_Core/0.DataRepo/0.TestData_Pjong/csv_data/"+file)
        vals = symbol_new['symbol'].values
        for symbol in info_vals:
            if symbol in vals:
                symbol_info.loc[symbol_info['symbol'] == symbol,'listing']='o'
        #갱신된 것
        a = [ symbol for symbol in vals if symbol not in info_vals ]
        #print(a)
        if len(a) != 0:
            b = symbol_new[symbol_new['symbol'] == a]
            b['listing'] = 'New'
            symbol_info = symbol_info.append(b, ignore_index=True)
     
    update_time = datetime.date.today().isoformat()
    symbol_info["updatetime"]=update_time
    symbol_info.name = "a"
    symbol_info.to_csv("C:/0.bigdata/4.web/Triple_Core/0.DataRepo/0.TestData_Pjong/symbol_info_update.csv", index=False, sep=",", encoding='‘utf-8’')

def update_symbol_info():
    symbol_info_update = pd.read_csv("C:/0.bigdata/4.web/Triple_Core/0.DataRepo/0.TestData_Pjong/symbol_info_update.csv")
    symbol_info = pd.read_csv("C:/0.bigdata/4.web/Triple_Core/0.DataRepo/0.TestData_Pjong/symbol_info.csv")
    #불러와서 저장
check_symbol_info()
listing_validation()
symbol_new = pd.read_csv("C:/0.bigdata/4.web/Triple_Core/0.DataRepo/0.TestData_Pjong/symbol_info.csv")
symbol_new.loc[symbol_new['symbol']==int('11000')]
