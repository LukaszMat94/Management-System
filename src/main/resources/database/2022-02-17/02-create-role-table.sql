--liquibase formatted sql
--changeset lukasz_matusik:2_create_role_table

USE [Management System]
GO

SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[MS_Role](
                                [id] [int] IDENTITY(1,1) NOT NULL,
                                [role] [varchar](255) NOT NULL,
                                PRIMARY KEY CLUSTERED
                                    (
                                     [id] ASC
                                        )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
                                CONSTRAINT [role_constraint_unique] UNIQUE NONCLUSTERED
                                    (
                                     [role] ASC
                                        )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[MS_Role]  WITH CHECK ADD  CONSTRAINT [role_constraint] CHECK  (([role] like 'ROLE[_]%'))
GO

ALTER TABLE [dbo].[MS_Role] CHECK CONSTRAINT [role_constraint]
GO


