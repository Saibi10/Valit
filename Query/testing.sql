select * from Users

select * from Booking

select * from Tour

select * from TransportProvider

select * from TourImages

select * from Request

select * from CustomerCareMessage

INSERT INTO Users (Email, FullName, Password, UserType , WalletBalance)
VALUES
('hussnain@exmaple.com', 'hussnain', '123', 'Admin' , 0),
('vertigo@exmaple.com', 'vertigo', '123', 'Customer' , 0),
('cyber@exmaple.com' , 'cyber' , '123' , 'Customer' , 0),
('hanz@example.com' , 'hanz' , '123' , 'Customer' , 0)

INSERT INTO Tour (TourName, TourDescription, TourPrice, TransportID, StartDate, Duration, GoogleMapLink)
VALUES
('Expired', 
'Discover the vibrant city life with guided tours of historic landmarks, modern architecture, bustling markets, and cultural hotspots. Ideal for travelers who want to soak in the essence of urban living.', 
200.00, 3, '2024-11-18', 3, 'https://goo.gl/maps/city2')

INSERT INTO Tour (TourName, TourDescription, TourPrice, TransportID, StartDate, Duration, GoogleMapLink)
VALUES
('City Explorer', 
'Discover the vibrant city life with guided tours of historic landmarks, modern architecture, bustling markets, and cultural hotspots. Ideal for travelers who want to soak in the essence of urban living.', 
200.00, 1, '2024-12-01', 3, 'https://goo.gl/maps/city1'),


('Mountain Adventure', 
'Experience the thrill of mountain hiking and camping. Trek through scenic trails, enjoy breathtaking views, and connect with nature. Perfect for adventure seekers looking for a challenge.', 
300.00, 2, '2024-12-10', 5, 'https://goo.gl/maps/mountain1'),

('Beach Retreat', 
'Unwind on pristine sandy beaches with crystal-clear waters. Indulge in water sports, sunset views, and beachside dining. A rejuvenating getaway for relaxation and fun.', 
400.00, 3, '2024-12-15', 7, 'https://goo.gl/maps/beach1'),

('Cultural Tour', 
'Immerse yourself in local traditions, festivals, and historical sites. Learn about the region’s rich heritage through art, music, and storytelling. A must-do for culture enthusiasts.', 
150.00, 4, '2024-12-20', 2, 'https://goo.gl/maps/culture1'),

('Wildlife Safari', 
'Embark on an exciting safari to witness exotic wildlife in their natural habitats. Enjoy guided tours, photography opportunities, and a chance to connect with nature.', 
350.00, 5, '2024-12-25', 4, 'https://goo.gl/maps/safari1'),

('Historical Journey', 
'Travel back in time by visiting iconic historical landmarks and ancient ruins. Gain insight into the past with expert guides and immersive experiences. Ideal for history buffs.', 
250.00, 1, '2025-01-01', 3, 'https://goo.gl/maps/history1'),

('Foodie Adventure', 
'Embark on a culinary journey to explore the region’s finest flavors. Enjoy food tours, cooking classes, and tastings of local delicacies. Perfect for food lovers and explorers.', 
180.00, 2, '2025-01-05', 2, 'https://goo.gl/maps/food1'),

('Luxury Cruise', 
'Set sail on a premium cruise offering world-class amenities, gourmet dining, entertainment, and spectacular ocean views. The ultimate indulgence for travelers seeking luxury and adventure.', 
1000.00, 3, '2025-01-10', 10, 'https://goo.gl/maps/cruise1'),

('Desert Safari', 
'Explore the majestic beauty of vast deserts with thrilling dune bashing, camel rides, and traditional Bedouin camps. A unique blend of adventure and cultural immersion.', 
220.00, 4, '2025-01-15', 3, 'https://goo.gl/maps/desert1'),

('Rainforest Expedition', 
'Discover the lush rainforests with guided nature walks, wildlife spotting, and serene waterfalls. A paradise for nature lovers and adventure enthusiasts looking for tranquility and excitement.', 
450.00, 5, '2025-01-20', 6, 'https://goo.gl/maps/rainforest1');


INSERT INTO [dbo].[TransportProvider] ( Name, Rating, FleetSize, Contact, VehicleTypes) VALUES
('Pak Travels', 4.5, 20, 'contact@paktravels.com', 'Bus, Van, Car'),
('Luxury Transport', 4.8, 15, 'luxury@transport.com', 'Car, SUV'),
('Skyline Tours', 4.2, 25, 'skyline@tours.com', 'Bus, Van'),
( 'Greenline Services', 4.0, 18, 'greenline@services.com', 'Bus, Car'),
( 'Mountain Adventures', 4.7, 10, 'adventures@mountain.com', 'SUV, Jeep'),
( 'Desert Rides', 4.1, 12, 'desert@rides.com', 'Van, Jeep'),
( 'Northern Express', 4.4, 22, 'northern@express.com', 'Bus, Van, Car'),
( 'Travel Ace', 4.6, 30, 'contact@travelace.com', 'Bus, Car, SUV'),
( 'Comfort Travels', 4.3, 16, 'comfort@travels.com', 'Car, SUV, Jeep'),
( 'Himalaya Roads', 4.9, 8, 'himalaya@roads.com', 'Jeep, SUV');