package application.core.services;

import application.core.database.MessageDatabase;
import application.core.exceptions.BadRequestExceptions.InvalidIdException;
import application.core.exceptions.NotFoundExceptions.MessageNotFoundException;
import application.core.domain.Message;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class DatabaseService
{
	static MessageDatabase messageDatabase = new MessageDatabase();


	public static void addMessage(Message message)
	{
		messageDatabase.save(message);
	}
	public static void addMessage(Map<String, String> message)
	{
		messageDatabase.save(new Message(message.get("text"), message.get("tag")));
	}

	public static ArrayList<Message> findAllMessages()
	{
		return messageDatabase.findAll();
	}
	public static List<Map<String, String>> findAllMessagesAsMapList()
	{
		ArrayList<Message> messages = findAllMessages();
		List<Map<String, String>> list = new ArrayList<>();
		if(messages.isEmpty())
		{
			return null;
		}
		for (Message message : messages)
		{
			list.add(message.asHashMap());
		}
		return list;
	}
	public static Message findMessageById(Long id)
	{
		if(id < 0)
			throw new InvalidIdException(id);
		Message message = messageDatabase.findById(id);
		if(message == null)
			throw new MessageNotFoundException(id);
		return message;
	}

	public static void deleteMessage(Long id)
	{
		messageDatabase.delete(id);
	}
}
