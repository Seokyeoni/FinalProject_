SELECT Sector, CONCAT( ROUND( AVG(Rtn) * 63 * 100, 2), '%' ) as Avg_Rtn, MIN(Date), MAX(Date) FROM test_stocks_prices 
WHERE Date > ( SELECT DATE_SUB('2018-04-01', INTERVAL 8 QUARTER) ) and Date < '2018-04-01'
and Code = ?
GROUP BY Code


-- Code = 's01'
-- COUNT(*)