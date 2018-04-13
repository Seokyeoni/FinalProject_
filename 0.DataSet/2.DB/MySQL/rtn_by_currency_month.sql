SELECT Sector, Currency, ROUND( AVG(Rtn) * 20 * 100, 2) as Avg_Rtn
FROM test_stocks_prices
WHERE Date > ( SELECT DATE_SUB('2018-04-01', INTERVAL 12 MONTH) ) and Date < '2018-04-01'
and Code = ?
GROUP BY Currency
ORDER BY Currency ASC

-- test_stocks_prices 
-- Code = 's01'
-- COUNT(*)