-- 코드를 입력하세요
SELECT USER.USER_ID, USER.NICKNAME, SUM(BOARD.PRICE) AS TOTAL_SALES
FROM USED_GOODS_USER USER
JOIN USED_GOODS_BOARD BOARD
ON USER.USER_ID = BOARD.WRITER_ID
WHERE BOARD.STATUS = 'DONE'
GROUP BY BOARD.WRITER_ID
HAVING SUM(BOARD.PRICE) >= 700000
ORDER BY TOTAL_SALES;