--liquibase formatted sql
--changeset lukasz_matusik:8

SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[MS_Employee_Task](
                                         [id_emp] [int] NOT NULL,
                                         [id_task] [int] NOT NULL
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[MS_Employee_Task]  WITH CHECK ADD  CONSTRAINT [FK58ivshx91k8u2fxbfk3801wwd] FOREIGN KEY([id_emp])
    REFERENCES [dbo].[MS_Employee] ([idEmployee])
GO

ALTER TABLE [dbo].[MS_Employee_Task] CHECK CONSTRAINT [FK58ivshx91k8u2fxbfk3801wwd]
GO

ALTER TABLE [dbo].[MS_Employee_Task]  WITH CHECK ADD  CONSTRAINT [FKbmbdoh9jbm3ix4cp14sstvqpw] FOREIGN KEY([id_task])
    REFERENCES [dbo].[MS_Task] ([idTask])
GO

ALTER TABLE [dbo].[MS_Employee_Task] CHECK CONSTRAINT [FKbmbdoh9jbm3ix4cp14sstvqpw]
GO



