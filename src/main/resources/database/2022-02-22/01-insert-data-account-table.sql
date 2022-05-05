--liquibase formatted sql
--changeset lukasz_matusik:1_insert_data_account

USE [${database.name}]
GO

SET IDENTITY_INSERT [${schema.name}].[MS_Account] ON

INSERT [${schema.name}].[MS_Account] ([idAccount], [login], [password]) VALUES (1, N'employee001', N'$2a$10$EiPojlbm7ei2VNkHaEjO.OMwCq6rp4riuq8T6trX1k.lSy.3yDDIW')
INSERT [${schema.name}].[MS_Account] ([idAccount], [login], [password]) VALUES (2, N'employee002', N'$2a$10$NI/Ka8jT4Xy7q7jfnsnXw.A3E/.wNW3xO8HfrxYTRA/RfDXm9EhP.')
INSERT [${schema.name}].[MS_Account] ([idAccount], [login], [password]) VALUES (3, N'employee003', N'$2a$10$A/pR6JiYK9FRKmvcDbRePezdeeGAfXi.kSYLnEpaBT/6.O8Tmssye')
INSERT [${schema.name}].[MS_Account] ([idAccount], [login], [password]) VALUES (4, N'employee004', N'$2a$10$NBL0c/PbcNOq1Jkj1iYlB.RpLFTapU7C0Sx2eHt84MbUoos4265AG')
INSERT [${schema.name}].[MS_Account] ([idAccount], [login], [password]) VALUES (5, N'employee005', N'$2a$10$8Rh9HU/Z5aIEvcO7.XVKiuc3I4PoQ2UhtzN48UUERgeQkr5da5/hW')
INSERT [${schema.name}].[MS_Account] ([idAccount], [login], [password]) VALUES (6, N'employee006', N'$2a$10$XXsViS030r1dJmxioVDc3e9q./SBmZusiEaQaAeyLHMOHuoTpS7BO')
INSERT [${schema.name}].[MS_Account] ([idAccount], [login], [password]) VALUES (7, N'employee007', N'$2a$10$HbBYaqQjwOV29VzrWIu5.urqbZv4iDoFsN9kovND2f9nzzjutP9Re')
INSERT [${schema.name}].[MS_Account] ([idAccount], [login], [password]) VALUES (8, N'employee008', N'$2a$10$KLHBNEYd9EhBUjJWIV85fODnX82blLt5onDnKJpPALCFZyOAbJdeO')
INSERT [${schema.name}].[MS_Account] ([idAccount], [login], [password]) VALUES (9, N'employee009', N'$2a$10$PnyW0yNuoBEpLzurEyrE9.N32N3FvCcSXEQd0FyEzqSTTONTSt.ga')
INSERT [${schema.name}].[MS_Account] ([idAccount], [login], [password]) VALUES (10, N'employee010', N'$2a$10$MiArGz4FfPj53FclqMBYxeL.ypTaBX5NlLjK3oPl0HvTWU7w.X2gS')
INSERT [${schema.name}].[MS_Account] ([idAccount], [login], [password]) VALUES (11, N'employee011', N'$2a$10$XxsNPwDO/lQ6BwkFtfopSOpNJztMYxaQr1IfSBIT3NIfukKW52HHW')
INSERT [${schema.name}].[MS_Account] ([idAccount], [login], [password]) VALUES (12, N'employee012', N'$2a$10$Z5Gdd8DXvUZETgx9ZioH2OECvBNUJ8kX.B2mAN6oWQHAGYXYef51.')
INSERT [${schema.name}].[MS_Account] ([idAccount], [login], [password]) VALUES (13, N'employee013', N'$2a$10$vWbeTagowwZSNx26fj7I4eu1Ysx0gZCBbr9ySXQZeKrgh4ksfzY6G')
INSERT [${schema.name}].[MS_Account] ([idAccount], [login], [password]) VALUES (14, N'employee014', N'$2a$10$Zj2LpjZgBhqc/GTIiA7gouiuzH6xrUwyjQOZmqw.qQV7YQqzGuJ.e')
SET IDENTITY_INSERT [${schema.name}].[MS_Account] OFF
GO

