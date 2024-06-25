INSERT INTO customers (name, postal, address, tel, email, birthday, register_date, withdraw_date, password)
VALUES
('田中太郎', '156-9999', '東京都港区1-1-1', '000-0000-0000', 'nn@gmail.com', '1991-08-15', '2020-08-09', null, 'stgadbfvefva'),
('山田花子', '845-9999', '北海道札幌市2-2-2', '4555-1035-9166', 'tt@outlook.jp', '1991-08-16', '2020-08-10', null, 'vdsfbaf'),
('佐藤次郎', '120-5548', '東京都港区3-3-3', '155-946-5985', 'ww@nomura.co.jp', '1991-08-17', '2020-08-11', null, 'fvdfavdf');


INSERT INTO categories (name)
VALUES
('シティホテル'),
('リゾートホテル'),
('ビジネスホテル'),
('旅館'),
('民泊'),
('ペンション');

INSERT INTO hotels (category_id, name, address, checkin_time, checkout_time)
VALUES
(1, 'シティホテルA', '東京都港区六本木1-1-1', '15:00', '10:00'),
(2, 'リゾートホテルB', '沖縄県恩納村字恩納123-45', '16:00', '10:00'),
(3, 'ビジネスホテルC', '東京都千代田区丸の内2-2-2', '17:00', '10:00'),
(4, '旅館D', '京都府京都市左京区祇園町1-1', '13:00', '10:00'),
(5, '民泊E', '北海道札幌市中央区北3条西4丁目', '14:00', '10:00'),
(6, 'ペンションF', '長野県軽井沢町南町1-1-1', '15:00', '10:00'),
(1, 'シティホテルG', '大阪府大阪市北区梅田5-5-5', '16:00', '10:00'),
(2, 'リゾートホテルH', '神奈川県鎌倉市極楽寺1-1', '17:00', '10:00'),
(3, 'ビジネスホテルI', '名古屋市中区栄3-3-3', '16:00', '10:00'),
(4, '旅館J', '京都府京都市北区上賀茂町2-2', '16:00', '10:00'),
(1,'シティホテルK','東京都新宿区新宿5-5-5','18:00','12:00'),
(1,'シティホテルL','東京都渋谷区道玄坂5-5-5','16:00','10:00'),
(3, 'ビジネスホテルM', '東京都豊島区池袋2-2-2', '17:00', '10:00'),
(1,'シティホテルN','東京都江東区豊洲2-2-2','15:00','10:00'),
(1,'シティホテルO','神奈川県横浜市西区高島1-1-1','15:00','10:00'),
(1,'シティホテルP','神奈川県横浜市港北区篠原町1-1-1','15:00','10:00');

INSERT INTO plans (hotel_id, name, price, room_count, note)
VALUES
(1, '素泊まり', 10000, 5, ''),
(1, '夕朝食付き', 15000, 3, ''),
(1, '朝食付き', 13000, 1, ''),
(2, '豪華バイキング', 20000, 4, ''),
(2, '誕生日', 30000, 4, ''),
(2, '素泊まり', 8000, 3, ''),
(3, '夕朝食付き', 15000, 7, ''),
(4, '朝食付き', 13000, 9, ''),
(5, '東京タワー眺望', 50000, 6, ''),
(6, '素泊まり', 6000, 9, ''),
(7, '夕朝食付き', 17000, 1, ''),
(8, '朝食付き', 8000, 2, ''),
(9, '部屋食', 10000, 1, ''),
(10, '素泊まり', 5000, 6, ''),
(11,'素泊まり',15000,10,''),
(12,'素泊まり',10000,10,''),
(13,'朝食付き',10000,20,''),
(14,'朝食付き',20000,8,''),
(15,'朝食付き',30000,5,''),
(16,'朝食付き',30000,5,'');


INSERT INTO reservations (customer_id, plan_id, reservation_date, checkin_date, checkout_date, room_count, is_canceled, note)
VALUES
(1, 1, '2024-06-20', '2024-07-05', '2024-07-07', 1, FALSE, ''),
(2, 4, '2024-07-05', '2024-07-20', '2024-07-22', 1, TRUE, ''),
(2, 3, '2024-07-20', '2024-08-04', '2024-08-06', 1, FALSE, ''),
(3, 1, '2024-08-04', '2024-08-19', '2024-08-21', 1, FALSE, '');


INSERT INTO reviews (customer_id, hotel_id, plan_id, user_age, stay_month, stay_days, point, review)
VALUES
(1, 1, 2, 50, 5, 2, 4, '設備がきれいで、スタッフの対応も良かったです。'),
(2, 1, 3, 20, 10, 1, 2, '紅葉がきれいだと聞いていたのに、まだ早かった。'),
(2, 2, 4, 70, 8,  1, 5, '素晴らしい。また来たい。'),
(3, 2, 6, 20, 12, 3, 5, '次回もまたこちらに伺いたいです。個人的NO１！'),
(1, 3, 7, 30, 2, 1, 1, '臭かった');