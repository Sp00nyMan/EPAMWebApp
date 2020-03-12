package application.core.Services;

import application.core.Database.MessageDatabase;
import application.core.domain.Message;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class DatabaseService
{
	MessageDatabase messageDatabase = new MessageDatabase();


	public void addMessage(Message message)
	{
		messageDatabase.save(message);
	}
	public void addMessage(Map<String, String> message)
	{
		messageDatabase.save(new Message(message.get("text"), message.get("tag")));
	}

	public ArrayList<Message> findAllMessages()
	{
		return messageDatabase.findAll();
	}
	public List<Map<String, String>> findAllMessagesAsMapList()
	{
		ArrayList<Message> messages = messageDatabase.findAll();
		List<Map<String, String>> list = new ArrayList<>();
		if(messages.isEmpty())
		{
			//return null;
			messageDatabase.save(new Message("TEST1", "test"));
			messageDatabase.save(new Message("TEST2", "test"));
			messageDatabase.save(new Message("TEST3", "test"));
		}
		for (Message message : messages)
		{
			list.add(message.asHashMap());
		}
		return list;
	}
	public Message findMessageById(Long id)
	{
		return messageDatabase.findById(id);
	}
}
