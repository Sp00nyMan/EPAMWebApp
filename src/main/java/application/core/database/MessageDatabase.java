package application.core.database;

import application.core.database.repositories.MessageRepository;
import application.core.domain.Message;

import java.util.ArrayList;
import java.util.Collection;

public class MessageDatabase
{
	private MessageRepository messages;

	public void save(Message message)
	{
		messages.save(message);
	}

	public ArrayList<Message> findAll()
	{
		return new ArrayList((Collection)messages.findAll());
	}
	public Message findById(Long id)
	{
		return messages.getById(id);
	}
	public void delete(Long id)
	{
		messages.delete(findById(id));
	}
}
