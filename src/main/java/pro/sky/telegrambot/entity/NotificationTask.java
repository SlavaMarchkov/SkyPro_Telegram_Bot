package pro.sky.telegrambot.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "notification_task")
public class NotificationTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long chatId;
    private String taskText;
    private LocalDateTime taskTime;

    public NotificationTask() {

    }

    public NotificationTask(final Long id, final Long chatId, final String taskText, final LocalDateTime taskTime) {
        this.id = id;
        this.chatId = chatId;
        this.taskText = taskText;
        this.taskTime = taskTime;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(final Long chatId) {
        this.chatId = chatId;
    }

    public String getTaskText() {
        return taskText;
    }

    public void setTaskText(final String taskText) {
        this.taskText = taskText;
    }

    public LocalDateTime getTaskTime() {
        return taskTime;
    }

    public void setTaskTime(final LocalDateTime taskTime) {
        this.taskTime = taskTime;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final NotificationTask that = (NotificationTask) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "NotificationTask{" +
                "id=" + id +
                ", chatId=" + chatId +
                ", taskText='" + taskText + '\'' +
                ", taskTime=" + taskTime +
                '}';
    }

}
