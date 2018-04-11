# -*- coding: utf-8 -*-

import os

import pandas as pd
import datetime as dt

aggregated_raw_data_path = "C:/0.bigdata/4.web/Triple_Core/0.DataSet/0.RawData/[AggregatedRawData]"
initial_date = "20180406"
initial_raw_data = "[initial]raw_data_utf8.csv"

update_date = "20180409"
update_raw_data = "[" + update_date + "]" + "raw_data_utf8.csv"

processed_data_path = "C:/0.bigdata/4.web/Triple_Core/0.DataSet/1.ProcessedData"
initial_validated_data = "[initial]validated_data_utf8.csv"
old_validated_data = "old_validated_data_utf8.csv"

#old_validated_data = "real_old_validated_data_utf8.csv"

sector_info = pd.read_json("C:/0.bigdata/4.web/Triple_Core/0.DataSet/1.ProcessedData/sector_info.json", encoding="UTF-8")


def initiate_to_aggregate_RawData(initial_date):
    raw_data_path = "C:/0.bigdata/4.web/Triple_Core/0.DataSet/0.RawData/" + "[" + initial_date + "]"
    raw_data_list = os.listdir(raw_data_path)
    raw_data_df = pd.DataFrame()
    
    for csv_file in raw_data_list:
        csv_df = pd.read_csv(raw_data_path + "/" + csv_file, encoding="UTF-8")
        csv_df.columns = ["Name", "Symbol", "Exchange", "Sector", "Industry"]
        raw_data_df = raw_data_df.append(csv_df)
    
    raw_data_df = pd.DataFrame(raw_data_df, columns = ["Name", "Symbol", "Exchange", "Sector", "Industry"])
    raw_data_df.to_csv(aggregated_raw_data_path + "/" + initial_raw_data, sep=",", index=False, encoding="UTF-8")
    

def update_to_aggregate_RawData(update_date):
    raw_data_path = "C:/0.bigdata/4.web/Triple_Core/0.DataSet/0.RawData/" + "[" + update_date + "]"
    raw_data_list = os.listdir(raw_data_path)
    raw_data_df = pd.DataFrame()
    
    for csv_file in raw_data_list:
        csv_df = pd.read_csv(raw_data_path + "/" + csv_file, encoding="UTF-8")
        csv_df.columns = ["Name", "Symbol", "Exchange", "Sector", "Industry"]
        raw_data_df = raw_data_df.append(csv_df)
    
    raw_data_df = pd.DataFrame(raw_data_df, columns = ["Name", "Symbol", "Exchange", "Sector", "Industry"])   
    raw_data_df.to_csv(aggregated_raw_data_path + "/" + update_raw_data, sep=",", index=False, encoding="UTF-8")


initiate_to_aggregate_RawData(initial_date)
update_to_aggregate_RawData(update_date)


def initiate_to_validate_ListingStatus():
    print("[exc] initiate_to_validate_ListingStatus() ")
    initial_validated_data_df = pd.read_csv(aggregated_raw_data_path + "/" + initial_raw_data, encoding="UTF-8")
    for code in sector_info.keys():
        initial_validated_data_df.loc[ initial_validated_data_df["Sector"] == str(sector_info[code][0]) , "Code"] = code
        
    initial_validated_data_df["Listing_status"]="O"
    initial_validated_data_df["Delisting_date"] = "null"
    initial_validated_data_df["New_listing_date"] = "null"
    initial_validated_data_df = pd.DataFrame(initial_validated_data_df, columns = ["Name", "Symbol", "Exchange", "Code", "Sector", "Industry", "Listing_status", "Delisting_date", "New_listing_date"])
    
    initial_validated_data_df.name = "initiated_in_" + initial_date
    initial_validated_data_df.to_csv(processed_data_path + "/" + initial_validated_data, sep=",", index=False, encoding="UTF-8")


def validate_ListingStatus(update_raw_data):
    processed_data_list = os.listdir(processed_data_path)
    print("[processed_data_list] ", processed_data_list)
    if old_validated_data not in processed_data_list:
        if initial_validated_data not in processed_data_list:
            print("[step01] initial_validated_data not exist")
            initiate_to_validate_ListingStatus()
            print("====================================")
            validate_ListingStatus(update_raw_data)
            
        elif initial_validated_data in processed_data_list:
            print("[step02] initial_validated_data exist" + " but old_validated_data not exist")
            old_validated_data_df = pd.read_csv(processed_data_path + "/" + initial_validated_data, encoding="UTF-8")
            old_validated_data_df.to_csv(processed_data_path + "/" + old_validated_data, sep=",", index=False, encoding="UTF-8")
            print("====================================")
            validate_ListingStatus(update_raw_data)
            
    elif old_validated_data in processed_data_list:
        print("[step03] old_validated_data exist")
        print("====================================")
        old_validated_data_df = pd.read_csv(processed_data_path + "/" + old_validated_data, encoding="UTF-8")
        old_vals = old_validated_data_df.values
