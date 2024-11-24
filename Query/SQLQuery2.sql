SELECT TOP 3 u.FullName, COUNT(b.ID) AS NumberOfBookings, SUM(t.TourPrice) AS TotalAmountPaid
FROM Booking b
JOIN Users u ON b.UserID = u.UserID
JOIN Tour t ON b.TourID = t.TourID
GROUP BY u.FullName
ORDER BY NumberOfBookings DESC, TotalAmountPaid DESC;

select * from Users

select * from Booking
	INSERT INTO Booking (UserID, TourID, TransportProviderID, BookingDate, Rating, Status)
	VALUES
	(1, 7, 1, '2024-12-11', 4, 'Pending'),
	(1, 9, 1, '2024-12-11', 4, 'Pending'),
	(1, 11, 1, '2024-12-11', 4, 'Pending'),
	(1, 3, 1, '2024-12-11', 4, 'Pending'),
	(1, 4, 1, '2024-12-11', 4, 'dadada')

	INSERT INTO Booking (UserID, TourID, TransportProviderID, BookingDate, Status)
	VALUES
	(1, 11, 1, '2024-12-11','Completed')

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