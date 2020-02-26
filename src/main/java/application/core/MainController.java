package application.core;

import application.core.domain.Message;
import application.core.repos.MessageRepo;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MainController
{
	private final MessageRepo messageRepo;

	public MainController(MessageRepo messageRepo) {this.messageRepo = messageRepo;}

	@GetMapping
	public String main(Map<String, Object> model)
	{
		Iterable<Message> messages = messageRepo.findAll();
		model.put("messages", messages);
		return "mainPage";
	}
	@PostMapping
	public String addMsg(Map<String, Object> model, @RequestParam String text, @RequestParam String tag)
	{
		//При перезагрузке страницы добавляется в ДБ лишнее сообщение (последнее)
		messageRepo.save(new Message(text, tag));
		Iterable<Message> messages = messageRepo.findAll();
		model.put("messages", messages);
		return "mainPage";
	}
}
