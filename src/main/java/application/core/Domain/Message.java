package application.core.Domain;

import application.core.Exceptions.BadRequestExceptions.BadRequestException;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.HashMap;

@Entity
@ApiModel(description = "All details about Message")
public class Message
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ApiModelProperty(notes = "Text of the message")
	private String text;
	@ApiModelProperty(notes = "Message's tag")
	private String tag;

	public Message(){}
	public Message(String text, String tag)	{
		this.text = text;
		this.tag = tag;
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getText()
	{
		return text;
	}

	public void setText(String text)
	{
		if(text == null)
			throw new BadRequestException("Invalid Request: cannot find field \"text\"");
		this.text = text;
	}

	public String getTag()
	{
		return tag;
	}

	public void setTag(String tag)
	{
		if(text == null)
			throw new BadRequestException("Invalid Request: cannot find field \"tag\"");
		this.tag = tag;
	}

	public HashMap<String, String> asHashMap()
	{
		HashMap<String, String> message = new HashMap<>()
		{{
			put("id", id.toString());
			put("text", text);
			put("tag", tag);
		}};
		return message;
	}
}
