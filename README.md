# JavaWebSSH
201905 東認 JavaWEB SSH 上課範例

-- 客戶資料檔
CREATE TABLE Customer(
    cus_id varchar(10) primary key, -- 主鍵(客戶代號)
    cus_name varchar(50) not null, -- (客戶名字)
    cus_ts timestamp default current_timestamp -- (建檔時間)
);

-- Foreign Key constraint in Derby

-- 訂單主檔
CREATE TABLE OrderMaster(
    master_id integer primary key generated always as identity, -- 主鍵(訂單代號)
    cus_id varchar(10) constraint cus_id_fk references Customer(cus_id), -- 外鍵(客戶代號)
    master_memo varchar(50), -- (訂單備註)
    master_ts timestamp default current_timestamp -- (訂單建立時間)
);

-- 訂單明細
CREATE TABLE OrderItem(
    item_id integer primary key generated always as identity, -- (項目流水序號)
    master_id integer constraint master_id_fk references OrderMaster(master_id),-- 外鍵(訂單代號)
    item_name varchar(50) not null, -- (項目名稱)
    item_price integer not null, -- (項目金額)
    item_amount integer not null, -- (項目數量)
    item_ts timestamp default current_timestamp -- (項目加入時間)
);

-- 一般限制查詢
SELECT c.CUS_ID, c.CUS_NAME, m.MASTER_ID, m.MASTER_MEMO
FROM SSH.CUSTOMER c, SSH.ORDERMASTER m
WHERE c.CUS_ID = m.CUS_ID

-- LEFT JOIN限制查詢
SELECT c.CUS_ID, c.CUS_NAME, m.MASTER_ID, m.MASTER_MEMO 
FROM SSH.CUSTOMER c
LEFT JOIN SSH.ORDERMASTER m 
ON m.cus_id = c.cus_id

-- 每一筆訂單總金額 part 1
SELECT o.MASTER_ID, SUM(o.ITEM_PRICE * o.ITEM_AMOUNT) as Total
FROM SSH.ORDERITEM o
GROUP BY o.MASTER_ID

-- 每一筆訂單總金額 part 2
SELECT o.MASTER_ID, c.CUS_NAME, SUM(o.ITEM_PRICE * o.ITEM_AMOUNT) as Total
FROM SSH.ORDERITEM o, SSH.ORDERMASTER m, SSH.CUSTOMER c
WHERE o.MASTER_ID = m.MASTER_ID AND m.CUS_ID = c.CUS_ID
GROUP BY o.MASTER_ID, c.CUS_NAME

-- 多對多
CREATE TABLE Fund(
    f_id integer primary key generated always as identity,
    f_name varchar(50) not null,
    f_ts timestamp default current_timestamp
);

CREATE TABLE Stock(
    s_id integer primary key generated always as identity,
    s_symbol varchar(50) not null,
    s_ts timestamp default current_timestamp
);

CREATE TABLE Ref_Fund_Stock(
    r_id integer primary key generated always as identity,
    f_id integer constraint f_id_fk references Fund(f_id),
    s_id integer constraint s_id_fk references Stock(s_id)
);

-- A 基金有哪些股票
SELECT f.F_NAME, s.S_SYMBOL
FROM SSH.REF_FUND_STOCK r, SSH.FUND f, SSH.STOCK s
WHERE f.F_NAME = 'A' AND r.F_ID = f.F_ID AND r.S_ID = s.S_ID

-- 2498 在那些基金中
SELECT s.S_SYMBOL, f.F_NAME
FROM SSH.REF_FUND_STOCK r, SSH.FUND f, SSH.STOCK s
WHERE s.S_SYMBOL = '2498' AND r.F_ID = f.F_ID AND r.S_ID = s.S_ID

-- 一對一
CREATE TABLE student_public(
    s_id integer primary key generated always as identity,
    s_name varchar(50) not null,
    s_sex varchar(1) not null
);

CREATE TABLE student_private(
    s_id integer primary key constraint sp_id_fk references student_public(s_id),
    s_phone varchar(20) not null,
    s_grade varchar(1) not null
);
