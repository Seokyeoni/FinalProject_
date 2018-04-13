SELECT Sector, CONCAT( ROUND( AVG(Rtn) * 20 * 100, 2), '%' ) as Avg_Rtn, MIN(Date), MAX(Date) 
FROM test_stocks_prices
WHERE Date > ( SELECT DATE_SUB('2018-04-01', INTERVAL 12 MONTH) ) and Date < '2018-04-01'
and Volume > 500000
and Code = ?
GROUP BY Code

-- test_stocks_prices 
-- Code = 's01'
-- COUNT(*)