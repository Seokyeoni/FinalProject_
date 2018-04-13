SELECT Name, Exchange, Sector, Industry, CONCAT( ROUND( AVG(Rtn) * 20 * 100, 2), '%' ) as Avg_Rtn, ROUND( AVG(Volume), 0 ) as Avg_Vol, MIN(Date), MAX(Date) 
FROM test_stocks_prices
WHERE Date > ( SELECT DATE_SUB('2018-04-01', INTERVAL 12 MONTH) ) and Date < '2018-04-01'
and Code = ? or Code = ? or Code = ?
GROUP BY Name
HAVING Avg_Vol > 1000000
ORDER BY Avg_Rtn DESC
LIMIT 5


-- s01, s02, s03 
-- Code = 's01' or Code = 's02' or Code = 's03'
-- COUNT(*)