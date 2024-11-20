SELECT TOP 3 u.FullName, COUNT(b.ID) AS NumberOfBookings, SUM(t.TourPrice) AS TotalAmountPaid
FROM Booking b
JOIN Users u ON b.UserID = u.UserID
JOIN Tour t ON b.TourID = t.TourID
GROUP BY u.FullName
ORDER BY NumberOfBookings DESC, TotalAmountPaid DESC;


select * from Booking

select * from Tour

select * from TransportProvider