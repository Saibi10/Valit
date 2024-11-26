SELECT TOP 3 u.FullName, COUNT(b.ID) AS NumberOfBookings, SUM(t.TourPrice) AS TotalAmountPaid
FROM Booking b
JOIN Users u ON b.UserID = u.UserID
JOIN Tour t ON b.TourID = t.TourID
GROUP BY u.FullName
ORDER BY NumberOfBookings DESC, TotalAmountPaid DESC;

select * from Users

select * from Booking

select * from Tour

select * from TransportProvider

select * from TourImages

select * from Request

select * from CustomerCareMessage





SELECT TOP 5
t.TourName,
COUNT(b.ID) AS Bookings, 
AVG(b.Rating) AS AverageRating 
FROM Booking b 
JOIN Tour t ON b.TourID = t.TourID 
GROUP BY t.TourName 
ORDER BY Bookings DESC, AverageRating DESC
ORDER BY Bookings DESC, AverageRating DESC

SELECT r.RequestID, u.FullName AS CustomerName, r.Location, r.Description, 
                       r.Status, r.CreatedAt, r.Response
                       FROM Request r
                       JOIN Users u ON r.UserID = u.UserID

					   SELECT
    (SELECT COUNT(*) FROM Users) AS TotalUsers,
    (SELECT COUNT(*) FROM Booking) AS TotalBookings,
    (SELECT SUM(t.TourPrice) FROM Booking b
     JOIN Tour t ON b.TourID = t.TourID) AS TotalAmount,
    (SELECT COUNT(*) FROM Tour WHERE StartDate > GETDATE()) AS ActiveTours;

INSERT INTO [dbo].[Users] ( Email, FullName, Password, UserType, WalletBalance) VALUES
('user1@example.com', 'User 1', 'Password1', 'Customer', 1234.56),
('user2@example.com', 'User 2', 'Password2', 'Customer', 2345.67),
('user3@example.com', 'User 3', 'Password3', 'Customer', 3456.78),
('user4@example.com', 'User 4', 'Password4', 'Customer', 4567.89),
('user5@example.com', 'User 5', 'Password5', 'Customer', 5678.90),
('user6@example.com', 'User 6', 'Password6', 'Customer', 6789.01),
('user7@example.com', 'User 7', 'Password7', 'Customer', 7890.12),
('user8@example.com', 'User 8', 'Password8', 'Customer', 8901.23),
('user9@example.com', 'User 9', 'Password9', 'Customer', 9012.34),
('user10@example.com', 'User 10', 'Password10', 'Customer', 1234.45),
('hussnain@exmaple.com' , 'hussnain' , '123' , 'Customer' , 0);
INSERT INTO [dbo].[Users] ( Email, FullName, Password, UserType, WalletBalance) VALUES
('user11@example.com', 'User 11', 'Password11', 'Customer', 2997.84),
('user12@example.com', 'User 12', 'Password12', 'Customer', 3940.14),
('user13@example.com', 'User 13', 'Password13', 'Customer', 2.83),
('user14@example.com', 'User 14', 'Password14', 'Customer', 3944.17),
('user15@example.com', 'User 15', 'Password15', 'Customer', 4604.07),
('user16@example.com', 'User 16', 'Password16', 'Customer', 143.09),
('user17@example.com', 'User 17', 'Password17', 'Customer', 3444.28),
('user18@example.com', 'User 18', 'Password18', 'Customer', 2750.65),
( 'user19@example.com', 'User 19', 'Password19', 'Customer', 1845.94),
( 'user20@example.com', 'User 20', 'Password20', 'Customer', 3713.99),
( 'user21@example.com', 'User 21', 'Password21', 'Customer', 1589.77),
( 'user22@example.com', 'User 22', 'Password22', 'Customer', 4269.59),
( 'user23@example.com', 'User 23', 'Password23', 'Customer', 1877.03),
( 'user24@example.com', 'User 24', 'Password24', 'Customer', 2599.61),
( 'user25@example.com', 'User 25', 'Password25', 'Customer', 3480.73),
( 'user26@example.com', 'User 26', 'Password26', 'Customer', 3667.92),
( 'user27@example.com', 'User 27', 'Password27', 'Customer', 2439.56),
( 'user28@example.com', 'User 28', 'Password28', 'Customer', 3429.82),
( 'user29@example.com', 'User 29', 'Password29', 'Customer', 2407.97),
( 'user30@example.com', 'User 30', 'Password30', 'Customer', 1436.03),
( 'user31@example.com', 'User 31', 'Password31', 'Customer', 4994.27),
( 'user32@example.com', 'User 32', 'Password32', 'Customer', 3021.65),
( 'user33@example.com', 'User 33', 'Password33', 'Customer', 3688.85),
( 'user34@example.com', 'User 34', 'Password34', 'Customer', 3554.08),
( 'user35@example.com', 'User 35', 'Password35', 'Customer', 2815.46),
( 'user36@example.com', 'User 36', 'Password36', 'Customer', 460.11),
( 'user37@example.com', 'User 37', 'Password37', 'Customer', 4249.23),
( 'user38@example.com', 'User 38', 'Password38', 'Customer', 3661.18),
( 'user39@example.com', 'User 39', 'Password39', 'Customer', 1865.74),
( 'user40@example.com', 'User 40', 'Password40', 'Customer', 2371.02),
( 'user41@example.com', 'User 41', 'Password41', 'Customer', 1999.82),
( 'user42@example.com', 'User 42', 'Password42', 'Customer', 1726.90),
( 'user43@example.com', 'User 43', 'Password43', 'Customer', 3306.46),
( 'user44@example.com', 'User 44', 'Password44', 'Customer', 3688.90),
( 'user45@example.com', 'User 45', 'Password45', 'Customer', 4845.31),
( 'user46@example.com', 'User 46', 'Password46', 'Customer', 2856.34),
( 'user47@example.com', 'User 47', 'Password47', 'Customer', 1221.11),
( 'user48@example.com', 'User 48', 'Password48', 'Customer', 2533.29),
( 'user49@example.com', 'User 49', 'Password49', 'Customer', 4865.59),
( 'user50@example.com', 'User 50', 'Password50', 'Customer', 3988.75),
( 'user51@example.com', 'User 51', 'Password51', 'Customer', 4912.18),
( 'user52@example.com', 'User 52', 'Password52', 'Customer', 1223.89),
( 'user53@example.com', 'User 53', 'Password53', 'Customer', 2355.62),
( 'user54@example.com', 'User 54', 'Password54', 'Customer', 3529.89),
( 'user55@example.com', 'User 55', 'Password55', 'Customer', 4811.16),
( 'user56@example.com', 'User 56', 'Password56', 'Customer', 1900.67),
( 'user57@example.com', 'User 57', 'Password57', 'Customer', 4715.04),
( 'user58@example.com', 'User 58', 'Password58', 'Customer', 1928.11),
( 'user59@example.com', 'User 59', 'Password59', 'Customer', 3065.19),
( 'user60@example.com', 'User 60', 'Password60', 'Customer', 2851.76);


