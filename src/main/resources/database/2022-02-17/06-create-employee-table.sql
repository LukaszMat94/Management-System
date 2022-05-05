--liquibase formatted sql
--changeset lukasz_matusik:6_create_employee_table

USE [${database.name}]
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [${schema.name}].[MS_Employee](
                                    [idEmployee] [int] IDENTITY(1,1) NOT NULL,
                                    [name] [nvarchar](50) NULL,
                                    [surname] [nvarchar](50) NULL,
                                    [email] [varchar](50) NULL,
                                    [salary] [decimal](19, 2) NULL,
                                    [personalIdentityNumber] [varchar](11) NULL,
                                    [birthday] [datetime2](7) NOT NULL,
                                    [employmentDate] [datetime2](7) NOT NULL,
                                    [dismissalDate] [datetime2](7) NULL,
                                    [idAccount] [int] NULL,
                                    [idJob] [int] NULL,
                                    [idLaptop] [int] NULL,
                                    PRIMARY KEY CLUSTERED
                                        (
                                         [idEmployee] ASC
                                            )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
                                    CONSTRAINT [email_constraint_unique] UNIQUE NONCLUSTERED
                                        (
                                         [email] ASC
                                            )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
                                    CONSTRAINT [personalIdentityNumber_constraint_unique] UNIQUE NONCLUSTERED
                                        (
                                         [personalIdentityNumber] ASC
                                            )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [${schema.name}].[MS_Employee]  WITH CHECK ADD  CONSTRAINT [FK_idAccount] FOREIGN KEY([idAccount])
    REFERENCES [${schema.name}].[MS_Account] ([idAccount])
GO
ALTER TABLE [${schema.name}].[MS_Employee] CHECK CONSTRAINT [FK_idAccount]
GO
ALTER TABLE [${schema.name}].[MS_Employee]  WITH CHECK ADD  CONSTRAINT [FK_idJob] FOREIGN KEY([idJob])
    REFERENCES [${schema.name}].[MS_Job] ([id])
GO
ALTER TABLE [${schema.name}].[MS_Employee] CHECK CONSTRAINT [FK_idJob]
GO
ALTER TABLE [${schema.name}].[MS_Employee]  WITH CHECK ADD  CONSTRAINT [FK_idLaptop] FOREIGN KEY([idLaptop])
    REFERENCES [${schema.name}].[MS_Laptop] ([idLaptop])
GO
ALTER TABLE [${schema.name}].[MS_Employee] CHECK CONSTRAINT [FK_idLaptop]
GO
ALTER TABLE [${schema.name}].[MS_Employee]  WITH CHECK ADD  CONSTRAINT [email_constraint] CHECK  (([email] like '%_@_%._%'))
GO
ALTER TABLE [${schema.name}].[MS_Employee] CHECK CONSTRAINT [email_constraint]
GO
ALTER TABLE [${schema.name}].[MS_Employee]  WITH CHECK ADD  CONSTRAINT [personalIdentityNumber_constraint] CHECK  ((len([personalIdentityNumber])=(11)))
GO
ALTER TABLE [${schema.name}].[MS_Employee] CHECK CONSTRAINT [personalIdentityNumber_constraint]
GO
ALTER TABLE [${schema.name}].[MS_Employee]  WITH CHECK ADD  CONSTRAINT [salary_constraint] CHECK  (([salary]>=(0)))
GO
ALTER TABLE [${schema.name}].[MS_Employee] CHECK CONSTRAINT [salary_constraint]
GO