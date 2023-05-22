# 12. Highest Peaks in Bulgaria
SELECT c.country_code, m.mountain_range, p.peak_name, p.elevation
FROM countries c
JOIN mountains_countries mc 
ON c.country_code = mc.country_code
JOIN mountains m
ON mc.mountain_id = m.id
JOIN peaks p
ON m.id = p.mountain_id
WHERE p.elevation > 2835 AND c.country_code = 'BG'
ORDER BY p.elevation DESC;

# 13. Count Mountain Ranges
SELECT mc.country_code, COUNT(m.mountain_range) AS 'mountain_range' 
FROM mountains m 
JOIN mountains_countries mc ON m.id = mc.mountain_id
WHERE mc.country_code IN ('BG', 'RU', 'US')
GROUP BY mc.country_code
ORDER BY mountain_range DESC;

# 14. Countries with Rivers
SELECT c.country_name, r.river_name
FROM countries c
LEFT JOIN countries_rivers cr ON c.country_code = cr.country_code
LEFT JOIN rivers r ON cr.river_id = r.id
WHERE c.continent_code = 'AF'
ORDER BY c.country_name
LIMIT 5;

# 15. *Continents and Currencies
SELECT co.continent_code,
 (SELECT cur.currency_code FROM countries c2
 WHERE c.currency_code = c2.currency_code
 GROUP BY c2.currency_code
 ORDER BY COUNT(*)
 LIMIT 1) AS 'currency_code',
 COUNT(*) AS 'currency_usage'
FROM continents co
JOIN countries c ON co.continent_code = c.continent_code
JOIN currencies cur ON c.currency_code = cur.currency_code
GROUP BY co.continent_code;

# 16. Countries without any Mountains
SELECT COUNT(*) AS 'country_count'
FROM countries c
LEFT JOIN mountains_countries mc 
ON c.country_code = mc.country_code
LEFT JOIN mountains m
ON mc.mountain_id = m.id
WHERE mountain_range IS NULL;

# 17. Highest Peak and Longest River by Country
SELECT c.country_name, MAX(p.elevation) AS 'highest_peak_elevation', MAX(r.length) AS 'longest_river_length'
FROM countries c
LEFT JOIN mountains_countries mc ON c.country_code = mc.country_code
LEFT JOIN peaks p ON mc.mountain_id = p.mountain_id
LEFT JOIN countries_rivers cr ON c.country_code = cr.country_code
LEFT JOIN rivers r ON cr.river_id = r.id
GROUP BY c.country_code
ORDER BY highest_peak_elevation DESC, longest_river_length DESC, c.country_name
LIMIT 5;





