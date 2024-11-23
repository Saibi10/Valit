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

SELECT TOP 5
t.TourName,
COUNT(b.ID) AS Bookings, 
AVG(b.Rating) AS AverageRating 
FROM Booking b 
JOIN Tour t ON b.TourID = t.TourID 
GROUP BY t.TourName 
ORDER BY Bookings DESC, AverageRating DESC