package application.core.Controller;

import application.core.Exceptions.NotFoundExceptions.NotFoundException;
import application.core.Services.DatabaseService;
import application.core.Domain.Message;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("message")
@Api(value = "Messages management controller", description = "Operations with messages")
public class MessageController
{
	private DatabaseService service = new DatabaseService();

	@ApiOperation(value = "View all messages", response = List.class)
	@GetMapping("all")
	public ResponseEntity<List<Map<String, String>>> all()
	{
		List<Map<String, String>> messages = service.findAllMessagesAsMapList();
		if(messages == null)
			throw new NotFoundException("No messages found");
		return new ResponseEntity<>(messages, HttpStatus.OK);
	}
	@ApiOperation(value = "Get message by id")
	@GetMapping("{id}")
	public ResponseEntity<Map<String, String>> getMessageById(@PathVariable String id)
	{
		Message message = service.findMessageById(Long.parseLong(id));
		return new ResponseEntity<>(message.asHashMap(), HttpStatus.OK);
	}

	@ApiOperation(value = "Add new message")
	@PostMapping
	public ResponseEntity<Map<String, String>> addMsg(
		@ApiParam(value = "Message that contains text and tag", required = true)
		@RequestBody Message message)
	{
		service.addMessage(message);
		List<Map<String, String>> messages = service.findAllMessagesAsMapList();
		return new ResponseEntity<>(messages.get(messages.size() - 1), HttpStatus.CREATED);
	}

	@ApiOperation(value = "Edit message by id")
	@PutMapping("{id}")
	public ResponseEntity<Map<String, String>> editMsg(@PathVariable String id, @ApiParam @RequestBody Message msg)
	{
		Message message = service.findMessageById(Long.parseLong(id));
		message.setText(msg.getText());
		message.setTag(msg.getTag());
		return new ResponseEntity<>(message.asHashMap(), HttpStatus.OK);
	}

	@ApiOperation(value = "Delete message by id")
	@DeleteMapping("{id}")
	public void deleteMsg(@PathVariable String id)
	{
		service.deleteMessage(Long.parseLong(id));
	}
}
