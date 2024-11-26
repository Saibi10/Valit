USE [TMS]
GO
/****** Object:  Table [dbo].[Booking]    Script Date: 11/26/2024 6:07:51 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Booking](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[UserID] [int] NOT NULL,
	[TourID] [int] NOT NULL,
	[TransportProviderID] [int] NOT NULL,
	[BookingDate] [date] NULL,
	[Rating] [int] NULL,
	[Status] [varchar](20) NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CustomerCareMessage]    Script Date: 11/26/2024 6:07:51 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CustomerCareMessage](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[UserID] [int] NOT NULL,
	[Title] [nvarchar](255) NOT NULL,
	[Message] [nvarchar](max) NOT NULL,
	[Response] [nvarchar](max) NULL,
	[Status] [nvarchar](50) NULL,
	[DateTime] [datetime] NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Request]    Script Date: 11/26/2024 6:07:51 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Request](
	[RequestID] [int] IDENTITY(1,1) NOT NULL,
	[UserID] [int] NOT NULL,
	[Location] [nvarchar](255) NOT NULL,
	[Description] [nvarchar](max) NOT NULL,
	[Status] [nvarchar](50) NULL,
	[CreatedAt] [datetime] NULL,
	[Response] [nvarchar](max) NULL,
PRIMARY KEY CLUSTERED 
(
	[RequestID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Tour]    Script Date: 11/26/2024 6:07:51 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Tour](
	[TourID] [int] IDENTITY(1,1) NOT NULL,
	[TourName] [varchar](100) NOT NULL,
	[TourDescription] [nvarchar](max) NULL,
	[TourPrice] [decimal](10, 2) NOT NULL,
	[TransportID] [int] NULL,
	[StartDate] [date] NOT NULL,
	[Duration] [int] NOT NULL,
	[GoogleMapLink] [varchar](255) NULL,
PRIMARY KEY CLUSTERED 
(
	[TourID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TourImages]    Script Date: 11/26/2024 6:07:51 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TourImages](
	[ImageID] [int] IDENTITY(1,1) NOT NULL,
	[TourID] [int] NOT NULL,
	[ImagePath] [varchar](255) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[ImageID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TransportProvider]    Script Date: 11/26/2024 6:07:51 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TransportProvider](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[Name] [varchar](100) NOT NULL,
	[Rating] [decimal](3, 2) NULL,
	[FleetSize] [int] NOT NULL,
	[Contact] [varchar](100) NOT NULL,
	[VehicleTypes] [varchar](255) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Users]    Script Date: 11/26/2024 6:07:51 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Users](
	[UserID] [int] IDENTITY(1,1) NOT NULL,
	[Email] [varchar](100) NOT NULL,
	[FullName] [varchar](100) NOT NULL,
	[Password] [varchar](255) NOT NULL,
	[UserType] [varchar](50) NOT NULL,
	[WalletBalance] [decimal](18, 2) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[UserID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
UNIQUE NONCLUSTERED 
(
	[Email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[Booking] ADD  DEFAULT (getdate()) FOR [BookingDate]
GO
ALTER TABLE [dbo].[Booking] ADD  DEFAULT ('Pending') FOR [Status]
GO
ALTER TABLE [dbo].[CustomerCareMessage] ADD  DEFAULT ('Pending') FOR [Status]
GO
ALTER TABLE [dbo].[CustomerCareMessage] ADD  DEFAULT (getdate()) FOR [DateTime]
GO
ALTER TABLE [dbo].[Request] ADD  DEFAULT ('Pending') FOR [Status]
GO
ALTER TABLE [dbo].[Request] ADD  DEFAULT (getdate()) FOR [CreatedAt]
GO
ALTER TABLE [dbo].[TransportProvider] ADD  DEFAULT ((0)) FOR [FleetSize]
GO
ALTER TABLE [dbo].[TransportProvider] ADD  DEFAULT ('Unknown') FOR [Contact]
GO
ALTER TABLE [dbo].[TransportProvider] ADD  DEFAULT ('Unknown') FOR [VehicleTypes]
GO
ALTER TABLE [dbo].[Users] ADD  DEFAULT ('Customer') FOR [UserType]
GO
ALTER TABLE [dbo].[Users] ADD  DEFAULT ((0.00)) FOR [WalletBalance]
GO
ALTER TABLE [dbo].[Booking]  WITH CHECK ADD FOREIGN KEY([TourID])
REFERENCES [dbo].[Tour] ([TourID])
GO
ALTER TABLE [dbo].[Booking]  WITH CHECK ADD FOREIGN KEY([TransportProviderID])
REFERENCES [dbo].[TransportProvider] ([ID])
GO
ALTER TABLE [dbo].[Booking]  WITH CHECK ADD FOREIGN KEY([UserID])
REFERENCES [dbo].[Users] ([UserID])
GO
ALTER TABLE [dbo].[TourImages]  WITH CHECK ADD FOREIGN KEY([TourID])
REFERENCES [dbo].[Tour] ([TourID])
GO
ALTER TABLE [dbo].[Booking]  WITH CHECK ADD CHECK  (([Rating]>=(1) AND [Rating]<=(5)))
GO
ALTER TABLE [dbo].[TransportProvider]  WITH CHECK ADD CHECK  (([Rating]>=(0) AND [Rating]<=(5)))
GO
