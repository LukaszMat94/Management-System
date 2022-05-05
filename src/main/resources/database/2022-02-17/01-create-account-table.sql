--liquibase formatted sql
--changeset lukasz_matusik:1_create_account_table

USE [${database.name}]
GO

SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [${schema.name}].[MS_Account](
                                   [idAccount] [int] IDENTITY(1,1) NOT NULL,
                                   [login] [varchar](255) NOT NULL,
                                   [password] [varchar](255) NULL,
                                   PRIMARY KEY CLUSTERED
                                       (
                                        [idAccount] ASC
                                           )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
                                   CONSTRAINT [login_constraint_unique] UNIQUE NONCLUSTERED
                                       (
                                        [login] ASC
                                           )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

ALTER TABLE [${schema.name}].[MS_Account]  WITH CHECK ADD  CONSTRAINT [login_constraint] CHECK  ((len([login])>=(8) AND NOT [login] like '%[^a-zA-Z0-9]%'))
GO

ALTER TABLE [${schema.name}].[MS_Account] CHECK CONSTRAINT [login_constraint]
GO

