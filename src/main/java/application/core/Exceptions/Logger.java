package application.core.Exceptions;

import java.time.format.DateTimeFormatter;

public class Logger
{
	private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-mm-dd HH:mm:ss");
	private static final String logFilePath = "log.txt";
	public static void write(String message)
	{
		//TODO
	}
}
