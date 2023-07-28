package pro.sky.telegrambot;

import org.springframework.stereotype.Service;
import pro.sky.telegrambot.entity.NotificationTask;
import pro.sky.telegrambot.repository.NotificationTaskRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class NotificationTaskService {

    private final NotificationTaskRepository repository;

    public NotificationTaskService(final NotificationTaskRepository repository) {
        this.repository = repository;
    }

    public NotificationTask create(Long chatId, String taskTime, String text) {
        LocalDateTime dateTime = LocalDateTime.parse(taskTime, DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));

        NotificationTask task = new NotificationTask();
        task.setChatId(chatId);
        task.setTaskTime(dateTime);
        task.setTaskText(text);

        return repository.save(task);
    }

}
