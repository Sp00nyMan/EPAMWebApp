package application.core.database.repositories;

import application.core.domain.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepository extends CrudRepository<Message, Long>
{
	Message getById(Long id);
}