INSERT INTO [dbo].[Tour] ( TourName, TourDescription, TourPrice, TransportID, StartDate, Duration, GoogleMapLink) VALUES
('Visit Hunza Valley', 'Amazing trip to Hunza Valley', 15000.00, 1, '2023-12-10', 7, 'http://maps.google.com/hunzavalley'),
('Visit Swat Valley', 'Amazing trip to Swat Valley', 18000.00, 2, '2024-01-15', 5, 'http://maps.google.com/swatvalley'),
( 'Visit Murree', 'Amazing trip to Murree', 12000.00, 3, '2024-02-20', 3, 'http://maps.google.com/murree'),
('Visit Skardu', 'Amazing trip to Skardu', 25000.00, 4, '2024-03-25', 10, 'http://maps.google.com/skardu'),
('Visit Naran', 'Amazing trip to Naran', 20000.00, 5, '2024-05-05', 6, 'http://maps.google.com/naran'),
( 'Visit Kaghan', 'Amazing trip to Kaghan', 22000.00, 1, '2023-08-12', 5, 'http://maps.google.com/kaghan'),
( 'Visit Fairy Meadows', 'Amazing trip to Fairy Meadows', 30000.00, 2, '2023-09-01', 8, 'http://maps.google.com/fairymeadows'),
( 'Visit Deosai Plains', 'Amazing trip to Deosai Plains', 40000.00, 3, '2023-10-15', 7, 'http://maps.google.com/deosaiplains'),
( 'Visit Shogran', 'Amazing trip to Shogran', 15000.00, 4, '2024-06-01', 3, 'http://maps.google.com/shogran'),
( 'Visit Ratti Gali Lake', 'Amazing trip to Ratti Gali Lake', 25000.00, 5, '2024-07-10', 4, 'http://maps.google.com/rattigalilake');


delete from TransportProvider where ID > 10



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


INSERT INTO [dbo].[Booking] ( UserID, TourID, TransportProviderID, BookingDate, Rating, Status) VALUES
( 1, 1, 1, '2023-11-01', 5, 'Completed'),
( 2, 2, 2, '2024-01-01', NULL, 'Pending'),
( 3, 3, 3, '2024-02-10', NULL, 'Confirmed'),
( 4, 4, 4, '2023-12-01', 4, 'Completed'),
( 5, 5, 5, '2024-05-01', NULL, 'Cancelled'),
( 6, 6, 1, '2023-08-01', 5, 'Completed'),
( 7, 7, 2, '2023-09-01', NULL, 'Cancelled'),
( 8, 8, 3, '2023-10-01', NULL, 'Pending'),
( 9, 9, 4, '2024-06-01', 3, 'Confirmed'),
( 10, 10, 5, '2024-07-01', 4, 'Completed');


