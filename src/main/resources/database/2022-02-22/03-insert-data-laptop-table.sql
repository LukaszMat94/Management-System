--liquibase formatted sql
--changeset lukasz_matusik:3_insert_data_laptop

USE [${database.name}]
GO

SET IDENTITY_INSERT [${schema.name}].[MS_Laptop] ON

INSERT [${schema.name}].[MS_Laptop] ([idLaptop], [brandLaptop], [loginLaptop], [nameLaptop], [passwordLaptop]) VALUES (1, N'Asus', N'Asus1234', N'AsusAS11', N'$2a$10$4MKt3DtoDL8i94QEsTvyFesEPSaPJN/PTkHT4UADI/rNlYJcJPWtG')
INSERT [${schema.name}].[MS_Laptop] ([idLaptop], [brandLaptop], [loginLaptop], [nameLaptop], [passwordLaptop]) VALUES (2, N'Asus', N'Asus1235', N'AsusAS12', N'$2a$10$IHS.RbGhGCf.WZbqU4wc.e3.pk2RoJAEUcEQ/xqEQZT778HHXcXeO')
INSERT [${schema.name}].[MS_Laptop] ([idLaptop], [brandLaptop], [loginLaptop], [nameLaptop], [passwordLaptop]) VALUES (3, N'Asus', N'Asus1236', N'AsusAS13', N'$2a$10$vf2ksiNtD1pUAk5XPqMS9.mb4YhNutAjHHb/q9R40LzKG1v06TMp6')
INSERT [${schema.name}].[MS_Laptop] ([idLaptop], [brandLaptop], [loginLaptop], [nameLaptop], [passwordLaptop]) VALUES (4, N'Asus', N'Asus1237', N'AsusAS14', N'$2a$10$qldI244wLUECXNnWBOWQLex8oy9Lp3nFEXDZ4lcrc1sNzIylQKFSu')
INSERT [${schema.name}].[MS_Laptop] ([idLaptop], [brandLaptop], [loginLaptop], [nameLaptop], [passwordLaptop]) VALUES (5, N'Acer', N'Acer1234', N'AcerAC11', N'$2a$10$TfhJqorKuHOsQ0GKQEmoru6JIX927weRa8T/.9wl8xh9rjoKASHvy')
INSERT [${schema.name}].[MS_Laptop] ([idLaptop], [brandLaptop], [loginLaptop], [nameLaptop], [passwordLaptop]) VALUES (6, N'Acer', N'Acer1235', N'AcerAC12', N'$2a$10$dGP7KkFXOlSXK2w3Z3Ec1.A.bohZFu8OBvW/r8tDpIMLgecu4qIsK')
INSERT [${schema.name}].[MS_Laptop] ([idLaptop], [brandLaptop], [loginLaptop], [nameLaptop], [passwordLaptop]) VALUES (7, N'Acer', N'Acer1236', N'AcerAC13', N'$2a$10$xkGwkRd45FTEJgzA.G7wIOyyuOYKhkyfmgXdnP/sccCi7KxoLxPzO')
INSERT [${schema.name}].[MS_Laptop] ([idLaptop], [brandLaptop], [loginLaptop], [nameLaptop], [passwordLaptop]) VALUES (8, N'Acer', N'Acer1237', N'AcerAC14', N'$2a$10$LsrrxDBKNKCZixZDBx03lO.qm/02j7C5NXji668QC5LIa9uP4hszK')
INSERT [${schema.name}].[MS_Laptop] ([idLaptop], [brandLaptop], [loginLaptop], [nameLaptop], [passwordLaptop]) VALUES (9, N'Msi', N'Msi12345', N'MsiMS11', N'$2a$10$lkuIU5gPNRTYRIhprii3yu8qTb3Gl7AmJNK3KnqT9tamHZRvUKGty')
INSERT [${schema.name}].[MS_Laptop] ([idLaptop], [brandLaptop], [loginLaptop], [nameLaptop], [passwordLaptop]) VALUES (10, N'Msi', N'Msi12346', N'MsiMS12', N'$2a$10$7cPeFJAdhjyo4BJE7N5GdeYyRelVNxf7dsNFUsEjeMBQ71JmIZzOu')
INSERT [${schema.name}].[MS_Laptop] ([idLaptop], [brandLaptop], [loginLaptop], [nameLaptop], [passwordLaptop]) VALUES (11, N'Msi', N'Msi12347', N'MsiMS13', N'$2a$10$d2z0eCbDiXesaAyXfoLSxeNI1ek/ROJvQs6jM6gK39FBofZ0ydb6S')
INSERT [${schema.name}].[MS_Laptop] ([idLaptop], [brandLaptop], [loginLaptop], [nameLaptop], [passwordLaptop]) VALUES (12, N'Msi', N'Msi12348', N'MsiMS14', N'$2a$10$RO4dpzGxMZZ9iMRBxAnLceHB7hWxfMTnXXv4tQxpgYz4W5wYMgbqy')
INSERT [${schema.name}].[MS_Laptop] ([idLaptop], [brandLaptop], [loginLaptop], [nameLaptop], [passwordLaptop]) VALUES (13, N'Lenovo', N'Lenovo11', N'LenovoLE11', N'$2a$10$1jIkhHAMfbrDYF58h8x6sOvV3fwRkVA1sb/ha8kwjK39n3ZaSU63W')
INSERT [${schema.name}].[MS_Laptop] ([idLaptop], [brandLaptop], [loginLaptop], [nameLaptop], [passwordLaptop]) VALUES (14, N'Lenovo', N'Lenovo12', N'LenovoLE12', N'$2a$10$FoMrImganWsciQx/EOVy0u0VplHB9AnnsN1SV3BnqEsYx9XtHiq9C')
INSERT [${schema.name}].[MS_Laptop] ([idLaptop], [brandLaptop], [loginLaptop], [nameLaptop], [passwordLaptop]) VALUES (15, N'Lenovo', N'Lenovo13', N'LenovoLE13', N'$2a$10$6USWMjwGDN2GrmkV.7GgzOXzRYVipevGopU0lGaSGyp4cLaoNs762')
INSERT [${schema.name}].[MS_Laptop] ([idLaptop], [brandLaptop], [loginLaptop], [nameLaptop], [passwordLaptop]) VALUES (16, N'Lenovo', N'Lenovo14', N'LenovoLE14', N'$2a$10$S6DTLV6aJmdar7Xbhm4DleA1YuvTj1kZ.vwlHyaNy49oGwSpjxAX.')
INSERT [${schema.name}].[MS_Laptop] ([idLaptop], [brandLaptop], [loginLaptop], [nameLaptop], [passwordLaptop]) VALUES (17, N'Toshiba', N'Toshiba11', N'ToshibaTO11', N'$2a$10$38qg5McPY1itgU5IWvmCH.Y0yxBekYWYsvwPX9rLfFSBqCn73fOaq')
INSERT [${schema.name}].[MS_Laptop] ([idLaptop], [brandLaptop], [loginLaptop], [nameLaptop], [passwordLaptop]) VALUES (18, N'Toshiba', N'Toshiba12', N'ToshibaTO12', N'$2a$10$JMj/jW1X2xElLYDORkldC.q80yltmavZswvUX1TorBI23xyRt3yYa')
INSERT [${schema.name}].[MS_Laptop] ([idLaptop], [brandLaptop], [loginLaptop], [nameLaptop], [passwordLaptop]) VALUES (19, N'Toshiba', N'Toshiba13', N'ToshibaTO13', N'$2a$10$/tYTCzCIbZqNdUE2wkf5c.COBrpNQn/fGBvAn3NkwfgtpHL/ftyeu')
INSERT [${schema.name}].[MS_Laptop] ([idLaptop], [brandLaptop], [loginLaptop], [nameLaptop], [passwordLaptop]) VALUES (20, N'Toshiba', N'Toshiba14', N'ToshibaTO14', N'$2a$10$fS.8Mp6KjSra7V5hBquWVOLRKfrgUuRcW5dGqhO227Ce2xq5UD3iO')
INSERT [${schema.name}].[MS_Laptop] ([idLaptop], [brandLaptop], [loginLaptop], [nameLaptop], [passwordLaptop]) VALUES (21, N'Samsung', N'Samsung11', N'SamsungSA11', N'$2a$10$bXcNdA8JCXjVjg5P/2mqquO3QWGZahWXH7nZjwmBxkJvSBMfkAeb.')
INSERT [${schema.name}].[MS_Laptop] ([idLaptop], [brandLaptop], [loginLaptop], [nameLaptop], [passwordLaptop]) VALUES (22, N'Samsung', N'Samsung12', N'SamsungSA12', N'$2a$10$1MMfPXYy8j2FQFD93ic6P.VDZjFNBDTmlFjwEJeUE1E4DYMpulSBi')
INSERT [${schema.name}].[MS_Laptop] ([idLaptop], [brandLaptop], [loginLaptop], [nameLaptop], [passwordLaptop]) VALUES (23, N'Samsung', N'Samsung13', N'SamsungSA13', N'$2a$10$T9GaBiL9dYXPcUGxTzSvK.nieO5DRPuccctV3ag22mCXNDPhpMfHS')
INSERT [${schema.name}].[MS_Laptop] ([idLaptop], [brandLaptop], [loginLaptop], [nameLaptop], [passwordLaptop]) VALUES (24, N'Samsung', N'Samsung14', N'SamsungSA14', N'$2a$10$lVYLCM2dW6INdGf23vApjunIjvmSyeKJw3T9Gvi4lL9/j1.cghYpq')
INSERT [${schema.name}].[MS_Laptop] ([idLaptop], [brandLaptop], [loginLaptop], [nameLaptop], [passwordLaptop]) VALUES (25, N'Dell', N'Dell1234', N'DellDE11', N'$2a$10$/9A.zQxXYhsuGanmqxNcYugwTqrUnZzV.Wca3PPmcc9kXyR.8bUma')
INSERT [${schema.name}].[MS_Laptop] ([idLaptop], [brandLaptop], [loginLaptop], [nameLaptop], [passwordLaptop]) VALUES (26, N'Dell', N'Dell1235', N'DellDE12', N'$2a$10$kpbgnIkCkjw0xpfVudysou9yaGA3Q6bxz7bWinZBFIe4XyAURL0Y6')
INSERT [${schema.name}].[MS_Laptop] ([idLaptop], [brandLaptop], [loginLaptop], [nameLaptop], [passwordLaptop]) VALUES (27, N'Dell', N'Dell1236', N'DellDE13', N'$2a$10$rJnkVoOdiNlLkd4/qz9S9uT8hVWqwzZi2OK38rAaImYoCJ0erWjU2')
INSERT [${schema.name}].[MS_Laptop] ([idLaptop], [brandLaptop], [loginLaptop], [nameLaptop], [passwordLaptop]) VALUES (28, N'Dell', N'Dell1237', N'DellDE14', N'$2a$10$lVE40IT5U1WQQjqZT9/0ZOe89xMWCIaO4e3KxZJGH9WFY8EmPQ5oe')
INSERT [${schema.name}].[MS_Laptop] ([idLaptop], [brandLaptop], [loginLaptop], [nameLaptop], [passwordLaptop]) VALUES (29, N'Apple', N'Apple1234', N'AppleAP11', N'$2a$10$IGU.YXCpZh.UopPZFDOju.RbXtCw.wTYWBPz3DxZek7/toY/oa.N.')
INSERT [${schema.name}].[MS_Laptop] ([idLaptop], [brandLaptop], [loginLaptop], [nameLaptop], [passwordLaptop]) VALUES (30, N'Apple', N'Apple1235', N'AppleAP12', N'$2a$10$OOUDnS/jOiTNZLoFsnxVde6yVV.nLj5QYWksWJotO132yGnkErOiK')
INSERT [${schema.name}].[MS_Laptop] ([idLaptop], [brandLaptop], [loginLaptop], [nameLaptop], [passwordLaptop]) VALUES (31, N'Apple', N'Apple1236', N'AppleAP13', N'$2a$10$4slQbaX1UW3n2oWj9x6NLOP9GhilzOoDb/7U7XIbpFDMErnObXTg6')
INSERT [${schema.name}].[MS_Laptop] ([idLaptop], [brandLaptop], [loginLaptop], [nameLaptop], [passwordLaptop]) VALUES (32, N'Apple', N'Apple1237', N'AppleAP14', N'$2a$10$liiiZghaxPZwHGdL6yZQj.cUYJ4zkEpP7RtQ5HSVzMZDM0e.PJOj2')
SET IDENTITY_INSERT [${schema.name}].[MS_Laptop] OFF
GO


