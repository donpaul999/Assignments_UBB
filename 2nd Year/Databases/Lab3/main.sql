USE [Book Library]
GO

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
ALTER PROCEDURE [dbo].[main]
	-- Add the parameters for the stored procedure here
	@newVersion varchar(30)
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
	DECLARE @nextStep varchar(30)
	DECLARE @currentVersion INT
	SET @currentVersion = (SELECT id_version FROM Version)

	if ISNUMERIC(@newVersion) != 1
	BEGIN
		print('Not a number')
		return 1 
	END
	
	SET @newVersion = cast(@newVersion as INT)
	if @newVersion < 0 or @newVersion > 5
	BEGIN
		print('Invalid number')
		return 2 
	END

	while @currentVersion < @newVersion
	begin
		SET @currentVersion = @currentVersion + 1
		SET @nextStep = 'up_to_version_' + convert(varchar(3), @currentVersion)
		execute @nextStep
	end

	while @currentVersion > @newVersion
	begin
		SET @currentVersion = @currentVersion - 1
		SET @nextStep = 'down_to_version_' + convert(varchar(3), @currentVersion)
		execute @nextStep
	end

	truncate table Version
	insert into Version values(@newVersion)
END