INSERT INTO [dbo].[CustomerCareMessage] ( UserID, Title, Message, Response, Status, DateTime) VALUES
( 1, 'Issue with booking', 'Details of the issue...', 'Your issue has been resolved.', 'Replied', '2024-01-15'),
( 2, 'Payment not processed', 'Details of the payment issue...', NULL, 'Pending', '2024-02-20'),
(3, 'Need more details on tour', 'Details...', 'Please check our website.', 'Replied', '2024-03-10'),
( 4, 'Booking canceled by mistake', 'Details...', NULL, 'Pending', '2024-05-25'),
( 5, 'Suggestion for improvement', 'Details...', 'Thank you for your feedback.', 'Replied', '2024-06-15');


INSERT INTO [dbo].[CustomerCareMessage] ( UserID, Title, Message, Response, Status, DateTime) VALUES
( 1, 'Issue with booking', 'Details of the issue...', 'Your issue has been resolved.', 'Replied', '2024-01-15'),
( 2, 'Payment not processed', 'Details of the payment issue...', NULL, 'Pending', '2024-02-20'),
( 3, 'Need more details on tour', 'Details...', 'Please check our website.', 'Replied', '2024-03-10'),
( 4, 'Booking canceled by mistake', 'Details...', NULL, 'Pending', '2024-05-25'),
( 5, 'Suggestion for improvement', 'Details...', 'Thank you for your feedback.', 'Replied', '2024-06-15');

INSERT INTO Tour (TourName, TourDescription, TourPrice, TransportID, StartDate, Duration, GoogleMapLink)
VALUES
('City Explorer', 
'Discover the vibrant city life with guided tours of historic landmarks, modern architecture, bustling markets, and cultural hotspots. Ideal for travelers who want to soak in the essence of urban living.', 
2000.00, 1, '2024-12-01', 3, 'https://goo.gl/maps/city1'),

('Mountain Adventure', 
'Experience the thrill of mountain hiking and camping. Trek through scenic trails, enjoy breathtaking views, and connect with nature. Perfect for adventure seekers looking for a challenge.', 
3000.00, 2, '2024-12-10', 5, 'https://goo.gl/maps/mountain1'),

('Beach Retreat', 
'Unwind on pristine sandy beaches with crystal-clear waters. Indulge in water sports, sunset views, and beachside dining. A rejuvenating getaway for relaxation and fun.', 
4000.00, 3, '2024-12-15', 7, 'https://goo.gl/maps/beach1'),

('Cultural Tour', 
'Immerse yourself in local traditions, festivals, and historical sites. Learn about the region’s rich heritage through art, music, and storytelling. A must-do for culture enthusiasts.', 
1500.00, 4, '2024-12-20', 2, 'https://goo.gl/maps/culture1'),

('Wildlife Safari', 
'Embark on an exciting safari to witness exotic wildlife in their natural habitats. Enjoy guided tours, photography opportunities, and a chance to connect with nature.', 
3500.00, 5, '2024-12-25', 4, 'https://goo.gl/maps/safari1'),

('Historical Journey', 
'Travel back in time by visiting iconic historical landmarks and ancient ruins. Gain insight into the past with expert guides and immersive experiences. Ideal for history buffs.', 
2500.00, 1, '2025-01-01', 3, 'https://goo.gl/maps/history1'),

('Foodie Adventure', 
'Embark on a culinary journey to explore the region’s finest flavors. Enjoy food tours, cooking classes, and tastings of local delicacies. Perfect for food lovers and explorers.', 
1800.00, 2, '2025-01-05', 2, 'https://goo.gl/maps/food1'),

('Luxury Cruise', 
'Set sail on a premium cruise offering world-class amenities, gourmet dining, entertainment, and spectacular ocean views. The ultimate indulgence for travelers seeking luxury and adventure.', 
10000.00, 3, '2025-01-10', 10, 'https://goo.gl/maps/cruise1'),

('Desert Safari', 
'Explore the majestic beauty of vast deserts with thrilling dune bashing, camel rides, and traditional Bedouin camps. A unique blend of adventure and cultural immersion.', 
2200.00, 4, '2025-01-15', 3, 'https://goo.gl/maps/desert1'),

('Rainforest Expedition', 
'Discover the lush rainforests with guided nature walks, wildlife spotting, and serene waterfalls. A paradise for nature lovers and adventure enthusiasts looking for tranquility and excitement.', 
4500.00, 5, '2025-01-20', 6, 'https://goo.gl/maps/rainforest1');