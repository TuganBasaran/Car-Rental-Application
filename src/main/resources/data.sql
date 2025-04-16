-- Insert Car Types and Status
-- Car Types: Enum değerlerini açıklama için
-- INSERT INTO car_type VALUES ('ECONOMY'), ('SUV'), ('STANDARD'), ('LUXURY');

-- Insert initial data for Car
INSERT INTO car (barcode, license_plate, passenger_capacity, brand, model, mileage, transmission_type, daily_price, type, status) VALUES
                                                                                                                                      (1001, '34ABC123', 4, 'Toyota', 'Corolla', 25000, 'Automatic', 50.0, 'STANDARD', 'AVAILABLE'),
                                                                                                                                      (1002, '34DEF456', 7, 'Volkswagen', 'Tiguan', 30000, 'Manual', 80.0, 'SUV', 'RESERVED'),
                                                                                                                                      (1003, '35GHI789', 2, 'BMW', 'Z4', 15000, 'Automatic', 150.0, 'LUXURY', 'AVAILABLE');

-- Insert initial data for Location
INSERT INTO location (code, name, address) VALUES
                                               (5, 'Istanbul Airport', 'Havalimanı Caddesi, Istanbul'),
                                               (6, 'Acıbadem', 'Istanbul'),
                                               (7, 'Izmir City Center', 'Alsancak, Izmir');

-- Insert initial data for Member
INSERT INTO member (id, name, address, email, phone, driving_license_number) VALUES
                                                                                 (5, 'tugan', '123 Elm Street', 'tugan@example.com', '5551234567', 'DL123456'),
                                                                                 (6, 'Jane Smith', '456 Oak Street', 'janesmith@example.com', '5559876543', 'DL987654'),
                                                                                 (7, 'Alice Johnson', '789 Pine Street', 'alicej@example.com', '5555678901', 'DL567890');

-- Insert initial data for Equipment
INSERT INTO equipment (id, name, price) VALUES
                                            (5, 'Snow Tyres', 25.0),
                                            (6, 'Child Seat', 15.0),
                                            (7, 'GPS', 10.0);

-- Insert initial data for Services
INSERT INTO services (id, name, price) VALUES
                                           (5, 'Additional Driver', 30.0),
                                           (6, 'Towing Service', 50.0),
                                           (7, 'Roadside Assistance', 40.0);