--liquibase formatted sql
--changeset lukasz_matusik:1_insert_data_account

USE [Management System]
GO

SET IDENTITY_INSERT [dbo].[MS_Account] ON

INSERT [dbo].[MS_Account] ([idAccount], [login], [password]) VALUES (1, N'employee001', N'81Ju0sOlEHoO4CgMl6my6Q==')
INSERT [dbo].[MS_Account] ([idAccount], [login], [password]) VALUES (2, N'employee002', N'81Ju0sOlEHrbs2ngOqnU6Q==')
INSERT [dbo].[MS_Account] ([idAccount], [login], [password]) VALUES (3, N'employee003', N'81Ju0sOlEHoYoACgbDwIOg==')
INSERT [dbo].[MS_Account] ([idAccount], [login], [password]) VALUES (4, N'employee004', N'81Ju0sOlEHrx3OudjIcw7w==')
INSERT [dbo].[MS_Account] ([idAccount], [login], [password]) VALUES (5, N'employee005', N'81Ju0sOlEHoJHOerpaxg4w==')
INSERT [dbo].[MS_Account] ([idAccount], [login], [password]) VALUES (6, N'employee006', N'81Ju0sOlEHqFcnpMFLJg3g==')
INSERT [dbo].[MS_Account] ([idAccount], [login], [password]) VALUES (7, N'employee007', N'81Ju0sOlEHrCHDD6a5hWYg==')
INSERT [dbo].[MS_Account] ([idAccount], [login], [password]) VALUES (8, N'employee008', N'81Ju0sOlEHqjyTiei5kKGw==')
INSERT [dbo].[MS_Account] ([idAccount], [login], [password]) VALUES (9, N'employee009', N'81Ju0sOlEHpeAOqLjoHTaA==')
INSERT [dbo].[MS_Account] ([idAccount], [login], [password]) VALUES (10, N'employee010', N'81Ju0sOlEHrzj0U+pB3DRA==')
INSERT [dbo].[MS_Account] ([idAccount], [login], [password]) VALUES (11, N'employee011', N'81Ju0sOlEHqODtSqmYwKtw==')
INSERT [dbo].[MS_Account] ([idAccount], [login], [password]) VALUES (12, N'employee012', N'81Ju0sOlEHoc+WI295LnsQ==')
INSERT [dbo].[MS_Account] ([idAccount], [login], [password]) VALUES (13, N'employee013', N'81Ju0sOlEHo5XCZ40lP5uQ==')
INSERT [dbo].[MS_Account] ([idAccount], [login], [password]) VALUES (14, N'employee014', N'81Ju0sOlEHoJ7i0yRuCgzA==')
SET IDENTITY_INSERT [dbo].[MS_Account] OFF
GO

