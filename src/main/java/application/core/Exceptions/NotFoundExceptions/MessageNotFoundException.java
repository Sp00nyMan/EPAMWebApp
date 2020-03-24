package application.core.Exceptions.NotFoundExceptions;

public class MessageNotFoundException extends NotFoundException
{
	public MessageNotFoundException(Long id)
	{
		super("Message with id \"" + id + "\" doesn't exit!");
	}
}
