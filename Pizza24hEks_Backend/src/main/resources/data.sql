INSERT INTO station (station_id, latitude, longitude)
VALUES (1, 55.41, 12.34);
INSERT INTO station (station_id, latitude, longitude)
VALUES (2, 55.67, 12.52);
INSERT INTO station (station_id, latitude, longitude)
VALUES (3, 55.70, 12.53);

INSERT INTO pizza (pizza_id, price, title)
VALUES (1, 100, 'Margherita');
INSERT INTO pizza (pizza_id, price, title)
VALUES (2, 200, 'Pepperoni');
INSERT INTO pizza (pizza_id, price, title)
VALUES (3, 300, 'Hawaiian');
INSERT INTO pizza (pizza_id, price, title)
VALUES (4, 400, 'Meat Lovers');
INSERT INTO pizza (pizza_id, price, title)
VALUES (5, 500, 'Vegetarian');

/* SCRIPT DER IKKE SKAL BRUGERS BAGEFTER */
INSERT INTO drone (drone_id, serial_uuid, operation_status, station_station_id)
VALUES (1,'123e4567-e89b-12d3-a456-426614174000', '1', 1);
INSERT INTO drone (drone_id, serial_uuid, operation_status, station_station_id)
VALUES (2,'123e4567-e89b-12d3-a456-426614174000', '1', 2);

INSERT INTO delivery (delivery_id, actual_delivery_date, expected_delivery_date, adresse, drone_drone_id, pizza_pizza_id)
VALUES (1, null, '2025-01-15 14:00:00', '123 Pizza Street', 1, null);

INSERT INTO delivery (delivery_id, actual_delivery_date, expected_delivery_date, adresse, drone_drone_id, pizza_pizza_id)
VALUES (2, null, '2025-01-15 14:00:00', '123 Pizza Street', null, null);
