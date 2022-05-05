--liquibase formatted sql
--changeset lukasz_matusik:7_insert_data_employee_role

USE [${database.name}]
GO
INSERT [${schema.name}].[MS_Employee_Role] ([id_emp], [id_role]) VALUES (1, 4)
INSERT [${schema.name}].[MS_Employee_Role] ([id_emp], [id_role]) VALUES (2, 1)
INSERT [${schema.name}].[MS_Employee_Role] ([id_emp], [id_role]) VALUES (3, 2)
INSERT [${schema.name}].[MS_Employee_Role] ([id_emp], [id_role]) VALUES (4, 3)
INSERT [${schema.name}].[MS_Employee_Role] ([id_emp], [id_role]) VALUES (5, 1)
INSERT [${schema.name}].[MS_Employee_Role] ([id_emp], [id_role]) VALUES (6, 3)
INSERT [${schema.name}].[MS_Employee_Role] ([id_emp], [id_role]) VALUES (7, 4)
INSERT [${schema.name}].[MS_Employee_Role] ([id_emp], [id_role]) VALUES (8, 2)
INSERT [${schema.name}].[MS_Employee_Role] ([id_emp], [id_role]) VALUES (9, 2)
INSERT [${schema.name}].[MS_Employee_Role] ([id_emp], [id_role]) VALUES (10, 3)
INSERT [${schema.name}].[MS_Employee_Role] ([id_emp], [id_role]) VALUES (11, 3)
INSERT [${schema.name}].[MS_Employee_Role] ([id_emp], [id_role]) VALUES (12, 4)
INSERT [${schema.name}].[MS_Employee_Role] ([id_emp], [id_role]) VALUES (13, 3)
INSERT [${schema.name}].[MS_Employee_Role] ([id_emp], [id_role]) VALUES (14, 4)
GO
