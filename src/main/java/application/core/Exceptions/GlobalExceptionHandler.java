package application.core.Exceptions;

import application.core.Exceptions.BadRequestExceptions.BadRequestException;
import application.core.Exceptions.NotFoundExceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler
{
	@ExceptionHandler(value = NotFoundException.class)
	public ResponseEntity<Object> handleNotFound(RuntimeException exception)
	{
		writeLogMessage(exception.getMessage());
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(value = BadRequestException.class)
	public ResponseEntity<Object> handleBadRequest(RuntimeException exception)
	{
		writeLogMessage(exception.getMessage());
		return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
	}
	private void writeLogMessage(String message)
	{
		String dt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());
		System.out.println(dt+ " " + message);
		Logger.write(message);
	}
}