USE [master]
GO
/****** Object:  Database [SalaryDB]    Script Date: 12/10/2022 8:03:09 PM ******/
CREATE DATABASE [SalaryDB]
GO
ALTER DATABASE [SalaryDB] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [SalaryDB].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [SalaryDB] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [SalaryDB] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [SalaryDB] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [SalaryDB] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [SalaryDB] SET ARITHABORT OFF 
GO
ALTER DATABASE [SalaryDB] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [SalaryDB] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [SalaryDB] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [SalaryDB] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [SalaryDB] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [SalaryDB] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [SalaryDB] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [SalaryDB] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [SalaryDB] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [SalaryDB] SET  DISABLE_BROKER 
GO
ALTER DATABASE [SalaryDB] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [SalaryDB] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [SalaryDB] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [SalaryDB] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [SalaryDB] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [SalaryDB] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [SalaryDB] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [SalaryDB] SET RECOVERY FULL 
GO
ALTER DATABASE [SalaryDB] SET  MULTI_USER 
GO
ALTER DATABASE [SalaryDB] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [SalaryDB] SET DB_CHAINING OFF 
GO
ALTER DATABASE [SalaryDB] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [SalaryDB] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [SalaryDB] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [SalaryDB] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
EXEC sys.sp_db_vardecimal_storage_format N'SalaryDB', N'ON'
GO
ALTER DATABASE [SalaryDB] SET QUERY_STORE = OFF
GO
USE [SalaryDB]
GO
/****** Object:  Table [dbo].[Administrative_Staff]    Script Date: 12/10/2022 8:03:09 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Administrative_Staff](
	[employeeId] [varchar](50) NOT NULL,
	[basicSalary] [money] NOT NULL,
 CONSTRAINT [PK_Administrative_Staff] PRIMARY KEY CLUSTERED 
(
	[employeeId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Assignment]    Script Date: 12/10/2022 8:03:09 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Assignment](
	[id] [varchar](50) NOT NULL,
	[workerId] [varchar](50) NOT NULL,
	[finishAmount] [int] NOT NULL,
	[productionStageId] [varchar](50) NOT NULL,
 CONSTRAINT [PK_Assignment] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Department]    Script Date: 12/10/2022 8:03:09 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Department](
	[id] [varchar](50) NOT NULL,
	[name] [nvarchar](100) NOT NULL,
 CONSTRAINT [PK_Department] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Employee]    Script Date: 12/10/2022 8:03:09 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Employee](
	[id] [varchar](50) NOT NULL,
	[fullName] [nvarchar](100) NOT NULL,
	[identifyCard] [nvarchar](20) NOT NULL,
	[dateOfBirth] [date] NOT NULL,
	[gender] [nvarchar](10) NOT NULL,
	[salary] [money] NOT NULL,
	[allowance] [money] NOT NULL,
	[phoneNumber] [varchar](10) NOT NULL,
	[address] [nvarchar](200) NOT NULL,
	[departmentId] [varchar](50) NOT NULL,
 CONSTRAINT [PK_Employee] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Product]    Script Date: 12/10/2022 8:03:09 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Product](
	[id] [varchar](50) NOT NULL,
	[name] [nvarchar](100) NOT NULL,
	[style] [nvarchar](100) NOT NULL,
	[material] [nvarchar](100) NOT NULL,
	[amount] [int] NOT NULL,
 CONSTRAINT [PK_Product] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Production_Stage]    Script Date: 12/10/2022 8:03:09 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Production_Stage](
	[id] [varchar](50) NOT NULL,
	[name] [nvarchar](100) NOT NULL,
	[price] [money] NOT NULL,
	[productId] [varchar](50) NOT NULL,
	[amount] [int] NOT NULL,
	[productionStageId] [varchar](50) NULL,
 CONSTRAINT [PK_Production_Stage] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ShiftWork]    Script Date: 12/10/2022 8:03:09 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ShiftWork](
	[id] [varchar](50) NOT NULL,
	[name] [nvarchar](100) NOT NULL,
	[startTime] [datetime] NOT NULL,
	[endTime] [datetime] NOT NULL,
	[salary] [money] NOT NULL,
 CONSTRAINT [PK_ShiftWork] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TimeKeeping]    Script Date: 12/10/2022 8:03:09 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TimeKeeping](
	[id] [varchar](50) NOT NULL,
	[employeeId] [varchar](50) NOT NULL,
	[createdDate] [date] NOT NULL,
	[shiftWorkId] [varchar](50) NOT NULL,
	[status] [nvarchar](50) NOT NULL,
	[leavePermission] [bit] NOT NULL,
 CONSTRAINT [PK_TimeKeeping] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TimeKeepingStaff]    Script Date: 12/10/2022 8:03:09 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TimeKeepingStaff](
	[timeKeepingId] [varchar](50) NOT NULL,
 CONSTRAINT [PK_TimeKeepingStaff] PRIMARY KEY CLUSTERED 
(
	[timeKeepingId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TimeKeepingWorker]    Script Date: 12/10/2022 8:03:09 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TimeKeepingWorker](
	[timeKeepingId] [varchar](50) NOT NULL,
	[productId] [varchar](50) NOT NULL,
	[productionStageId] [varchar](50) NOT NULL,
	[assignmentId] [varchar](50) NOT NULL,
 CONSTRAINT [PK_TimeKeepingWorker] PRIMARY KEY CLUSTERED 
(
	[timeKeepingId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Worker]    Script Date: 12/10/2022 8:03:09 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Worker](
	[employeeId] [varchar](50) NOT NULL,
 CONSTRAINT [PK_Worker] PRIMARY KEY CLUSTERED 
(
	[employeeId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[Administrative_Staff] ([employeeId], [basicSalary]) VALUES (N'EMP-01', 15000000.0000)
INSERT [dbo].[Administrative_Staff] ([employeeId], [basicSalary]) VALUES (N'EMP-02', 15000000.0000)
INSERT [dbo].[Administrative_Staff] ([employeeId], [basicSalary]) VALUES (N'EMP-03', 15000000.0000)
INSERT [dbo].[Administrative_Staff] ([employeeId], [basicSalary]) VALUES (N'EMP-04', 15000000.0000)
INSERT [dbo].[Administrative_Staff] ([employeeId], [basicSalary]) VALUES (N'EMP-05', 15000000.0000)
INSERT [dbo].[Administrative_Staff] ([employeeId], [basicSalary]) VALUES (N'EMP-06', 15000000.0000)
GO
INSERT [dbo].[Assignment] ([id], [workerId], [finishAmount], [productionStageId]) VALUES (N'PC-01', N'CN-01', 5, N'CÐ-1')
INSERT [dbo].[Assignment] ([id], [workerId], [finishAmount], [productionStageId]) VALUES (N'PC-02', N'CN-02', 5, N'CÐ-1')
INSERT [dbo].[Assignment] ([id], [workerId], [finishAmount], [productionStageId]) VALUES (N'PC-03', N'CN-01', 10, N'CÐ-2')
GO
INSERT [dbo].[Department] ([id], [name]) VALUES (N'DP-01', N'Ban Hành Chính')
INSERT [dbo].[Department] ([id], [name]) VALUES (N'DP-02', N'Ban Kế Toán')
INSERT [dbo].[Department] ([id], [name]) VALUES (N'DP-03', N'Ban Quản Lý')
GO
INSERT [dbo].[Employee] ([id], [fullName], [identifyCard], [dateOfBirth], [gender], [salary], [allowance], [phoneNumber], [address], [departmentId]) VALUES (N'CN-01', N'Minh CN', N'9847291752314', CAST(N'2022-12-12' AS Date), N'Nam', 92307.9808, 10000.0000, N'1253216891', N'50/5 street 25', N'DP-01')
INSERT [dbo].[Employee] ([id], [fullName], [identifyCard], [dateOfBirth], [gender], [salary], [allowance], [phoneNumber], [address], [departmentId]) VALUES (N'CN-02', N'Hà', N'1234567890', CAST(N'2022-12-08' AS Date), N'Nữ', 30769.3269, 10000.0000, N'1234567890', N'123467', N'DP-03')
INSERT [dbo].[Employee] ([id], [fullName], [identifyCard], [dateOfBirth], [gender], [salary], [allowance], [phoneNumber], [address], [departmentId]) VALUES (N'EMP-01', N'Minh', N'596820158692', CAST(N'1994-06-15' AS Date), N'Nam', 588461.5385, 300000.0000, N'0937582615', N'58/6 street 24', N'DP-01')
INSERT [dbo].[Employee] ([id], [fullName], [identifyCard], [dateOfBirth], [gender], [salary], [allowance], [phoneNumber], [address], [departmentId]) VALUES (N'EMP-02', N'Tuấn', N'084569281592', CAST(N'2022-12-22' AS Date), N'Nam', 634615.3846, 1500000.0000, N'0987543213', N'89/5 street 59', N'DP-02')
INSERT [dbo].[Employee] ([id], [fullName], [identifyCard], [dateOfBirth], [gender], [salary], [allowance], [phoneNumber], [address], [departmentId]) VALUES (N'EMP-03', N'Tài', N'024467251592', CAST(N'1994-12-08' AS Date), N'Nam', 317307.6923, 1500000.0000, N'0237446713', N'12/52 street 54', N'DP-03')
INSERT [dbo].[Employee] ([id], [fullName], [identifyCard], [dateOfBirth], [gender], [salary], [allowance], [phoneNumber], [address], [departmentId]) VALUES (N'EMP-04', N'Bảo', N'123456789053', CAST(N'1999-06-19' AS Date), N'Nam', 288653.8462, 10000.0000, N'1234567890', N'87/5 street 5', N'DP-03')
INSERT [dbo].[Employee] ([id], [fullName], [identifyCard], [dateOfBirth], [gender], [salary], [allowance], [phoneNumber], [address], [departmentId]) VALUES (N'EMP-05', N'Thịnh', N'12345678912356', CAST(N'2022-12-10' AS Date), N'Nam', 577307.6923, 10000.0000, N'1234567890', N'89/5 street 5', N'DP-02')
INSERT [dbo].[Employee] ([id], [fullName], [identifyCard], [dateOfBirth], [gender], [salary], [allowance], [phoneNumber], [address], [departmentId]) VALUES (N'EMP-06', N'Đạt', N'1234567890', CAST(N'2022-12-14' AS Date), N'Nam', 288653.8462, 10000.0000, N'1234567890', N'89/54 street 25', N'DP-02')
GO
INSERT [dbo].[Product] ([id], [name], [style], [material], [amount]) VALUES (N'SP-01', N'Áo Thun', N'Oversize', N'cotton', 0)
INSERT [dbo].[Product] ([id], [name], [style], [material], [amount]) VALUES (N'SP-02', N'Áo Ba lỗ', N'nhỏ', N'vải', 25)
INSERT [dbo].[Product] ([id], [name], [style], [material], [amount]) VALUES (N'SP-03', N'Áo da bò', N'vừa', N'da bò', 50)
GO
INSERT [dbo].[Production_Stage] ([id], [name], [price], [productId], [amount], [productionStageId]) VALUES (N'CÐ-1', N'Công đoạn 1 áo ba lỗ', 500000.0000, N'SP-01', 0, NULL)
INSERT [dbo].[Production_Stage] ([id], [name], [price], [productId], [amount], [productionStageId]) VALUES (N'CÐ-2', N'Công đoạn 2 áo ba lỗ', 500000.0000, N'SP-02', 15, N'CÐ-1')
GO
INSERT [dbo].[ShiftWork] ([id], [name], [startTime], [endTime], [salary]) VALUES (N'S-01', N'Sáng', CAST(N'2022-12-09T07:00:00.000' AS DateTime), CAST(N'2022-12-09T11:00:00.000' AS DateTime), 150000.0000)
INSERT [dbo].[ShiftWork] ([id], [name], [startTime], [endTime], [salary]) VALUES (N'S-02', N'Chiều', CAST(N'2022-12-09T02:00:00.000' AS DateTime), CAST(N'2022-12-09T05:00:00.000' AS DateTime), 150000.0000)
GO
INSERT [dbo].[TimeKeeping] ([id], [employeeId], [createdDate], [shiftWorkId], [status], [leavePermission]) VALUES (N'CCN-05', N'CN-01', CAST(N'2022-12-10' AS Date), N'S-01', N'có mặt', 0)
INSERT [dbo].[TimeKeeping] ([id], [employeeId], [createdDate], [shiftWorkId], [status], [leavePermission]) VALUES (N'CCN-06', N'CN-02', CAST(N'2022-12-10' AS Date), N'S-01', N'có mặt', 0)
INSERT [dbo].[TimeKeeping] ([id], [employeeId], [createdDate], [shiftWorkId], [status], [leavePermission]) VALUES (N'CCN-07', N'CN-01', CAST(N'2022-12-10' AS Date), N'S-02', N'có mặt', 0)
INSERT [dbo].[TimeKeeping] ([id], [employeeId], [createdDate], [shiftWorkId], [status], [leavePermission]) VALUES (N'CNV-01', N'EMP-01', CAST(N'2022-12-10' AS Date), N'S-01', N'có mặt', 0)
INSERT [dbo].[TimeKeeping] ([id], [employeeId], [createdDate], [shiftWorkId], [status], [leavePermission]) VALUES (N'CNV-02', N'EMP-02', CAST(N'2022-12-10' AS Date), N'S-01', N'có mặt', 0)
INSERT [dbo].[TimeKeeping] ([id], [employeeId], [createdDate], [shiftWorkId], [status], [leavePermission]) VALUES (N'CNV-03', N'EMP-05', CAST(N'2022-12-10' AS Date), N'S-01', N'nghỉ', 0)
INSERT [dbo].[TimeKeeping] ([id], [employeeId], [createdDate], [shiftWorkId], [status], [leavePermission]) VALUES (N'CNV-04', N'EMP-05', CAST(N'2022-12-10' AS Date), N'S-02', N'có mặt', 0)
GO
INSERT [dbo].[TimeKeepingStaff] ([timeKeepingId]) VALUES (N'CNV-01')
INSERT [dbo].[TimeKeepingStaff] ([timeKeepingId]) VALUES (N'CNV-02')
INSERT [dbo].[TimeKeepingStaff] ([timeKeepingId]) VALUES (N'CNV-03')
INSERT [dbo].[TimeKeepingStaff] ([timeKeepingId]) VALUES (N'CNV-04')
GO
INSERT [dbo].[TimeKeepingWorker] ([timeKeepingId], [productId], [productionStageId], [assignmentId]) VALUES (N'CCN-06', N'SP-01', N'CÐ-1', N'PC-02')
INSERT [dbo].[TimeKeepingWorker] ([timeKeepingId], [productId], [productionStageId], [assignmentId]) VALUES (N'CCN-07', N'SP-02', N'CÐ-2', N'PC-03')
GO
INSERT [dbo].[Worker] ([employeeId]) VALUES (N'CN-01')
INSERT [dbo].[Worker] ([employeeId]) VALUES (N'CN-02')
GO
ALTER TABLE [dbo].[Administrative_Staff]  WITH CHECK ADD  CONSTRAINT [FK_Administrative_Staff_Employee] FOREIGN KEY([employeeId])
REFERENCES [dbo].[Employee] ([id])
GO
ALTER TABLE [dbo].[Administrative_Staff] CHECK CONSTRAINT [FK_Administrative_Staff_Employee]
GO
ALTER TABLE [dbo].[Assignment]  WITH CHECK ADD  CONSTRAINT [FK_Assignment_ProductionStage] FOREIGN KEY([productionStageId])
REFERENCES [dbo].[Production_Stage] ([id])
GO
ALTER TABLE [dbo].[Assignment] CHECK CONSTRAINT [FK_Assignment_ProductionStage]
GO
ALTER TABLE [dbo].[Assignment]  WITH CHECK ADD  CONSTRAINT [FK_Assignment_Worker] FOREIGN KEY([workerId])
REFERENCES [dbo].[Worker] ([employeeId])
GO
ALTER TABLE [dbo].[Assignment] CHECK CONSTRAINT [FK_Assignment_Worker]
GO
ALTER TABLE [dbo].[Employee]  WITH CHECK ADD  CONSTRAINT [FK_Employee_Department] FOREIGN KEY([departmentId])
REFERENCES [dbo].[Department] ([id])
GO
ALTER TABLE [dbo].[Employee] CHECK CONSTRAINT [FK_Employee_Department]
GO
ALTER TABLE [dbo].[Production_Stage]  WITH CHECK ADD  CONSTRAINT [FK_Production_Stage_Product] FOREIGN KEY([productId])
REFERENCES [dbo].[Product] ([id])
GO
ALTER TABLE [dbo].[Production_Stage] CHECK CONSTRAINT [FK_Production_Stage_Product]
GO
ALTER TABLE [dbo].[Production_Stage]  WITH CHECK ADD  CONSTRAINT [FK_Production_Stage_Production_Stage] FOREIGN KEY([productionStageId])
REFERENCES [dbo].[Production_Stage] ([id])
GO
ALTER TABLE [dbo].[Production_Stage] CHECK CONSTRAINT [FK_Production_Stage_Production_Stage]
GO
ALTER TABLE [dbo].[TimeKeeping]  WITH CHECK ADD  CONSTRAINT [FK_TimeKeeping_Employee] FOREIGN KEY([employeeId])
REFERENCES [dbo].[Employee] ([id])
GO
ALTER TABLE [dbo].[TimeKeeping] CHECK CONSTRAINT [FK_TimeKeeping_Employee]
GO
ALTER TABLE [dbo].[TimeKeeping]  WITH CHECK ADD  CONSTRAINT [FK_TimeKeeping_ShiftWork] FOREIGN KEY([shiftWorkId])
REFERENCES [dbo].[ShiftWork] ([id])
GO
ALTER TABLE [dbo].[TimeKeeping] CHECK CONSTRAINT [FK_TimeKeeping_ShiftWork]
GO
ALTER TABLE [dbo].[TimeKeepingStaff]  WITH CHECK ADD  CONSTRAINT [FK_TimeKeepingStaff_TimeKeeping] FOREIGN KEY([timeKeepingId])
REFERENCES [dbo].[TimeKeeping] ([id])
GO
ALTER TABLE [dbo].[TimeKeepingStaff] CHECK CONSTRAINT [FK_TimeKeepingStaff_TimeKeeping]
GO
ALTER TABLE [dbo].[TimeKeepingWorker]  WITH CHECK ADD  CONSTRAINT [FK_TimeKeepingWorker_TimeKeeping] FOREIGN KEY([timeKeepingId])
REFERENCES [dbo].[TimeKeeping] ([id])
GO
ALTER TABLE [dbo].[TimeKeepingWorker] CHECK CONSTRAINT [FK_TimeKeepingWorker_TimeKeeping]
GO
ALTER TABLE [dbo].[Worker]  WITH CHECK ADD  CONSTRAINT [FK_Worker_Employee] FOREIGN KEY([employeeId])
REFERENCES [dbo].[Employee] ([id])
GO
ALTER TABLE [dbo].[Worker] CHECK CONSTRAINT [FK_Worker_Employee]
GO
USE [master]
GO
ALTER DATABASE [SalaryDB] SET  READ_WRITE 
GO
