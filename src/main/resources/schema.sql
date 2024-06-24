-- 各種テーブル削除
DROP TABLE IF EXISTS customers;
DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS hotels;
DROP TABLE IF EXISTS plans cascade;
DROP TABLE IF EXISTS reservations cascade;

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
-- 予約ビュー
create view view_reservations as
select
   r.id,
   r.customer_id,
   r.plan_id,
   r.reservation_date,
   r.checkin_date,
   r.checkout_date,
   r.room_count,
   r.room_count*p.price as payment,
   p.name as plan_name
from
   reservations r
join plans p on r.plan_id = p.id