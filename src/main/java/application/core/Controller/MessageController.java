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
	public List<Map<String, String>> main(Map<String, Object> model)
	{
		List<Map<String, String>> messages = service.findAllMessagesAsMapList();
		model.put("messages", messages);
		return messages;
	}
	@PostMapping
	public List<Map<String, String>> addMsg(Map<String, Object> model, @RequestParam String text, @RequestParam String tag)
	{
		service.addMessage(new Message(text, tag));
		List<Map<String, String>> messages = service.findAllMessagesAsMapList();
		model.put("messages", messages);
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
}
