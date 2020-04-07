package application.core.exceptions.BadRequestExceptions;

public class InvalidIdException extends BadRequestException
{
	public InvalidIdException(Long id)
	{
		super("Id \"" + id + "\" is invalid!");
	}
}
