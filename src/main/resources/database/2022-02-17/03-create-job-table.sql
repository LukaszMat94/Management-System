--liquibase formatted sql
--changeset lukasz_matusik:3_create_job_table

USE [${database.name}]
GO

SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [${schema.name}].[MS_Job](
                               [id] [int] IDENTITY(1,1) NOT NULL,
                               [job] [varchar](255) NULL,
                               PRIMARY KEY CLUSTERED
                                   (
                                    [id] ASC
                                       )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO


