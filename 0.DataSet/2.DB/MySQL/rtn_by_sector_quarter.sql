SELECT Sector, CONCAT(YEAR(Date), '-', QUARTER(Date)) as YQ, ROUND( AVG(Rtn) * 63 *, 2 ) as Avg_Rtn FROM test_stocks_prices 
WHERE Date > ( SELECT DATE_SUB('2018-04-01', INTERVAL 8 QUARTER ) ) and Date < '2018-04-01'
and Volume > 500000
and Code = ?
GROUP BY Code, YQ


-- Code = 's01'
-- COUNT(*)