package application.core;

import application.core.domain.Message;
import application.core.repositories.MessageRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MainController
{
	private final MessageRepository messageRepository;

	public MainController(MessageRepository messageRepository) {this.messageRepository = messageRepository;}

	@GetMapping
	public String main(Map<String, Object> model)
	{
		Iterable<Message> messages = messageRepository.findAll();
		model.put("messages", messages);
		return "mainPage";
	}
	@PostMapping
	public String addMsg(Map<String, Object> model, @RequestParam String text, @RequestParam String tag)
	{
		messageRepository.save(new Message(text, tag));
		Iterable<Message> messages = messageRepository.findAll();
		model.put("messages", messages);
		return "mainPage";
	}
}
