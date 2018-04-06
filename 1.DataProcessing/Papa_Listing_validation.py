# -*- coding: utf-8 -*-

import os

import pandas as pd
import datetime as dt

#DB에 저장된 정보를 CSV로 하나 유지(모든 파일의 통합) 미리 생성
#csv는 미리 존재해야함

initial_date = "20180406"
update_date = "20180406"

target_path = "C:/0.bigdata/4.web/Triple_Core/0.DataSet/0.RawData/[AggregatedRawData]"



def initiate_to_aggregate_RawData(initial_date):
    raw_data_df = pd.DataFrame()
    raw_data_path = "C:/0.bigdata/4.web/Triple_Core/0.DataSet/0.RawData/" + "[" + initial_date + "]"
    raw_data_list = os.listdir(raw_data_path)
    
    for csv_file in raw_data_list:
        csv_df = pd.read_csv(raw_data_path + "/" + csv_file, encoding="UTF-8")
        csv_df.columns = ['Name', 'Symbol', 'Exchange', 'Sector', 'Industry']
        raw_data_df = raw_data_df.append(csv_df)
        
    raw_data_df.to_csv(target_path + "/" + "[initial]raw_data_utf8.csv", sep=",", index=False, encoding="UTF-8")
    

def update_to_aggregate_RawData(update_date):
    raw_data_df = pd.DataFrame()
    raw_data_path = "C:/0.bigdata/4.web/Triple_Core/0.DataSet/0.RawData/" + "[" + update_date + "]"
    raw_data_list = os.listdir(raw_data_path)
    
    for csv_file in raw_data_list:
        csv_df = pd.read_csv(raw_data_path + "/" + csv_file, encoding="UTF-8")
        csv_df.columns = ['Name', 'Symbol', 'Exchange', 'Sector', 'Industry']
        raw_data_df = raw_data_df.append(csv_df)
        
    raw_data_df.to_csv(target_path + "/" + "[" + update_date + "]" + "raw_data_utf8.csv", sep=",", index=False, encoding="UTF-8")
        

#def check_old_raw_data():
#    check_exist_info = [i for i in info_list if i.endswith('.csv')]
#    if 'old_raw_data.csv' not in check_exist_info:
#        old_raw_data = pd.DataFrame()
#        for file in raw_data_list:
#            new_raw_data = pd.read_csv("C:/0.bigdata/4.web/Triple_Core/0.DataRepo/0.LocalStorage/csv_data/"+ file)
#            new_raw_data.columns = ['Name', 'Symbol', 'Exchange', 'Sector', 'Industry']
#            old_raw_data=old_raw_data.append(new_raw_data)
#        old_raw_data.to_csv("C:/0.bigdata/4.web/Triple_Core/0.DataRepo/0.LocalStorage/old_raw_data1.csv", index=False, sep=",", encoding='‘utf-8’')
        

old_date = "yesterday"
new_date = "today"

def validate_ListingStatus():
    old_raw_data = pd.read_csv(target_path + "/" + old_date + ".csv", encoding="UTF-8")
    old_raw_data["Listing_status"]="Delisted"
    old_vals = old_raw_data["Symbol"].values
    
    new_raw_data = pd.read_csv(target_path + "/" + new_date + ".csv", encoding="UTF-8")
    new_vals = new_raw_data["Symbol"].values
    
    for old_symbol in old_vals:
        if old_symbol in new_vals:
            old_raw_data.loc[old_raw_data["Symbol"] == old_symbol, "Listing_status"]='Listed'
    new_symbols = [ symbol for symbol in new_vals if symbol not in old_vals ]
    if len(new_symbols) != 0:
        for new_symbol in new_symbols:
            new_listing_data = new_raw_data[new_raw_data["Symbol"] == new_symbol]
            new_listing_data["Listing_status"] = "New"
            update_time = dt.date.today().isoformat()
            new_listing_data["New_listing_date"] = str(update_time)
            old_raw_data = old_raw_data.append(new_listing_data, ignore_index=True)

    old_raw_data.to_csv(target_path + "/" + old_date + ".csv", sep=",", index=False, encoding="UTF-8")

