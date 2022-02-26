--liquibase formatted sql
--changeset lukasz_matusik:5_insert_data_task

USE [Management System]
GO

SET IDENTITY_INSERT [dbo].[MS_Task] ON

INSERT [dbo].[MS_Task] ([idTask], [description], [endDate], [name], [startDate]) VALUES (1, N'Order Laptops with Intel i9 processors', CAST(N'2022-02-28T10:00:00.0000000' AS DateTime2), N'Order Laptops', CAST(N'2022-02-25T10:00:00.0000000' AS DateTime2))
INSERT [dbo].[MS_Task] ([idTask], [description], [endDate], [name], [startDate]) VALUES (2, N'Order mechanical keyboards', CAST(N'2022-02-28T10:00:00.0000000' AS DateTime2), N'Order Keyboards', CAST(N'2022-02-25T10:00:00.0000000' AS DateTime2))
INSERT [dbo].[MS_Task] ([idTask], [description], [endDate], [name], [startDate]) VALUES (3, N'Order optical mouses', CAST(N'2022-02-28T10:00:00.0000000' AS DateTime2), N'Order Mouses', CAST(N'2022-02-25T10:00:00.0000000' AS DateTime2))
INSERT [dbo].[MS_Task] ([idTask], [description], [endDate], [name], [startDate]) VALUES (4, N'Code review - project Satellite-Tracker', CAST(N'2022-03-25T11:00:00.0000000' AS DateTime2), N'Code review', CAST(N'2022-03-25T10:00:00.0000000' AS DateTime2))
INSERT [dbo].[MS_Task] ([idTask], [description], [endDate], [name], [startDate]) VALUES (5, N'Code review - project Bookstore-System', CAST(N'2022-03-25T13:00:00.0000000' AS DateTime2), N'Code review', CAST(N'2022-03-25T12:00:00.0000000' AS DateTime2))
INSERT [dbo].[MS_Task] ([idTask], [description], [endDate], [name], [startDate]) VALUES (6, N'Code review - project AIS-System', CAST(N'2022-03-25T16:00:00.0000000' AS DateTime2), N'Code review', CAST(N'2022-03-25T15:00:00.0000000' AS DateTime2))
INSERT [dbo].[MS_Task] ([idTask], [description], [endDate], [name], [startDate]) VALUES (7, N'Scrum meeting with Team X', CAST(N'2022-03-23T14:00:00.0000000' AS DateTime2), N'Scrum meeting', CAST(N'2022-03-23T12:00:00.0000000' AS DateTime2))
INSERT [dbo].[MS_Task] ([idTask], [description], [endDate], [name], [startDate]) VALUES (8, N'Scrum meeting with Team Y', CAST(N'2022-03-22T12:00:00.0000000' AS DateTime2), N'Scrum meeting', CAST(N'2022-03-22T10:00:00.0000000' AS DateTime2))
INSERT [dbo].[MS_Task] ([idTask], [description], [endDate], [name], [startDate]) VALUES (9, N'Develop registration feature to project X', CAST(N'2022-03-21T10:00:00.0000000' AS DateTime2), N'Registration Feature', CAST(N'2022-03-18T10:00:00.0000000' AS DateTime2))
INSERT [dbo].[MS_Task] ([idTask], [description], [endDate], [name], [startDate]) VALUES (10, N'Develop tracker feature to project Y', CAST(N'2022-03-18T10:00:00.0000000' AS DateTime2), N'Tracker Feature', CAST(N'2022-03-16T10:00:00.0000000' AS DateTime2))
INSERT [dbo].[MS_Task] ([idTask], [description], [endDate], [name], [startDate]) VALUES (11, N'Develop crud feature to project Z', CAST(N'2022-03-16T10:00:00.0000000' AS DateTime2), N'CRUD Feature', CAST(N'2022-03-15T10:00:00.0000000' AS DateTime2))
INSERT [dbo].[MS_Task] ([idTask], [description], [endDate], [name], [startDate]) VALUES (12, N'Recrutation Meeting - Java Developers', CAST(N'2022-03-15T12:00:00.0000000' AS DateTime2), N'Recrutation Meeting', CAST(N'2022-03-15T10:00:00.0000000' AS DateTime2))
INSERT [dbo].[MS_Task] ([idTask], [description], [endDate], [name], [startDate]) VALUES (13, N'Recrutation Meeting - C# Developers', CAST(N'2022-03-14T12:00:00.0000000' AS DateTime2), N'Recrutation Meeting', CAST(N'2022-03-14T10:00:00.0000000' AS DateTime2))
INSERT [dbo].[MS_Task] ([idTask], [description], [endDate], [name], [startDate]) VALUES (14, N'Recrutation Meeting - Python Developers', CAST(N'2022-03-13T12:00:00.0000000' AS DateTime2), N'Recrutation Meeting', CAST(N'2022-03-13T10:00:00.0000000' AS DateTime2))
INSERT [dbo].[MS_Task] ([idTask], [description], [endDate], [name], [startDate]) VALUES (15, N'Create UI for Project X', CAST(N'2022-03-30T10:00:00.0000000' AS DateTime2), N'Create UI', CAST(N'2022-03-26T10:00:00.0000000' AS DateTime2))
INSERT [dbo].[MS_Task] ([idTask], [description], [endDate], [name], [startDate]) VALUES (16, N'Create UI for Project Y', CAST(N'2022-03-30T10:00:00.0000000' AS DateTime2), N'Create UI', CAST(N'2022-03-26T10:00:00.0000000' AS DateTime2))
SET IDENTITY_INSERT [dbo].[MS_Task] OFF
GO

