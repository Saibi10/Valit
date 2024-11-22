SELECT TOP 3 u.FullName, COUNT(b.ID) AS NumberOfBookings, SUM(t.TourPrice) AS TotalAmountPaid
FROM Booking b
JOIN Users u ON b.UserID = u.UserID
JOIN Tour t ON b.TourID = t.TourID
GROUP BY u.FullName
ORDER BY NumberOfBookings DESC, TotalAmountPaid DESC;


	

select * from Tour

select * from Users

select * from TransportProvider

select * from TourImages	

INSERT INTO Booking (UserID, TourID, TransportProviderID, BookingDate, Rating, Status)
VALUES
(1, 7, 4, '2024-12-11', 4, 'Pending')