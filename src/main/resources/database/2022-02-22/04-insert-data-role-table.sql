--liquibase formatted sql
--changeset lukasz_matusik:4_insert_data_role

USE [Management System]
GO

SET IDENTITY_INSERT [dbo].[MS_Role] ON
INSERT [dbo].[MS_Role] ([id], [role]) VALUES (1, N'ROLE_DIRECTOR')
INSERT [dbo].[MS_Role] ([id], [role]) VALUES (2, N'ROLE_ADMIN')
INSERT [dbo].[MS_Role] ([id], [role]) VALUES (3, N'ROLE_MANAGER')
INSERT [dbo].[MS_Role] ([id], [role]) VALUES (4, N'ROLE_OFFICE_WORKER')
SET IDENTITY_INSERT [dbo].[MS_Role] OFF
GO

