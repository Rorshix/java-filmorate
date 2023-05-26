#### java-filmorate

# *Доступ к данным приложения осуществляется согласно следующей ER-диаграмме:*
![This is an image](src/main/resources/Untitled.png)
## **Примеры SQL-запросов:**


- Получить список всех фильмов:

``` roomsql
SELECT
f.name AS title
FROM
Film AS f;
```
- Получить список всех пользователей:
```roomsql
SELECT u.name, u.login, u.email
FROM User AS u;
```
- Получить список всех пользователей (имя и логин), лайкнувших фильм "Крестный отец":
```roomsql
SELECT u.Name, u.Login
FROMUser AS u
WHERE f.name = "Крестный отец"
RIGHT JOIN Likes_line AS ll ON u.User_id = ll.User_id
INNER JOIN Film AS f ON ll.Film_id = f.Film_id
GROUP BY u.Name
ORDER BY u.Name;
```
- Получить список всех подтвержденных друзей (имя и логин) пользователя с id 1:
```roomsql
SELECT uf.Name, uf.Login
FROMUser AS uf
WHEREuf.UserID IN (SELECT u.User_ID FROM User AS u
WHERE u.User_ID = 1 AND s.Status = 'Aproved'
INNER JOIN Frends_line AS fl ON u.User_id = fl.User_id
INNER JOIN Status AS s ON fl.Status_ID = s.Status_ID
);
```




- Получить список названий пяти самых залайканых фильмов:
```roomsql
SELECT f.Name, COUNT (ll.Likes_line_ID)
FROM Film AS f
LEFT JOIN Likes_line as ll ON f.Film_ID = ll.Film_ID
GROUP BY f.Name
ORDER BY COUNT (ll.Likes_line_ID) DESC
LIMIT 5;
```
- Получить список названий всех фильмов жанра комедия:
```roomsql
SELECT f.Name
FROM Film AS f
WHERE g.genre = 'Comedy'
LEFT JOIN Genres_line as gl ON f.Film_ID = ll.Film_ID
LEFT JOIN Genres as g ON gl.Genre_ID = g.Genre_ID
```
- Получить список всех фильмов, продолжительностью более 120 минут:
```roomsql
SELECT f.Name
FROM Film AS f
WHERE f.Duration > 120;
```
- Получить список всех фильмов рейтинга R за 2022 год:
```roomsql
SELECT f.Name
FROM Film AS f
WHERE EXTRACT(YEAR FROM CAST(ReleaseDate AS date)) = '2022'
AND r.Rating = 'R'
RIGHT JOIN Rating as r ON f.Rating_ID = r.Rating_ID
```