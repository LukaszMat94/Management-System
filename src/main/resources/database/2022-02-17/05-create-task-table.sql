--liquibase formatted sql
--changeset lukasz_matusik:5_create_task_table

USE [${database.name}]
GO

SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [${schema.name}].[MS_Task](
                                [idTask] [int] IDENTITY(1,1) NOT NULL,
                                [name] [varchar](255) NULL,
                                [description] [varchar](255) NULL,
                                [startDate] [datetime2](7) NULL,
                                [endDate] [datetime2](7) NULL,
                                PRIMARY KEY CLUSTERED
                                    (
                                     [idTask] ASC
                                        )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO


