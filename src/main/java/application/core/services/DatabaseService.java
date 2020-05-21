package application.core.services;

import application.core.database.MessageDatabase;
import application.core.database.repositories.MessageRepository;
import application.core.domain.Message;
import application.core.exceptions.BadRequestExceptions.InvalidIdException;
import application.core.exceptions.NotFoundExceptions.MessageNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class DatabaseService
{
	private static MessageDatabase messageDatabase;

	public DatabaseService(MessageRepository messageRepository) {
		DatabaseService.messageDatabase = new MessageDatabase(messageRepository);
	}

	public static void add(Message message) {
		messageDatabase.save(message);
	}
	public static void add(Map<String, String> message)	{
		messageDatabase.save(new Message(message.get("text"), message.get("tag")));
	}

	public static ArrayList<Message> findAllMessages() {
		return messageDatabase.findAll();
	}
	public static List<Map<String, String>> findAllMessagesAsMapList() {
		ArrayList<Message> messages = findAllMessages();
		if(messages.isEmpty())
			return null;
		List<Map<String, String>> list = new ArrayList<>();
		for (Message message : messages)
			list.add(message.asHashMap());
		return list;
	}
	public static Message findMessageById(Long id)
	{
		return messageDatabase.findById(id);
	}

	public static void deleteMessage(Long id)
	{
		messageDatabase.delete(id);
	}

	public static void update(Long id, Message msg)
	{
		messageDatabase.update(id, msg);
	}
}
