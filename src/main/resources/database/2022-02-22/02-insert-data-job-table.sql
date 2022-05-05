--liquibase formatted sql
--changeset lukasz_matusik:2_insert_data_job

USE [${database.name}]
GO
SET IDENTITY_INSERT [${schema.name}].[MS_Job] ON

INSERT [${schema.name}].[MS_Job] ([id], [job]) VALUES (1, N'Head Director')
INSERT [${schema.name}].[MS_Job] ([id], [job]) VALUES (2, N'IT Director')
INSERT [${schema.name}].[MS_Job] ([id], [job]) VALUES (3, N'IT Manager')
INSERT [${schema.name}].[MS_Job] ([id], [job]) VALUES (4, N'Financial Manager')
INSERT [${schema.name}].[MS_Job] ([id], [job]) VALUES (5, N'Office Manager')
INSERT [${schema.name}].[MS_Job] ([id], [job]) VALUES (6, N'Marketing and Sales Manager')
INSERT [${schema.name}].[MS_Job] ([id], [job]) VALUES (7, N'IT Admin')
INSERT [${schema.name}].[MS_Job] ([id], [job]) VALUES (8, N'IT Employee')
INSERT [${schema.name}].[MS_Job] ([id], [job]) VALUES (9, N'Java Programmer')
INSERT [${schema.name}].[MS_Job] ([id], [job]) VALUES (10, N'C# Programmer')
INSERT [${schema.name}].[MS_Job] ([id], [job]) VALUES (11, N'Python Programmer')
INSERT [${schema.name}].[MS_Job] ([id], [job]) VALUES (12, N'C++ Programmer')
INSERT [${schema.name}].[MS_Job] ([id], [job]) VALUES (13, N'JS Programmer')
INSERT [${schema.name}].[MS_Job] ([id], [job]) VALUES (14, N'Kotlin Programmer')
INSERT [${schema.name}].[MS_Job] ([id], [job]) VALUES (15, N'Scala Programmer')
INSERT [${schema.name}].[MS_Job] ([id], [job]) VALUES (16, N'Project Manager')
INSERT [${schema.name}].[MS_Job] ([id], [job]) VALUES (17, N'Security Specialist')
SET IDENTITY_INSERT [${schema.name}].[MS_Job] OFF
GO


