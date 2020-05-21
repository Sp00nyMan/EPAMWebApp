package application.core.database;

import application.core.database.repositories.MessageRepository;
import application.core.domain.Message;
import application.core.exceptions.BadRequestExceptions.BadRequestException;
import application.core.exceptions.BadRequestExceptions.InvalidIdException;
import application.core.exceptions.NotFoundExceptions.MessageNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class MessageDatabase
{
	private final MessageRepository messages;

	public MessageDatabase(MessageRepository messages) {
		this.messages = messages;
	}

	public void save(Message message)
	{
		if(message.getText() == null || message.getTag() == null)
			throw new BadRequestException("Empty fields aren't allowed!");
		messages.save(message);
	}

	public ArrayList<Message> findAll()
	{
		return new ArrayList<>((Collection<Message>) messages.findAll());
	}

	public Message findById(Long id)
	{
		if(id < 0)
			throw new InvalidIdException(id);
		if(!messages.existsById(id))
			throw new MessageNotFoundException(id);
		return messages.getById(id);
	}

	public void delete(Long id)
	{
		messages.delete(findById(id));
	}

	@Transactional(rollbackFor = BadRequestException.class)
	public void update(Long id, Message msg)
	{
		Message message = findById(id);
		if(message == null)
			throw new MessageNotFoundException(id);
		message.setText(msg.getText());
		message.setTag(msg.getTag());
		save(message);
	}
}
