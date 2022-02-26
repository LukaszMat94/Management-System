--liquibase formatted sql
--changeset lukasz_matusik:3_insert_data_laptop

USE [Management System]
GO

SET IDENTITY_INSERT [dbo].[MS_Laptop] ON

INSERT [dbo].[MS_Laptop] ([idLaptop], [brandLaptop], [loginLaptop], [nameLaptop], [passwordLaptop]) VALUES (1, N'Asus', N'Asus1234', N'AsusAS11', N'wMQcxsOkhzwkSI/b2IV4AQ==')
INSERT [dbo].[MS_Laptop] ([idLaptop], [brandLaptop], [loginLaptop], [nameLaptop], [passwordLaptop]) VALUES (2, N'Asus', N'Asus1235', N'AsusAS12', N'kQtI6NTUMbkkSI/b2IV4AQ==')
INSERT [dbo].[MS_Laptop] ([idLaptop], [brandLaptop], [loginLaptop], [nameLaptop], [passwordLaptop]) VALUES (3, N'Asus', N'Asus1236', N'AsusAS13', N'rt/8nedhbY4kSI/b2IV4AQ==')
INSERT [dbo].[MS_Laptop] ([idLaptop], [brandLaptop], [loginLaptop], [nameLaptop], [passwordLaptop]) VALUES (4, N'Asus', N'Asus1237', N'AsusAS14', N'gx/LJhPANVAkSI/b2IV4AQ==')
INSERT [dbo].[MS_Laptop] ([idLaptop], [brandLaptop], [loginLaptop], [nameLaptop], [passwordLaptop]) VALUES (5, N'Acer', N'Acer1234', N'AcerAC11', N'R/OVJ+sMxuEkSI/b2IV4AQ==')
INSERT [dbo].[MS_Laptop] ([idLaptop], [brandLaptop], [loginLaptop], [nameLaptop], [passwordLaptop]) VALUES (6, N'Acer', N'Acer1235', N'AcerAC12', N'saOhrKPGg4kkSI/b2IV4AQ==')
INSERT [dbo].[MS_Laptop] ([idLaptop], [brandLaptop], [loginLaptop], [nameLaptop], [passwordLaptop]) VALUES (7, N'Acer', N'Acer1236', N'AcerAC13', N'7LzxV22nZm4kSI/b2IV4AQ==')
INSERT [dbo].[MS_Laptop] ([idLaptop], [brandLaptop], [loginLaptop], [nameLaptop], [passwordLaptop]) VALUES (8, N'Acer', N'Acer1237', N'AcerAC14', N'8ztFcV0AvC4kSI/b2IV4AQ==')
INSERT [dbo].[MS_Laptop] ([idLaptop], [brandLaptop], [loginLaptop], [nameLaptop], [passwordLaptop]) VALUES (9, N'Msi', N'Msi12345', N'MsiMS11', N'4SqRb9cJ00EkSI/b2IV4AQ==')
INSERT [dbo].[MS_Laptop] ([idLaptop], [brandLaptop], [loginLaptop], [nameLaptop], [passwordLaptop]) VALUES (10, N'Msi', N'Msi12346', N'MsiMS12', N'tEYZAn4OyyokSI/b2IV4AQ==')
INSERT [dbo].[MS_Laptop] ([idLaptop], [brandLaptop], [loginLaptop], [nameLaptop], [passwordLaptop]) VALUES (11, N'Msi', N'Msi12347', N'MsiMS13', N'dMPO8Yuep6AkSI/b2IV4AQ==')
INSERT [dbo].[MS_Laptop] ([idLaptop], [brandLaptop], [loginLaptop], [nameLaptop], [passwordLaptop]) VALUES (12, N'Msi', N'Msi12348', N'MsiMS14', N'y9/ijJhx8AIkSI/b2IV4AQ==')
INSERT [dbo].[MS_Laptop] ([idLaptop], [brandLaptop], [loginLaptop], [nameLaptop], [passwordLaptop]) VALUES (13, N'Lenovo', N'Lenovo11', N'LenovoLE11', N'Fz2QK5S2D2okSI/b2IV4AQ==')
INSERT [dbo].[MS_Laptop] ([idLaptop], [brandLaptop], [loginLaptop], [nameLaptop], [passwordLaptop]) VALUES (14, N'Lenovo', N'Lenovo12', N'LenovoLE12', N'Lzte1KCe5GokSI/b2IV4AQ==')
INSERT [dbo].[MS_Laptop] ([idLaptop], [brandLaptop], [loginLaptop], [nameLaptop], [passwordLaptop]) VALUES (15, N'Lenovo', N'Lenovo13', N'LenovoLE13', N'KiFttCb59oEkSI/b2IV4AQ==')
INSERT [dbo].[MS_Laptop] ([idLaptop], [brandLaptop], [loginLaptop], [nameLaptop], [passwordLaptop]) VALUES (16, N'Lenovo', N'Lenovo14', N'LenovoLE14', N'NMRQ0pO8PBokSI/b2IV4AQ==')
INSERT [dbo].[MS_Laptop] ([idLaptop], [brandLaptop], [loginLaptop], [nameLaptop], [passwordLaptop]) VALUES (17, N'Toshiba', N'Toshiba11', N'ToshibaTO11', N'yBFRka3dpz9AGrt8h8dGSA==')
INSERT [dbo].[MS_Laptop] ([idLaptop], [brandLaptop], [loginLaptop], [nameLaptop], [passwordLaptop]) VALUES (18, N'Toshiba', N'Toshiba12', N'ToshibaTO12', N'yBFRka3dpz+dvUitGwPOaA==')
INSERT [dbo].[MS_Laptop] ([idLaptop], [brandLaptop], [loginLaptop], [nameLaptop], [passwordLaptop]) VALUES (19, N'Toshiba', N'Toshiba13', N'ToshibaTO13', N'yBFRka3dpz85P5ysys1c0w==')
INSERT [dbo].[MS_Laptop] ([idLaptop], [brandLaptop], [loginLaptop], [nameLaptop], [passwordLaptop]) VALUES (20, N'Toshiba', N'Toshiba14', N'ToshibaTO14', N'yBFRka3dpz8bdZV9leybrg==')
INSERT [dbo].[MS_Laptop] ([idLaptop], [brandLaptop], [loginLaptop], [nameLaptop], [passwordLaptop]) VALUES (21, N'Samsung', N'Samsung11', N'SamsungSA11', N'h5KqW5ALrrxAGrt8h8dGSA==')
INSERT [dbo].[MS_Laptop] ([idLaptop], [brandLaptop], [loginLaptop], [nameLaptop], [passwordLaptop]) VALUES (22, N'Samsung', N'Samsung12', N'SamsungSA12', N'h5KqW5ALrrydvUitGwPOaA==')
INSERT [dbo].[MS_Laptop] ([idLaptop], [brandLaptop], [loginLaptop], [nameLaptop], [passwordLaptop]) VALUES (23, N'Samsung', N'Samsung13', N'SamsungSA13', N'h5KqW5ALrrw5P5ysys1c0w==')
INSERT [dbo].[MS_Laptop] ([idLaptop], [brandLaptop], [loginLaptop], [nameLaptop], [passwordLaptop]) VALUES (24, N'Samsung', N'Samsung14', N'SamsungSA14', N'h5KqW5ALrrwbdZV9leybrg==')
INSERT [dbo].[MS_Laptop] ([idLaptop], [brandLaptop], [loginLaptop], [nameLaptop], [passwordLaptop]) VALUES (25, N'Dell', N'Dell1234', N'DellDE11', N'cr3n6sQkBzMkSI/b2IV4AQ==')
INSERT [dbo].[MS_Laptop] ([idLaptop], [brandLaptop], [loginLaptop], [nameLaptop], [passwordLaptop]) VALUES (26, N'Dell', N'Dell1235', N'DellDE12', N'oOk5uB+beRAkSI/b2IV4AQ==')
INSERT [dbo].[MS_Laptop] ([idLaptop], [brandLaptop], [loginLaptop], [nameLaptop], [passwordLaptop]) VALUES (27, N'Dell', N'Dell1236', N'DellDE13', N'n07IgcE7UdUkSI/b2IV4AQ==')
INSERT [dbo].[MS_Laptop] ([idLaptop], [brandLaptop], [loginLaptop], [nameLaptop], [passwordLaptop]) VALUES (28, N'Dell', N'Dell1237', N'DellDE14', N'wNsGmojxyDEkSI/b2IV4AQ==')
INSERT [dbo].[MS_Laptop] ([idLaptop], [brandLaptop], [loginLaptop], [nameLaptop], [passwordLaptop]) VALUES (29, N'Apple', N'Apple1234', N'AppleAP11', N'PbXXsixSp9sbdZV9leybrg==')
INSERT [dbo].[MS_Laptop] ([idLaptop], [brandLaptop], [loginLaptop], [nameLaptop], [passwordLaptop]) VALUES (30, N'Apple', N'Apple1235', N'AppleAP12', N'PbXXsixSp9vEdpP7p6Vgig==')
INSERT [dbo].[MS_Laptop] ([idLaptop], [brandLaptop], [loginLaptop], [nameLaptop], [passwordLaptop]) VALUES (31, N'Apple', N'Apple1236', N'AppleAP13', N'PbXXsixSp9ufqPvnZRjqTw==')
INSERT [dbo].[MS_Laptop] ([idLaptop], [brandLaptop], [loginLaptop], [nameLaptop], [passwordLaptop]) VALUES (32, N'Apple', N'Apple1237', N'AppleAP14', N'PbXXsixSp9segBRcdD+OGA==')
SET IDENTITY_INSERT [dbo].[MS_Laptop] OFF
GO


