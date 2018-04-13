SELECT Sector, DATE_FORMAT(Date, '%Y-%m') as YM , ROUND( AVG(Rtn) * 20, 2) as Avg_Rtn 
FROM test_stocks_prices 
WHERE Date > ( SELECT DATE_SUB('2018-04-01', INTERVAL 12 MONTH) ) and Date < '2018-04-01'
and Volume > 500000
and Code = ?
GROUP BY Code, YM

-- test_stocks_prices
-- Code = 's01'
-- COUNT(*)