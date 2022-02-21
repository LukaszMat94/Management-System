--liquibase formatted sql
--changeset lukasz_matusik:5

SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[MS_Task](
                                [idTask] [int] IDENTITY(1,1) NOT NULL,
                                [description] [varchar](255) NULL,
                                [endDate] [datetime2](7) NULL,
                                [name] [varchar](255) NULL,
                                [startDate] [datetime2](7) NULL,
                                PRIMARY KEY CLUSTERED
                                    (
                                     [idTask] ASC
                                        )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO


