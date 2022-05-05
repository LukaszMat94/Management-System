--liquibase formatted sql
--changeset lukasz_matusik:7_create_employee_role_table

USE [${database.name}]
GO

SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [${schema.name}].[MS_Employee_Role](
                                         [id_emp] [int] NOT NULL,
                                         [id_role] [int] NOT NULL
) ON [PRIMARY]
GO

ALTER TABLE [${schema.name}].[MS_Employee_Role]  WITH CHECK ADD  CONSTRAINT [FK39iq4w7j5ruo0k3veh0yexktl] FOREIGN KEY([id_role])
    REFERENCES [${schema.name}].[MS_Role] ([id])
GO

ALTER TABLE [${schema.name}].[MS_Employee_Role] CHECK CONSTRAINT [FK39iq4w7j5ruo0k3veh0yexktl]
GO

ALTER TABLE [${schema.name}].[MS_Employee_Role]  WITH CHECK ADD  CONSTRAINT [FK5bukgof4f0hp99fiypemrl7cy] FOREIGN KEY([id_emp])
    REFERENCES [${schema.name}].[MS_Employee] ([idEmployee])
GO

ALTER TABLE [${schema.name}].[MS_Employee_Role] CHECK CONSTRAINT [FK5bukgof4f0hp99fiypemrl7cy]
GO


