package application.core.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class MainController
{
	@GetMapping
	public void main(HttpServletResponse response) throws IOException
	{
		response.sendRedirect("/message/all");
	}
}
