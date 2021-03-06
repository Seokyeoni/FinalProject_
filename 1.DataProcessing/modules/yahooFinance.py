
# 주가 데이터 수집 관련 함수를 정의한다
#
# 한국생산성본부 금융 빅데이터 전문가 과정 (금융 모델링 파트) 실습용 코드
# Written : 2018.2.5
# 제작 : 조성현
# -----------------------------------------------------------------
import pandas_datareader.data as web
import pandas as pd
import datetime as dt

# Yahoo site로 부터 대형주 종목 데이터를 수집하여 파일에 저장한다.
# Yahoo site로 부터 주가 데이터를 수집한다. 가끔 안들어올 때가 있어서 10번 시도한다.
# 수정 주가로 환산하여 읽어온다
def getStockDataYahoo(stockCode, start='', end=''):
    # 수집 기간
    if start == '':
        today = dt.datetime.today()
        time_delta = dt.timedelta(days=365)
        firstday = (today - time_delta).replace(day=1)
        start = dt.datetime.strftime(firstday,"%Y-%m-%d")
    else:
        start = dt.datetime.strptime(start, '%Y-%m-%d')
    
    if end == '':
        end = dt.date.today()
    else:
        end = dt.datetime.strptime(end, '%Y-%m-%d')
    
    stock = pd.DataFrame()
    for i in range(0, 1):
        try:
            stock = web.YahooDailyReader(stockCode, start, end, adjust_price=True).read()
        except:
            print("%s not collected (%d)" % (stockCode, i + 1))
            
        if not stock.empty:
            # 수정주가 비율은 이미 적용되었으므로 제거한다
            stock = stock.drop('Adj_Ratio', 1)
            
            # Volume이 0 인 경우가 있으므로, 이를 제거한다 
            stock = stock.drop(stock[stock.Volume < 10000].index)
            
            # 수집한 데이터를 파일에 저장한다.
            break
        
    if stock.empty:
        print("%s not collected" % stockCode)
    

    return stock

# 일일 데이터를 주간 (Weekly), 혹은 월간 (Monthly)으로 변환한다
def myAgg(x):
    names = {
            'Open' : x['Open'].head(1),
            'High' : x['High'].max(),
            'Low' : x['Low'].min(),
            'Close' : x['Close'].tail(1),
            'Volume' : x['Volume'].mean()}
    return pd.Series(names, index=['Open', 'High', 'Low', 'Close', 'Volume'])

def getWeekMonthOHLC(x, type='Week'):
    if type == 'Week':
        rtn = x.resample('W-Fri').apply(myAgg)
    elif type == 'Month':
        rtn = x.resample('M').apply(myAgg)
    else:
        print("invalid type in getWeekMonthOHLC()")
        return
    rtn = rtn.dropna()
    rtn = rtn.apply(pd.to_numeric)
    return rtn



def getStockDataYahooTwoYr(stockCode, start='', end=''):
    # 수집 기간
    if start == '':
        today = dt.datetime.today()
        time_delta = dt.timedelta(days=730)
        firstday = (today - time_delta).replace(day=1)
        start = dt.datetime.strftime(firstday,"%Y-%m-%d")
    else:
        start = dt.datetime.strptime(start, '%Y-%m-%d')
    
    if end == '':
        end = dt.date.today()
    else:
        end = dt.datetime.strptime(end, '%Y-%m-%d')
    
    stock = pd.DataFrame()
    for i in range(0, 2):
        try:
            stock = web.YahooDailyReader(stockCode, start, end, adjust_price=True).read()
        except:
            print("%s not collected (%d)" % (stockCode, i + 1))
            
        if not stock.empty:
            # 수정주가 비율은 이미 적용되었으므로 제거한다
            stock = stock.drop('Adj_Ratio', 1)
            
            # Volume이 0 인 경우가 있으므로, 이를 제거한다 
#            stock = stock.drop(stock[stock.Volume < 10].index)
            stock = stock.drop(stock[stock.Volume < 10000].index)
            
            # 수집한 데이터를 파일에 저장한다.
            break
        
    if stock.empty:
        print("%s not collected" % stockCode)
    

    return stock