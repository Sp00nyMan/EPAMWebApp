package application.core.Database.repositories;

import application.core.domain.Message;

import java.util.ArrayList;

public class MessageCollection
{
	private ArrayList<Message> messages = new ArrayList<>();
	public MessageCollection(){
	}
	public ArrayList<Message> messages()
	{
		return messages;
	}
	public void save(Message msg)
	{
		if(messages.isEmpty())
			msg.setId((long) 0);
		else
			msg.setId(messages.get(messages.size() - 1).getId() + 1);
		messages.add(msg);
	}
	public Message getById(Long id)
	{
		for (Message message : messages)
			if(message.getId() == id)
				return message;
		return null;
	}
}
