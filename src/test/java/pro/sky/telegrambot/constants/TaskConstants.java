package pro.sky.telegrambot.constants;

import pro.sky.telegrambot.entity.NotificationTask;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TaskConstants {
    public static final LocalDateTime TIME_TASK_1 = LocalDateTime.parse("2023-07-31 21:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    public static final LocalDateTime TIME_TASK_2 = LocalDateTime.parse("2023-07-31 21:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    public static final LocalDateTime TIME_TASK_3 = LocalDateTime.parse("2023-07-31 21:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    public static final NotificationTask TASK_1 = new NotificationTask(1L, 1L, "Задание для первого пользователя", TIME_TASK_1);
    public static final NotificationTask TASK_2 = new NotificationTask(2L, 2L, "Задание для второго пользователя", TIME_TASK_2);
    public static final NotificationTask TASK_3 = new NotificationTask(3L, 3L, "Задание для третьего пользователя", TIME_TASK_3);

    public static final List<NotificationTask> TASK_LIST = List.of(
            TASK_1,
            TASK_2,
            TASK_3
    );

}