#        old_sym_vals = [ [val[1], val[2], val[-3], val[-2], val[-1]] for val in old_vals ]
        
        new_raw_data_df = pd.read_csv(aggregated_raw_data_path + "/" + update_raw_data, encoding="UTF-8")
        new_vals = new_raw_data_df.values
#        new_sym_vals = [ [val[1], val[2], val[-3], val[-2], val[-1]] for val in new_vals ]
#        print(old_vals)
        
#        print("====================================")
#        
        old_listing_vals = [ [ val[1], val[2] ] for val in old_vals 
                                if val[-2] == 'null' ]
#        print(old_listing_vals)
#        print("====================================")
#        
        current_listing_vals = [ [ val[1], val[2] ] for val in new_vals ]
#        print(current_listing_vals)
#        print("====================================")        
#        
        delisting_vals = [ [ val[1], val[2] ] for val in old_vals 
                              if [ val[1], val[2] ] not in current_listing_vals  ]
#        print(delisting_vals)
#        print("====================================")
#        
        new_listing_vals = [ [ val[1], val[2] ] for val in new_vals 
                              if [ val[1], val[2] ] not in old_listing_vals ]
#        print(new_listing_vals)
#        print("====================================")
        
        old_validated_data_df.loc[ old_validated_data_df["Delisting_date"] == "null" , "Listing_status"] = "O"
        
        for val in delisting_vals:
#            print(val)
#            print("====================================")
            old_validated_data_df.loc[ ( old_validated_data_df["Symbol"] == val[0] ) 
                                        & ( old_validated_data_df["Exchange"] == val[1] )
                                        , ["Listing_status", "Delisting_date" ] ] = [ "X", str(dt.date.today().isoformat()) ]
        if len(new_listing_vals) != 0:
            for val in new_listing_vals:
#                print(val)
#                print("====================================")
                new_listing_data = new_raw_data_df.loc[ ( new_raw_data_df["Symbol"] == val[0] )
                                                        & ( new_raw_data_df["Exchange"] == val[1] ) ]
                
                new_listing_data["Listing_status"] = "New"
                new_listing_data["Delisting_date"] = "null"
                new_listing_data["New_listing_date"] = str(dt.date.today().isoformat())
                new_listing_data.columns = ["Name", "Symbol", "Exchange", "Code", "Sector", "Industry", "Listing_status", "Delisting_date", "New_listing_date"]
                old_validated_data_df = old_validated_data_df.append(new_listing_data, ignore_index=True)
            
        old_validated_data_df = pd.DataFrame(old_validated_data_df, columns = ["Name", "Symbol", "Exchange", "Code", "Sector", "Industry", "Listing_status", "Delisting_date", "New_listing_date"])
        old_validated_data_df.to_csv(processed_data_path + "/" + old_validated_data, sep=",", index=False, encoding="UTF-8")

        
        for code in sector_info.keys():
            if code != "Code":
                old_validated_data_df = pd.read_csv(processed_data_path + "/" + old_validated_data, encoding="UTF-8")
                old_validated_data_by_sector = "old_validated_data_" + code + "_utf8.csv"
                old_validated_data_df_by_sector = old_validated_data_df.loc[ old_validated_data_df["Code"] == code ]
                old_validated_data_df_by_sector.to_csv(processed_data_path + "/by_sector/" + old_validated_data_by_sector, sep=",", index=False, encoding="UTF-8")
            else:
                continue


### ============================================================================
### sample test : virtual data 
#
## number of sample = 2
#sample_new_raw_data_01 = "sample_new_raw_data01_utf8.csv"
#sample_new_raw_data_02 = "sample_new_raw_data02_utf8.csv"
#
## number of sample = 8
#sample_new_raw_data_03 = "sample_new_raw_data03_utf8.csv"
#sample_new_raw_data_04 = "sample_new_raw_data04_utf8.csv"
#
##validate_ListingStatus(sample_new_raw_data_01)
##validate_ListingStatus(sample_new_raw_data_03)
#
#Symbol이 숫자만 있으면 int로 인식해서 검증 로직 제대로 작동 하지 않기 때문에, 항상 str값도 포함시켜서 데이터셋 구성해야 함
        
### ============================================================================
### sample test : real data
#
## number of sample = 8
#real_sample_new_raw_data = "real_sample_new_raw_data01_utf8.csv"
#validate_ListingStatus(real_sample_new_raw_data)
#        
### ============================================================================

validate_ListingStatus(update_raw_data)
