CREATE TABLE customer
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE machine
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    license     UUID,
    customer_id INT,
    device_id   INT,
    CONSTRAINT fk_customer FOREIGN KEY (customer_id) REFERENCES customer (id) ON DELETE CASCADE,
    CONSTRAINT fk_device FOREIGN KEY (device_id) REFERENCES device (id) ON DELETE CASCADE
);

CREATE TABLE device
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

-- Insert test data
INSERT INTO customer (name)
VALUES ('Richard'),
       ('Michael'),
       ('Denial');

-- 5. Insert example devices
INSERT INTO device (name)
VALUES ('Device_1'),
       ('Device_2'),
       ('Device_3');

-- 6. Insert example machines and associate them with customers and devices
INSERT INTO machine (name, customer_id, device_id, license)
VALUES ('Machine_1', 1, 1, uuid_generate_v4()),
       ('Machine_2', 1, 2, uuid_generate_v4()),
       ('Machine_3', 2, 3, uuid_generate_v4()),
       ('Machine_4', 3, 1, uuid_generate_v4());


