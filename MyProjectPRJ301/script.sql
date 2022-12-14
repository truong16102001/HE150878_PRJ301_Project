USE [master]
GO
/****** Object:  Database [prj]    Script Date: 11/8/2022 11:16:47 PM ******/
CREATE DATABASE [prj]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'prj', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.SQLEXPRESS\MSSQL\DATA\prj.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'prj_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.SQLEXPRESS\MSSQL\DATA\prj_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [prj] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [prj].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [prj] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [prj] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [prj] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [prj] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [prj] SET ARITHABORT OFF 
GO
ALTER DATABASE [prj] SET AUTO_CLOSE ON 
GO
ALTER DATABASE [prj] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [prj] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [prj] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [prj] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [prj] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [prj] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [prj] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [prj] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [prj] SET  ENABLE_BROKER 
GO
ALTER DATABASE [prj] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [prj] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [prj] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [prj] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [prj] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [prj] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [prj] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [prj] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [prj] SET  MULTI_USER 
GO
ALTER DATABASE [prj] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [prj] SET DB_CHAINING OFF 
GO
ALTER DATABASE [prj] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [prj] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [prj] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [prj] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
ALTER DATABASE [prj] SET QUERY_STORE = OFF
GO
USE [prj]
GO
/****** Object:  Table [dbo].[Account]    Script Date: 11/8/2022 11:16:47 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Account](
	[username] [varchar](150) NOT NULL,
	[password] [varchar](150) NOT NULL,
	[displayname] [varchar](150) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Attandance]    Script Date: 11/8/2022 11:16:47 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Attandance](
	[sesid] [int] NOT NULL,
	[stdid] [int] NOT NULL,
	[present] [bit] NOT NULL,
	[description] [varchar](150) NOT NULL,
	[record_time] [datetime] NULL,
 CONSTRAINT [PK_Attandance] PRIMARY KEY CLUSTERED 
(
	[sesid] ASC,
	[stdid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Feature]    Script Date: 11/8/2022 11:16:47 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Feature](
	[fid] [int] NOT NULL,
	[fname] [varchar](150) NOT NULL,
	[url] [varchar](max) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[fid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Group]    Script Date: 11/8/2022 11:16:47 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Group](
	[gid] [int] NOT NULL,
	[gname] [varchar](150) NOT NULL,
	[subid] [int] NOT NULL,
	[lid] [int] NOT NULL,
	[sem] [varchar](150) NOT NULL,
	[year] [int] NOT NULL,
 CONSTRAINT [PK_Group] PRIMARY KEY CLUSTERED 
(
	[gid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Lecturer]    Script Date: 11/8/2022 11:16:47 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Lecturer](
	[lid] [int] NOT NULL,
	[lname] [varchar](150) NOT NULL,
	[username] [varchar](150) NULL,
 CONSTRAINT [PK_Lecturer] PRIMARY KEY CLUSTERED 
(
	[lid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Role]    Script Date: 11/8/2022 11:16:47 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Role](
	[roid] [int] NOT NULL,
	[roname] [varchar](150) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[roid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Role_Account]    Script Date: 11/8/2022 11:16:47 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Role_Account](
	[roid] [int] NOT NULL,
	[username] [varchar](150) NOT NULL,
 CONSTRAINT [pk_assignment2] PRIMARY KEY CLUSTERED 
(
	[roid] ASC,
	[username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Role_Feature]    Script Date: 11/8/2022 11:16:47 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Role_Feature](
	[roid] [int] NOT NULL,
	[fid] [int] NOT NULL,
 CONSTRAINT [pk_assignment] PRIMARY KEY CLUSTERED 
(
	[roid] ASC,
	[fid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Room]    Script Date: 11/8/2022 11:16:47 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Room](
	[rid] [int] NOT NULL,
	[rname] [varchar](150) NOT NULL,
 CONSTRAINT [PK_Room] PRIMARY KEY CLUSTERED 
(
	[rid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Session]    Script Date: 11/8/2022 11:16:47 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Session](
	[sesid] [int] NOT NULL,
	[gid] [int] NOT NULL,
	[rid] [int] NOT NULL,
	[date] [date] NOT NULL,
	[tid] [int] NOT NULL,
	[index] [int] NOT NULL,
	[lid] [int] NOT NULL,
	[attanded] [bit] NULL,
 CONSTRAINT [PK_Session] PRIMARY KEY CLUSTERED 
(
	[sesid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Student]    Script Date: 11/8/2022 11:16:47 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Student](
	[stdid] [int] NOT NULL,
	[stdname] [varchar](150) NOT NULL,
	[username] [varchar](150) NULL,
 CONSTRAINT [PK_Student] PRIMARY KEY CLUSTERED 
(
	[stdid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Student_Group]    Script Date: 11/8/2022 11:16:47 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Student_Group](
	[stdid] [int] NOT NULL,
	[gid] [int] NOT NULL,
 CONSTRAINT [PK_Student_Group] PRIMARY KEY CLUSTERED 
(
	[stdid] ASC,
	[gid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Subject]    Script Date: 11/8/2022 11:16:47 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Subject](
	[subid] [int] NOT NULL,
	[subname] [varchar](150) NOT NULL,
 CONSTRAINT [PK_Subject] PRIMARY KEY CLUSTERED 
(
	[subid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TimeSlot]    Script Date: 11/8/2022 11:16:47 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TimeSlot](
	[tid] [int] NOT NULL,
	[description] [varchar](150) NOT NULL,
 CONSTRAINT [PK_TimeSlot] PRIMARY KEY CLUSTERED 
(
	[tid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[Account] ([username], [password], [displayname]) VALUES (N'abc', N'123', N'Mr.ABC')
INSERT [dbo].[Account] ([username], [password], [displayname]) VALUES (N'danv', N'123', N'Mr.Da')
INSERT [dbo].[Account] ([username], [password], [displayname]) VALUES (N'sonhx', N'123', N'Mr.HXS')
INSERT [dbo].[Account] ([username], [password], [displayname]) VALUES (N'sonnt', N'123', N'Superman')
INSERT [dbo].[Account] ([username], [password], [displayname]) VALUES (N'trungdt', N'123', N'Mr.DTT')
GO
INSERT [dbo].[Attandance] ([sesid], [stdid], [present], [description], [record_time]) VALUES (1, 1, 1, N'no probelm', CAST(N'2022-10-30T00:48:08.063' AS DateTime))
INSERT [dbo].[Attandance] ([sesid], [stdid], [present], [description], [record_time]) VALUES (1, 2, 1, N' abc', CAST(N'2022-10-30T00:48:08.063' AS DateTime))
INSERT [dbo].[Attandance] ([sesid], [stdid], [present], [description], [record_time]) VALUES (1, 3, 1, N' vang2', CAST(N'2022-10-30T00:48:08.067' AS DateTime))
INSERT [dbo].[Attandance] ([sesid], [stdid], [present], [description], [record_time]) VALUES (1, 4, 0, N'', CAST(N'2022-10-30T00:48:08.067' AS DateTime))
INSERT [dbo].[Attandance] ([sesid], [stdid], [present], [description], [record_time]) VALUES (2, 1, 0, N'', CAST(N'2022-10-28T15:26:54.587' AS DateTime))
INSERT [dbo].[Attandance] ([sesid], [stdid], [present], [description], [record_time]) VALUES (2, 2, 1, N' abc', CAST(N'2022-10-28T15:26:54.590' AS DateTime))
INSERT [dbo].[Attandance] ([sesid], [stdid], [present], [description], [record_time]) VALUES (2, 3, 1, N'deea', CAST(N'2022-10-28T15:26:54.590' AS DateTime))
INSERT [dbo].[Attandance] ([sesid], [stdid], [present], [description], [record_time]) VALUES (2, 4, 0, N'', CAST(N'2022-10-28T15:26:54.590' AS DateTime))
INSERT [dbo].[Attandance] ([sesid], [stdid], [present], [description], [record_time]) VALUES (3, 1, 1, N'', CAST(N'2022-10-30T01:04:07.640' AS DateTime))
INSERT [dbo].[Attandance] ([sesid], [stdid], [present], [description], [record_time]) VALUES (3, 2, 0, N'', CAST(N'2022-10-30T01:04:07.640' AS DateTime))
INSERT [dbo].[Attandance] ([sesid], [stdid], [present], [description], [record_time]) VALUES (3, 3, 1, N'Xxxxxx', CAST(N'2022-10-30T01:04:07.640' AS DateTime))
INSERT [dbo].[Attandance] ([sesid], [stdid], [present], [description], [record_time]) VALUES (3, 4, 1, N'X', CAST(N'2022-10-30T01:04:07.640' AS DateTime))
INSERT [dbo].[Attandance] ([sesid], [stdid], [present], [description], [record_time]) VALUES (4, 1, 1, N'A', CAST(N'2022-10-28T15:35:58.080' AS DateTime))
INSERT [dbo].[Attandance] ([sesid], [stdid], [present], [description], [record_time]) VALUES (4, 2, 1, N'A', CAST(N'2022-10-28T15:35:58.080' AS DateTime))
INSERT [dbo].[Attandance] ([sesid], [stdid], [present], [description], [record_time]) VALUES (4, 3, 1, N'Abcs', CAST(N'2022-10-28T15:35:58.080' AS DateTime))
INSERT [dbo].[Attandance] ([sesid], [stdid], [present], [description], [record_time]) VALUES (4, 4, 0, N'', CAST(N'2022-10-28T15:35:58.080' AS DateTime))
INSERT [dbo].[Attandance] ([sesid], [stdid], [present], [description], [record_time]) VALUES (6, 1, 0, N'', CAST(N'2022-10-24T16:12:42.817' AS DateTime))
INSERT [dbo].[Attandance] ([sesid], [stdid], [present], [description], [record_time]) VALUES (6, 3, 0, N'', CAST(N'2022-10-24T16:12:42.820' AS DateTime))
INSERT [dbo].[Attandance] ([sesid], [stdid], [present], [description], [record_time]) VALUES (6, 4, 1, N'ngao1', CAST(N'2022-10-24T16:12:42.820' AS DateTime))
INSERT [dbo].[Attandance] ([sesid], [stdid], [present], [description], [record_time]) VALUES (6, 5, 1, N'ngao2', CAST(N'2022-10-24T16:12:42.820' AS DateTime))
INSERT [dbo].[Attandance] ([sesid], [stdid], [present], [description], [record_time]) VALUES (6, 6, 1, N'ngoa3', CAST(N'2022-10-24T16:12:42.820' AS DateTime))
INSERT [dbo].[Attandance] ([sesid], [stdid], [present], [description], [record_time]) VALUES (10, 1, 0, N'', CAST(N'2022-10-22T23:27:25.870' AS DateTime))
INSERT [dbo].[Attandance] ([sesid], [stdid], [present], [description], [record_time]) VALUES (10, 3, 1, N'', CAST(N'2022-10-22T23:27:25.870' AS DateTime))
INSERT [dbo].[Attandance] ([sesid], [stdid], [present], [description], [record_time]) VALUES (10, 4, 1, N'', CAST(N'2022-10-22T23:27:25.870' AS DateTime))
INSERT [dbo].[Attandance] ([sesid], [stdid], [present], [description], [record_time]) VALUES (10, 5, 1, N'', CAST(N'2022-10-22T23:27:25.870' AS DateTime))
INSERT [dbo].[Attandance] ([sesid], [stdid], [present], [description], [record_time]) VALUES (10, 6, 0, N'', CAST(N'2022-10-22T23:27:25.870' AS DateTime))
INSERT [dbo].[Attandance] ([sesid], [stdid], [present], [description], [record_time]) VALUES (14, 3, 1, N'dee', CAST(N'2022-10-29T14:22:36.600' AS DateTime))
INSERT [dbo].[Attandance] ([sesid], [stdid], [present], [description], [record_time]) VALUES (14, 4, 1, N'cccczzz', CAST(N'2022-10-29T14:22:36.600' AS DateTime))
INSERT [dbo].[Attandance] ([sesid], [stdid], [present], [description], [record_time]) VALUES (14, 7, 0, N'', CAST(N'2022-10-29T14:22:36.603' AS DateTime))
INSERT [dbo].[Attandance] ([sesid], [stdid], [present], [description], [record_time]) VALUES (15, 3, 0, N'', CAST(N'2022-10-23T01:55:43.500' AS DateTime))
INSERT [dbo].[Attandance] ([sesid], [stdid], [present], [description], [record_time]) VALUES (15, 4, 1, N'late', CAST(N'2022-10-23T01:55:43.500' AS DateTime))
INSERT [dbo].[Attandance] ([sesid], [stdid], [present], [description], [record_time]) VALUES (15, 7, 0, N'', CAST(N'2022-10-23T01:55:43.503' AS DateTime))
INSERT [dbo].[Attandance] ([sesid], [stdid], [present], [description], [record_time]) VALUES (19, 1, 1, N'late 60minutes', CAST(N'2022-10-23T00:27:19.997' AS DateTime))
INSERT [dbo].[Attandance] ([sesid], [stdid], [present], [description], [record_time]) VALUES (19, 2, 0, N'', CAST(N'2022-10-23T00:27:19.997' AS DateTime))
INSERT [dbo].[Attandance] ([sesid], [stdid], [present], [description], [record_time]) VALUES (20, 1, 1, N'', CAST(N'2022-10-22T23:44:43.950' AS DateTime))
INSERT [dbo].[Attandance] ([sesid], [stdid], [present], [description], [record_time]) VALUES (20, 2, 0, N'', CAST(N'2022-10-22T23:44:43.950' AS DateTime))
GO
INSERT [dbo].[Feature] ([fid], [fname], [url]) VALUES (1, N'Lecturer Schedule', N'/lecturer/schedule')
INSERT [dbo].[Feature] ([fid], [fname], [url]) VALUES (2, N'Lecturer Attendance View', N'/lecturer/attview')
INSERT [dbo].[Feature] ([fid], [fname], [url]) VALUES (3, N'Lecturer Attendance Save', N'/lecturer/att')
INSERT [dbo].[Feature] ([fid], [fname], [url]) VALUES (4, N'Lecturer Attendance Report', N'/lecturer/reportattendance')
GO
INSERT [dbo].[Group] ([gid], [gname], [subid], [lid], [sem], [year]) VALUES (1, N'SE1645-PRJ301', 1, 1, N'FALL', 2022)
INSERT [dbo].[Group] ([gid], [gname], [subid], [lid], [sem], [year]) VALUES (2, N'SE1645-MAS291', 2, 2, N'FALL', 2022)
INSERT [dbo].[Group] ([gid], [gname], [subid], [lid], [sem], [year]) VALUES (3, N'SE1645-IOT102', 3, 3, N'FALL', 2022)
INSERT [dbo].[Group] ([gid], [gname], [subid], [lid], [sem], [year]) VALUES (4, N'SE1645-PRO192', 4, 1, N'FALL', 2022)
INSERT [dbo].[Group] ([gid], [gname], [subid], [lid], [sem], [year]) VALUES (5, N'SE-1645_PRO192', 4, 2, N'FALL', 2022)
INSERT [dbo].[Group] ([gid], [gname], [subid], [lid], [sem], [year]) VALUES (6, N'AI1602-MAS291', 2, 2, N'FALL', 2022)
INSERT [dbo].[Group] ([gid], [gname], [subid], [lid], [sem], [year]) VALUES (7, N'IA1122-PRJ301', 1, 1, N'FALL', 2022)
GO
INSERT [dbo].[Lecturer] ([lid], [lname], [username]) VALUES (1, N'Superman', N'sonnt')
INSERT [dbo].[Lecturer] ([lid], [lname], [username]) VALUES (2, N'Mr.TrungDT', N'trungdt')
INSERT [dbo].[Lecturer] ([lid], [lname], [username]) VALUES (3, N'Mr.SonHX', N'sonhx')
GO
INSERT [dbo].[Role] ([roid], [roname]) VALUES (1, N'IT')
INSERT [dbo].[Role] ([roid], [roname]) VALUES (2, N'Accounting')
INSERT [dbo].[Role] ([roid], [roname]) VALUES (3, N'Student')
INSERT [dbo].[Role] ([roid], [roname]) VALUES (4, N'Lecturer')
GO
INSERT [dbo].[Role_Account] ([roid], [username]) VALUES (1, N'sonnt')
INSERT [dbo].[Role_Account] ([roid], [username]) VALUES (2, N'danv')
INSERT [dbo].[Role_Account] ([roid], [username]) VALUES (2, N'sonhx')
INSERT [dbo].[Role_Account] ([roid], [username]) VALUES (4, N'sonhx')
INSERT [dbo].[Role_Account] ([roid], [username]) VALUES (4, N'sonnt')
INSERT [dbo].[Role_Account] ([roid], [username]) VALUES (4, N'trungdt')
GO
INSERT [dbo].[Role_Feature] ([roid], [fid]) VALUES (1, 1)
INSERT [dbo].[Role_Feature] ([roid], [fid]) VALUES (1, 2)
INSERT [dbo].[Role_Feature] ([roid], [fid]) VALUES (1, 3)
INSERT [dbo].[Role_Feature] ([roid], [fid]) VALUES (1, 4)
INSERT [dbo].[Role_Feature] ([roid], [fid]) VALUES (2, 1)
INSERT [dbo].[Role_Feature] ([roid], [fid]) VALUES (4, 1)
INSERT [dbo].[Role_Feature] ([roid], [fid]) VALUES (4, 2)
INSERT [dbo].[Role_Feature] ([roid], [fid]) VALUES (4, 3)
INSERT [dbo].[Role_Feature] ([roid], [fid]) VALUES (4, 4)
GO
INSERT [dbo].[Room] ([rid], [rname]) VALUES (1, N'A1')
INSERT [dbo].[Room] ([rid], [rname]) VALUES (2, N'B1')
INSERT [dbo].[Room] ([rid], [rname]) VALUES (3, N'A3')
INSERT [dbo].[Room] ([rid], [rname]) VALUES (4, N'A5')
INSERT [dbo].[Room] ([rid], [rname]) VALUES (5, N'B3')
GO
INSERT [dbo].[Session] ([sesid], [gid], [rid], [date], [tid], [index], [lid], [attanded]) VALUES (1, 1, 1, CAST(N'2022-10-10' AS Date), 1, 1, 1, 1)
INSERT [dbo].[Session] ([sesid], [gid], [rid], [date], [tid], [index], [lid], [attanded]) VALUES (2, 1, 1, CAST(N'2022-10-12' AS Date), 1, 2, 1, 1)
INSERT [dbo].[Session] ([sesid], [gid], [rid], [date], [tid], [index], [lid], [attanded]) VALUES (3, 1, 1, CAST(N'2022-10-14' AS Date), 1, 3, 1, 1)
INSERT [dbo].[Session] ([sesid], [gid], [rid], [date], [tid], [index], [lid], [attanded]) VALUES (4, 1, 1, CAST(N'2022-10-17' AS Date), 1, 4, 1, 1)
INSERT [dbo].[Session] ([sesid], [gid], [rid], [date], [tid], [index], [lid], [attanded]) VALUES (5, 1, 1, CAST(N'2022-10-19' AS Date), 1, 5, 1, 0)
INSERT [dbo].[Session] ([sesid], [gid], [rid], [date], [tid], [index], [lid], [attanded]) VALUES (6, 2, 1, CAST(N'2022-10-10' AS Date), 2, 1, 1, 1)
INSERT [dbo].[Session] ([sesid], [gid], [rid], [date], [tid], [index], [lid], [attanded]) VALUES (7, 2, 1, CAST(N'2022-10-12' AS Date), 2, 2, 1, 0)
INSERT [dbo].[Session] ([sesid], [gid], [rid], [date], [tid], [index], [lid], [attanded]) VALUES (8, 2, 1, CAST(N'2022-10-14' AS Date), 2, 3, 1, 0)
INSERT [dbo].[Session] ([sesid], [gid], [rid], [date], [tid], [index], [lid], [attanded]) VALUES (9, 2, 1, CAST(N'2022-10-17' AS Date), 2, 4, 1, 0)
INSERT [dbo].[Session] ([sesid], [gid], [rid], [date], [tid], [index], [lid], [attanded]) VALUES (10, 2, 1, CAST(N'2022-10-19' AS Date), 2, 5, 1, 1)
INSERT [dbo].[Session] ([sesid], [gid], [rid], [date], [tid], [index], [lid], [attanded]) VALUES (11, 3, 2, CAST(N'2022-10-10' AS Date), 1, 1, 2, 0)
INSERT [dbo].[Session] ([sesid], [gid], [rid], [date], [tid], [index], [lid], [attanded]) VALUES (12, 3, 2, CAST(N'2022-10-12' AS Date), 1, 2, 2, 0)
INSERT [dbo].[Session] ([sesid], [gid], [rid], [date], [tid], [index], [lid], [attanded]) VALUES (13, 3, 2, CAST(N'2022-10-14' AS Date), 1, 3, 2, 0)
INSERT [dbo].[Session] ([sesid], [gid], [rid], [date], [tid], [index], [lid], [attanded]) VALUES (14, 3, 2, CAST(N'2022-10-17' AS Date), 1, 4, 2, 1)
INSERT [dbo].[Session] ([sesid], [gid], [rid], [date], [tid], [index], [lid], [attanded]) VALUES (15, 3, 2, CAST(N'2022-10-19' AS Date), 1, 5, 2, 1)
INSERT [dbo].[Session] ([sesid], [gid], [rid], [date], [tid], [index], [lid], [attanded]) VALUES (16, 4, 1, CAST(N'2022-10-10' AS Date), 3, 1, 1, 0)
INSERT [dbo].[Session] ([sesid], [gid], [rid], [date], [tid], [index], [lid], [attanded]) VALUES (17, 4, 1, CAST(N'2022-10-12' AS Date), 3, 2, 1, 0)
INSERT [dbo].[Session] ([sesid], [gid], [rid], [date], [tid], [index], [lid], [attanded]) VALUES (18, 4, 1, CAST(N'2022-10-14' AS Date), 3, 3, 1, 0)
INSERT [dbo].[Session] ([sesid], [gid], [rid], [date], [tid], [index], [lid], [attanded]) VALUES (19, 4, 1, CAST(N'2022-10-17' AS Date), 3, 4, 1, 1)
INSERT [dbo].[Session] ([sesid], [gid], [rid], [date], [tid], [index], [lid], [attanded]) VALUES (20, 4, 1, CAST(N'2022-10-19' AS Date), 3, 5, 1, 1)
INSERT [dbo].[Session] ([sesid], [gid], [rid], [date], [tid], [index], [lid], [attanded]) VALUES (21, 7, 3, CAST(N'2022-10-20' AS Date), 5, 10, 2, 1)
INSERT [dbo].[Session] ([sesid], [gid], [rid], [date], [tid], [index], [lid], [attanded]) VALUES (22, 1, 1, CAST(N'2022-10-20' AS Date), 1, 6, 1, 0)
GO
INSERT [dbo].[Student] ([stdid], [stdname], [username]) VALUES (1, N'Nguyen Van Da', NULL)
INSERT [dbo].[Student] ([stdid], [stdname], [username]) VALUES (2, N'Nguyen Van Sa', NULL)
INSERT [dbo].[Student] ([stdid], [stdname], [username]) VALUES (3, N'Nguyen Van Ca', NULL)
INSERT [dbo].[Student] ([stdid], [stdname], [username]) VALUES (4, N'Nguyen Van Ba', NULL)
INSERT [dbo].[Student] ([stdid], [stdname], [username]) VALUES (5, N'Nguyen Van Na', NULL)
INSERT [dbo].[Student] ([stdid], [stdname], [username]) VALUES (6, N'Nguyen Van MA', NULL)
INSERT [dbo].[Student] ([stdid], [stdname], [username]) VALUES (7, N'Nguyen Van Ha', NULL)
INSERT [dbo].[Student] ([stdid], [stdname], [username]) VALUES (8, N'Nguyen Thi La', NULL)
INSERT [dbo].[Student] ([stdid], [stdname], [username]) VALUES (9, N'Nguyen Thi Va', NULL)
INSERT [dbo].[Student] ([stdid], [stdname], [username]) VALUES (10, N'Nguyen Thi IA', NULL)
GO
INSERT [dbo].[Student_Group] ([stdid], [gid]) VALUES (1, 1)
INSERT [dbo].[Student_Group] ([stdid], [gid]) VALUES (1, 2)
INSERT [dbo].[Student_Group] ([stdid], [gid]) VALUES (1, 4)
INSERT [dbo].[Student_Group] ([stdid], [gid]) VALUES (2, 1)
INSERT [dbo].[Student_Group] ([stdid], [gid]) VALUES (2, 4)
INSERT [dbo].[Student_Group] ([stdid], [gid]) VALUES (3, 1)
INSERT [dbo].[Student_Group] ([stdid], [gid]) VALUES (3, 2)
INSERT [dbo].[Student_Group] ([stdid], [gid]) VALUES (3, 3)
INSERT [dbo].[Student_Group] ([stdid], [gid]) VALUES (4, 1)
INSERT [dbo].[Student_Group] ([stdid], [gid]) VALUES (4, 2)
INSERT [dbo].[Student_Group] ([stdid], [gid]) VALUES (4, 3)
INSERT [dbo].[Student_Group] ([stdid], [gid]) VALUES (5, 2)
INSERT [dbo].[Student_Group] ([stdid], [gid]) VALUES (6, 2)
INSERT [dbo].[Student_Group] ([stdid], [gid]) VALUES (7, 3)
INSERT [dbo].[Student_Group] ([stdid], [gid]) VALUES (8, 6)
GO
INSERT [dbo].[Subject] ([subid], [subname]) VALUES (1, N'PRJ301')
INSERT [dbo].[Subject] ([subid], [subname]) VALUES (2, N'MAS291')
INSERT [dbo].[Subject] ([subid], [subname]) VALUES (3, N'IOT102')
INSERT [dbo].[Subject] ([subid], [subname]) VALUES (4, N'PRO192')
GO
INSERT [dbo].[TimeSlot] ([tid], [description]) VALUES (1, N'7h30 -9h')
INSERT [dbo].[TimeSlot] ([tid], [description]) VALUES (2, N'9h10 - 10h40')
INSERT [dbo].[TimeSlot] ([tid], [description]) VALUES (3, N'10h50 - 12h20')
INSERT [dbo].[TimeSlot] ([tid], [description]) VALUES (4, N'12h50 - 14h20')
INSERT [dbo].[TimeSlot] ([tid], [description]) VALUES (5, N'14h30 - 16h')
INSERT [dbo].[TimeSlot] ([tid], [description]) VALUES (6, N'16h10 - 17h40')
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [product_account_unique]    Script Date: 11/8/2022 11:16:48 PM ******/
ALTER TABLE [dbo].[Role_Account] ADD  CONSTRAINT [product_account_unique] UNIQUE NONCLUSTERED 
(
	[roid] ASC,
	[username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
/****** Object:  Index [product_feature_unique]    Script Date: 11/8/2022 11:16:48 PM ******/
ALTER TABLE [dbo].[Role_Feature] ADD  CONSTRAINT [product_feature_unique] UNIQUE NONCLUSTERED 
(
	[roid] ASC,
	[fid] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
ALTER TABLE [dbo].[Attandance]  WITH CHECK ADD  CONSTRAINT [FK_Attandance_Session] FOREIGN KEY([sesid])
REFERENCES [dbo].[Session] ([sesid])
GO
ALTER TABLE [dbo].[Attandance] CHECK CONSTRAINT [FK_Attandance_Session]
GO
ALTER TABLE [dbo].[Attandance]  WITH CHECK ADD  CONSTRAINT [FK_Attandance_Student] FOREIGN KEY([stdid])
REFERENCES [dbo].[Student] ([stdid])
GO
ALTER TABLE [dbo].[Attandance] CHECK CONSTRAINT [FK_Attandance_Student]
GO
ALTER TABLE [dbo].[Group]  WITH CHECK ADD  CONSTRAINT [FK_Group_Lecturer] FOREIGN KEY([lid])
REFERENCES [dbo].[Lecturer] ([lid])
GO
ALTER TABLE [dbo].[Group] CHECK CONSTRAINT [FK_Group_Lecturer]
GO
ALTER TABLE [dbo].[Group]  WITH CHECK ADD  CONSTRAINT [FK_Group_Subject] FOREIGN KEY([subid])
REFERENCES [dbo].[Subject] ([subid])
GO
ALTER TABLE [dbo].[Group] CHECK CONSTRAINT [FK_Group_Subject]
GO
ALTER TABLE [dbo].[Lecturer]  WITH CHECK ADD FOREIGN KEY([username])
REFERENCES [dbo].[Account] ([username])
GO
ALTER TABLE [dbo].[Role_Account]  WITH CHECK ADD FOREIGN KEY([username])
REFERENCES [dbo].[Account] ([username])
GO
ALTER TABLE [dbo].[Role_Account]  WITH CHECK ADD FOREIGN KEY([roid])
REFERENCES [dbo].[Role] ([roid])
GO
ALTER TABLE [dbo].[Role_Feature]  WITH CHECK ADD FOREIGN KEY([roid])
REFERENCES [dbo].[Role] ([roid])
GO
ALTER TABLE [dbo].[Role_Feature]  WITH CHECK ADD FOREIGN KEY([fid])
REFERENCES [dbo].[Feature] ([fid])
GO
ALTER TABLE [dbo].[Session]  WITH CHECK ADD  CONSTRAINT [FK_Session_Group] FOREIGN KEY([gid])
REFERENCES [dbo].[Group] ([gid])
GO
ALTER TABLE [dbo].[Session] CHECK CONSTRAINT [FK_Session_Group]
GO
ALTER TABLE [dbo].[Session]  WITH CHECK ADD  CONSTRAINT [FK_Session_Lecturer] FOREIGN KEY([lid])
REFERENCES [dbo].[Lecturer] ([lid])
GO
ALTER TABLE [dbo].[Session] CHECK CONSTRAINT [FK_Session_Lecturer]
GO
ALTER TABLE [dbo].[Session]  WITH CHECK ADD  CONSTRAINT [FK_Session_Room] FOREIGN KEY([rid])
REFERENCES [dbo].[Room] ([rid])
GO
ALTER TABLE [dbo].[Session] CHECK CONSTRAINT [FK_Session_Room]
GO
ALTER TABLE [dbo].[Session]  WITH CHECK ADD  CONSTRAINT [FK_Session_TimeSlot] FOREIGN KEY([tid])
REFERENCES [dbo].[TimeSlot] ([tid])
GO
ALTER TABLE [dbo].[Session] CHECK CONSTRAINT [FK_Session_TimeSlot]
GO
ALTER TABLE [dbo].[Student]  WITH CHECK ADD FOREIGN KEY([username])
REFERENCES [dbo].[Account] ([username])
GO
ALTER TABLE [dbo].[Student_Group]  WITH CHECK ADD  CONSTRAINT [FK_Student_Group_Group] FOREIGN KEY([gid])
REFERENCES [dbo].[Group] ([gid])
GO
ALTER TABLE [dbo].[Student_Group] CHECK CONSTRAINT [FK_Student_Group_Group]
GO
ALTER TABLE [dbo].[Student_Group]  WITH CHECK ADD  CONSTRAINT [FK_Student_Group_Student] FOREIGN KEY([stdid])
REFERENCES [dbo].[Student] ([stdid])
GO
ALTER TABLE [dbo].[Student_Group] CHECK CONSTRAINT [FK_Student_Group_Student]
GO
USE [master]
GO
ALTER DATABASE [prj] SET  READ_WRITE 
GO
