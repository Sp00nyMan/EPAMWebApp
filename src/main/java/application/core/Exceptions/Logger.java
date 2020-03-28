package application.core.Exceptions;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger
{
	private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-mm-dd HH:mm:ss");
	private static final File logFile = new File("logs/log.txt");
	public static void write(String message)
	{
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(logFile)))
		{
			writer.write(dateTimeFormatter.format(LocalDateTime.now()) + " " + message);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

	}
}
