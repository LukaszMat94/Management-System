--liquibase formatted sql
--changeset lukasz_matusik:4_create_laptop_table

USE [Management System]
GO

SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[MS_Laptop](
                                  [idLaptop] [int] IDENTITY(1,1) NOT NULL,
                                  [nameLaptop] [varchar](20) NULL,
                                  [brandLaptop] [varchar](255) NULL,
                                  [loginLaptop] [varchar](255) NULL,
                                  [passwordLaptop] [varchar](255) NULL,
                                  PRIMARY KEY CLUSTERED
                                      (
                                       [idLaptop] ASC
                                          )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
                                  CONSTRAINT [namelaptop_constraint_unique] UNIQUE NONCLUSTERED
                                      (
                                       [nameLaptop] ASC
                                          )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[MS_Laptop]  WITH CHECK ADD  CONSTRAINT [loginlaptop_constraint] CHECK  ((len([loginLaptop])>=(8) AND NOT [loginLaptop] like '%[^a-zA-Z0-9]%'))
GO

ALTER TABLE [dbo].[MS_Laptop] CHECK CONSTRAINT [loginlaptop_constraint]
GO

ALTER TABLE [dbo].[MS_Laptop]  WITH CHECK ADD  CONSTRAINT [namelaptop_constraint_length] CHECK  (((4)<=len([nameLaptop]) AND len([nameLaptop])<=(20)))
GO

ALTER TABLE [dbo].[MS_Laptop] CHECK CONSTRAINT [namelaptop_constraint_length]
GO


