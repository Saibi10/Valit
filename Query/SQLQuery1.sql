CREATE DATABASE TMS;

CREATE TABLE Users (
    UserID INT identity(1,1) PRIMARY KEY,
    Email VARCHAR(100) UNIQUE NOT NULL,    
    FullName VARCHAR(100) NOT NULL,    
    Password VARCHAR(255) NOT NULL        
);

ALTER TABLE Users
ADD UserType VARCHAR(50) NOT NULL DEFAULT 'Customer';

CREATE TABLE Tour (
    TourID INT IDENTITY(1,1) PRIMARY KEY, -- Unique tour identifier (auto-incrementing)
    TourName VARCHAR(100) NOT NULL,       -- Name of the tour
    TourDescription TEXT,                 -- Description of the tour
    TourPrice DECIMAL(10, 2) NOT NULL,    -- Price of the tour
    TransportID INT,                      -- Reference to a transport method
    StartDate DATE NOT NULL,              -- Start date of the tour
    Duration INT NOT NULL,                -- Duration in days
    GoogleMapLink VARCHAR(255)            -- Link to the tour's location on Google Maps
);

CREATE TABLE TransportProvider (
    ID INT IDENTITY(1,1) PRIMARY KEY,       -- Unique identifier for the transport provider
    Name VARCHAR(100) NOT NULL,             -- Name of the transport provider
    TransportType VARCHAR(50) NOT NULL,     -- Type of transport (e.g., Bus, Train, Flight)
    Rating DECIMAL(3, 2) CHECK (Rating BETWEEN 0 AND 5) -- Rating between 0 and 5, with 2 decimal places
);

CREATE TABLE Booking (
    ID INT IDENTITY(1,1) PRIMARY KEY,                   -- Unique booking identifier
    UserID INT NOT NULL,                                -- Reference to the Users table
    TourID INT NOT NULL,                                -- Reference to the Tour table
    TransportProviderID INT NOT NULL,                  -- Reference to the TransportProvider table
    BookingDate DATE DEFAULT CURRENT_TIMESTAMP,        -- Date of booking (optional, defaults to current date)
    FOREIGN KEY (UserID) REFERENCES Users(UserID),     -- Foreign key to Users table
    FOREIGN KEY (TourID) REFERENCES Tour(TourID),      -- Foreign key to Tour table
    FOREIGN KEY (TransportProviderID) REFERENCES TransportProvider(ID) -- Foreign key to TransportProvider table
);
ALTER TABLE Booking
ADD 
    Rating INT CHECK (Rating BETWEEN 0 AND 5),    -- Rating column (values between 1 and 5)
    Status VARCHAR(20) DEFAULT 'Pending';         -- Status column with a default value of 'Pending'


CREATE TABLE TourImages (
    ImageID INT IDENTITY(1,1) PRIMARY KEY, -- Unique identifier for each image
    TourID INT NOT NULL,                   -- Foreign key referencing the Tour table
    ImagePath VARCHAR(255) NOT NULL,       -- Path or URL to the image
    FOREIGN KEY (TourID) REFERENCES Tour(TourID) -- Foreign key relationship
)



INSERT INTO Users (Email, FullName, Password, UserType)
VALUES
('user1@example.com', 'Alice Johnson', 'password_hash1', 'Customer'),
('user2@example.com', 'Bob Smith', 'password_hash2', 'Customer'),
('user3@example.com', 'Charlie Brown', 'password_hash3', 'Admin'),
('user4@example.com', 'Daisy White', 'password_hash4', 'Customer'),
('user5@example.com', 'Evan Green', 'password_hash5', 'Manager'),
('user6@example.com', 'Fiona Black', 'password_hash6', 'Customer'),
('user7@example.com', 'George Blue', 'password_hash7', 'Customer'),
('user8@example.com', 'Hannah Pink', 'password_hash8', 'Customer'),
('user9@example.com', 'Ian Purple', 'password_hash9', 'Customer'),
('user10@example.com', 'Jenna Gold', 'password_hash10', 'Customer');

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


INSERT INTO TransportProvider (Name, TransportType, Rating)
VALUES
('City Bus Co.', 'Bus', 4.5),
('Express Rail', 'Train', 4.8),
('Skyways', 'Flight', 4.9),
('Luxury Limo Service', 'Taxi', 4.2),
('Riverboat Rides', 'Boat', 4.7),
('Adventure Rentals', 'Car', 4.3),
('Cycle Adventures', 'Bike', 4.6),
('Metro Rail Services', 'Train', 4.4),
('Air Comforts', 'Flight', 4.8),
('Eco Bus Tours', 'Bus', 4.3);


INSERT INTO Booking (UserID, TourID, TransportProviderID, BookingDate)
VALUES
(1, 1, 1, '2024-12-01'),
(2, 2, 2, '2024-12-02'),
(3, 3, 3, '2024-12-03'),
(4, 4, 4, '2024-12-04'),
(5, 5, 5, '2024-12-05'),
(6, 6, 1, '2024-12-06'),
(7, 7, 2, '2024-12-07'),
(8, 8, 3, '2024-12-08'),
(9, 9, 4, '2024-12-09'),
(10, 10, 5, '2024-12-10');

UPDATE Booking
SET 
    Rating = 5,          -- Example rating (5)
    Status = 'Completed' -- Example status (Completed)
WHERE ID = 1;

UPDATE Booking
SET 
         -- Example rating (4)
    Status = 'Pending'   -- Example status (Pending)
WHERE ID = 2;

UPDATE Booking
SET 
    Rating = 3,          -- Example rating (3)
    Status = 'Completed' -- Example status (Completed)
WHERE ID = 3;

UPDATE Booking
SET 
         -- Example rating (5)
    Status = 'Pending'   -- Example status (Pending)
WHERE ID = 4;

UPDATE Booking
SET 
    Rating = 2,          -- Example rating (2)
    Status = 'Completed' -- Example status (Completed)
WHERE ID = 5;

UPDATE Booking
SET 
         -- Example rating (4)
    Status = 'Pending'   -- Example status (Pending)
WHERE ID = 6;

UPDATE Booking
SET 
    Rating = 3,          -- Example rating (3)
    Status = 'Completed' -- Example status (Completed)
WHERE ID = 7;

UPDATE Booking
SET 
         -- Example rating (5)
    Status = 'Pending'   -- Example status (Pending)
WHERE ID = 8;

UPDATE Booking
SET 
    Rating = 4,          -- Example rating (4)
    Status = 'Completed' -- Example status (Completed)
WHERE ID = 9;

UPDATE Booking
SET         -- Example rating (2)
    Status = 'Pending'   -- Example status (Pending)
WHERE ID = 10;

