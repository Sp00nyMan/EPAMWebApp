package application.core.Database;

import application.core.Database.repositories.MessageCollection;
import application.core.Domain.Message;

import java.util.ArrayList;

public class MessageDatabase
{
	private MessageCollection messages = new MessageCollection();

	public void save(Message message)
	{
		messages.save(message);
	}

	public ArrayList<Message> findAll()
	{
		return messages.messages();
	}
	public Message findById(Long id)
	{
		return messages.getById(id);
	}
	public void delete(Long id)
	{
		messages.remove(id);
	}
}
