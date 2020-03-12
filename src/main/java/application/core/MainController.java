package application.core;

import application.core.Services.DatabaseService;
import application.core.domain.Message;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class MainController
{
	private DatabaseService service = new DatabaseService();

	@GetMapping
	public String main(Map<String, Object> model)
	{
		Iterable<Message> messages = service.findAllMessages();
		model.put("messages", messages);
		return "mainPage";
	}
	@PostMapping
	public String addMsg(Map<String, Object> model, @RequestParam String text, @RequestParam String tag)
	{
		service.addMessage(new Message(text, tag));
		Iterable<Message> messages = service.findAllMessages();
		model.put("messages", messages);
		return "mainPage";
	}
}
