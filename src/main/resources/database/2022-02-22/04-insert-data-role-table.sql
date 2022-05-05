--liquibase formatted sql
--changeset lukasz_matusik:4_insert_data_role

USE [${database.name}]
GO

SET IDENTITY_INSERT [${schema.name}].[MS_Role] ON
INSERT [${schema.name}].[MS_Role] ([id], [role]) VALUES (1, N'ROLE_DIRECTOR')
INSERT [${schema.name}].[MS_Role] ([id], [role]) VALUES (2, N'ROLE_ADMIN')
INSERT [${schema.name}].[MS_Role] ([id], [role]) VALUES (3, N'ROLE_MANAGER')
INSERT [${schema.name}].[MS_Role] ([id], [role]) VALUES (4, N'ROLE_OFFICE_WORKER')
SET IDENTITY_INSERT [${schema.name}].[MS_Role] OFF
GO

