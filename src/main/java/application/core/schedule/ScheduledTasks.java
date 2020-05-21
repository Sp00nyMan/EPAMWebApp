package application.core.schedule;

import application.core.domain.Message;
import application.core.services.DatabaseService;
import org.apache.tomcat.jni.Time;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class ScheduledTasks
{
	public static final long fixedRate = Time.APR_MSEC_PER_USEC * 60 * 1; //Every 2 minutes
	@Scheduled(fixedRate = fixedRate)
	public void addNewMessage()
	{
		System.out.println(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()));
		Message message = new Message("Scheduled message №" + DatabaseService.findAllMessages().size(), "scheduled");
		DatabaseService.add(message);
	}
}
