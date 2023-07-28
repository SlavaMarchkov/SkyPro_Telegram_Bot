package pro.sky.telegrambot.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.NotificationTaskService;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class TelegramBotUpdatesListener implements UpdatesListener {

    private static final Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);
    private final NotificationTaskService service;
    private final TelegramBot telegramBot;

    @Autowired
    public TelegramBotUpdatesListener(final NotificationTaskService service, final TelegramBot telegramBot) {
        this.service = service;
        this.telegramBot = telegramBot;
    }

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            if (update.message().text() != null) {
                Long chatId = update.message().chat().id();
                String text = update.message().text();
                if (text.equals("/start")) {
                    sendMessage("Привет, я таск-бот. Введи время и название задачи в виде: 01.01.2022 20:00 Сделать домашнюю работу, и я пришлю тебе напоминалку", chatId);
                } else {
                    Pattern pattern = Pattern.compile("([0-9.:\\s]{16})(\\s)(\\W+)");
                    Matcher matcher = pattern.matcher(text);
                    if (matcher.matches()) {
                        String date = matcher.group(1);
                        String item = matcher.group(3);
                        service.create(chatId, date, item);
                        sendMessage("Задача успешно добавлена", chatId);
                    } else {
                        sendMessage("Сообщение должно иметь вид: 01.01.2022 20:00 Сделать домашнюю работу", chatId);
                    }
                }
            }
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

    private void sendMessage(final String text, final Long chatId) {
        SendMessage message = new SendMessage(chatId, text);
        SendResponse response = telegramBot.execute(message);
        logger.info("Response: {}", response.isOk());
        logger.info("Error code: {}", response.errorCode());
    }

}
