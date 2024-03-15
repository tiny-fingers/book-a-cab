CREATE TABLE Bookings
(
    id              INT AUTO_INCREMENT PRIMARY KEY,
    user_id         VARCHAR(255) NOT NULL,
    pickup_time     DATETIME,
    pickup_location VARCHAR(255),
    destination     VARCHAR(255),
    min_seats       INT,
    price           DECIMAL(10, 2)
);
