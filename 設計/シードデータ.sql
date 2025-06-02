-- データベース作成
CREATE DATABASE dance_event_db;

-- データベース選択
\c dance_event_db; -- PostgreSQL

-- usersテーブル
CREATE TABLE users (
  id SERIAL PRIMARY KEY,
  username VARCHAR(30) NOT NULL UNIQUE,
  dancername VARCHAR(30) NOT NULL
);

-- eventsテーブル
CREATE TABLE events (
  id SERIAL PRIMARY KEY,
  name VARCHAR(30) NOT NULL,
  event_date TIMESTAMP,
  organizer_name VARCHAR(30),
  comment TEXT
);

-- entrylistテーブル
CREATE TABLE entrylist (
  event_id INTEGER,
  dancer_id INTEGER,
  points INTEGER DEFAULT 0,
  PRIMARY KEY (event_id, dancer_id),
  FOREIGN KEY (event_id) REFERENCES events(id),
  FOREIGN KEY (dancer_id) REFERENCES users(id)
);

-- users
INSERT INTO users (username, dancername) VALUES
  ('danceking', 'TARO'),
  ('groovemaster', 'HANAKO'),
  ('b-boy123', 'YUTA'),
  ('freestyler', 'MIKU'),
  ('lockmaster', 'KENTA');

-- events
INSERT INTO events (name, event_date, organizer_name, comment) VALUES
  ('ユーガットサーブド', '2025-07-15 18:00:00', 'DJ KEN', '夏の恒例ダンスバトル'),
  ('ステップアップ', '2025-12-10 19:30:00', 'MC RYO', '冬のビッグイベント'),
  ('ストレイト・アウト・コンプトン', '2025-04-20 17:00:00', 'DJ MIKI', '春の交流会イベント');

