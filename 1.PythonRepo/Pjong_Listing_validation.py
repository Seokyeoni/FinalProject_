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
    path_csv = "C:/0.bigdata/4.web/Triple_Core/0.DataRepo/0.TestData_Pjong/csv_data/"
    path_info = "C:/0.bigdata/4.web/Triple_Core/0.DataRepo/0.TestData_Pjong/"
    csv_list = os.listdir(path_csv)
    info_list = os.listdir(path_info)
    
    check_symbol_info()
    symbol_info = pd.read_csv("C:/0.bigdata/4.web/Triple_Core/0.DataRepo/0.TestData_Pjong/symbol_info.csv")
    symbol_info["listing"]='x'
    for file in csv_list:
        symbol_new = pd.read_csv("C:/0.bigdata/4.web/Triple_Core/0.DataRepo/0.TestData_Pjong/csv_data/"+file)       
        for symbol in symbol_info['symbol'].values:
            if symbol in symbol_new['symbol'].values:
                symbol_info.loc[symbol_info['symbol'] == symbol,'listing']='o'
        #갱신된 것
        for symbol in symbol_new['symbol'].values:
            if symbol not in symbol_info['symbol']:
                symbol_info.append(symbol_new.loc[symbol_new['symbol'] == symbol.index])
                symbol_info.loc[symbol_info['symbol'] == symbol,'listing']='New'
                
    update_time = datetime.date.today().isoformat()
    symbol_info["updatetime"]=update_time
    symbol_info.to_csv("C:/0.bigdata/4.web/Triple_Core/0.DataRepo/0.TestData_Pjong/symbol_info_update.csv", index=False, sep=",", encoding='‘utf-8’')

def update_symbol_info():
    symbol_info_update = pd.read_csv("C:/0.bigdata/4.web/Triple_Core/0.DataRepo/0.TestData_Pjong/symbol_info_update.csv")
    symbol_info = pd.read_csv("C:/0.bigdata/4.web/Triple_Core/0.DataRepo/0.TestData_Pjong/symbol_info.csv")
    #불러와서 저장
check_symbol_info()
listing_validation()

