CREATE TABLE movie (
  movie_id BIGINT PRIMARY KEY,
  title VARCHAR(100),
  language VARCHAR(50),
  genre VARCHAR(50),
  duration INT
);

CREATE TABLE theatre (
  theatre_id BIGINT PRIMARY KEY,
  name VARCHAR(100),
  city VARCHAR(50),
  partner_id BIGINT
);

CREATE TABLE screen (
  screen_id BIGINT PRIMARY KEY,
  theatre_id BIGINT,
  capacity INT
);

CREATE TABLE show (
  show_id BIGINT PRIMARY KEY,
  movie_id BIGINT,
  screen_id BIGINT,
  show_time TIME,
  show_date DATE
);

CREATE TABLE seat (
  seat_id VARCHAR(10),
  screen_id BIGINT,
  seat_type VARCHAR(20),
  PRIMARY KEY (seat_id, screen_id)
);

CREATE TABLE seat_inventory (
  show_id BIGINT,
  seat_id VARCHAR(10),
  status VARCHAR(20),
  PRIMARY KEY (show_id, seat_id)
);

CREATE TABLE booking (
  booking_id BIGINT PRIMARY KEY,
  user_id BIGINT,
  show_id BIGINT,
  status VARCHAR(20),
  total_amount DECIMAL(10,2)
);


/*Transaction Choice
-- Booking + seat update in same DB transaction
-- Seat locks handled outside DB (Redis) */