package application.core.Controller;

import application.core.Exceptions.NotFoundException;
import application.core.Services.DatabaseService;
import application.core.domain.Message;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("message")
public class MessageController
{
	private DatabaseService service = new DatabaseService();

	@GetMapping
	public List<Map<String, String>> main()
	{
		List<Map<String, String>> messages = service.findAllMessagesAsMapList();
		return messages;
	}
	@GetMapping("{id}")
	public Map<String, String> getMessageById(@PathVariable String id)
	{
		Message message = service.findMessageById(Long.parseLong(id));
		if(message == null)
			throw new NotFoundException();
		return message.asHashMap();
	}
	@PostMapping
	public Map<String, String> addMsg(@RequestBody Map<String, String> message)
	{
		service.addMessage(message);
		List<Map<String, String>> messages = service.findAllMessagesAsMapList();
		return messages.get(messages.size() - 1);
	}
}
