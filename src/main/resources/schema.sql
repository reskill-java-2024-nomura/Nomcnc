-- 各種テーブル削除
DROP TABLE IF EXISTS customers;
DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS hotels;
DROP TABLE IF EXISTS plans;
DROP TABLE IF EXISTS reservations;

-- 会員テーブル
CREATE TABLE customers
(
id SERIAL PRIMARY KEY,
name TEXT,
postal TEXT,
address TEXT,
tel TEXT,
email TEXT,
birthday DATE,
register_date DATE,
withdraw_date DATE,
password TEXT
);

-- カテゴリーテーブル
CREATE TABLE categories
(
id SERIAL PRIMARY KEY,
name TEXT
);

-- 宿テーブル
CREATE TABLE hotels
(
id SERIAL PRIMARY KEY,
category_id INTEGER,
name TEXT,
address TEXT,
checkin_time TIME,
checkout_time TIME
);

-- プランテーブル
CREATE TABLE plans
(
id SERIAL PRIMARY KEY,
hotel_id INTEGER,
name TEXT,
price INTEGER,
room_count INTEGER,
note TEXT
);
-- 予約テーブル
CREATE TABLE reservations
(
id SERIAL PRIMARY KEY,
customer_id INTEGER,
plan_id INTEGER,
reservation_date DATE,
checkin_date DATE,
checkout_date DATE,
room_count INTEGER,
is_canceled BOOLEAN,
note TEXT
);
