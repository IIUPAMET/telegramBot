package org.example.telegram;

import org.apache.log4j.Logger;
import org.example.telegram.commands.HelpCommand;
import org.example.telegram.commands.StartCommand;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

public class TelegramBot extends TelegramLongPollingCommandBot {

    private final Logger logger = Logger.getLogger(TelegramBot.class);

    private final String BOT_NAME = "fili_mono_bot";
    private final String BOT_TOKEN = "5104647841:AAEoDU-MPEiOytwe4ankwiU63sBz5YDGjmY";


    public TelegramBot() {
        register(new StartCommand("start", "Start your work with bot"));
        register(new HelpCommand("help", "Give more info to user"));
    }

    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    @Override
    public void processNonCommandUpdate(Update update) {
        setAnswer(update.getMessage().getChatId(), "hi", update.getMessage().getText());
        logger.debug("new Update received");
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }


    /**
     * Формирование имени пользователя
     *
     * @param msg сообщение
     */
    private String getUserName(Message msg) {
        User user = msg.getFrom();
        String userName = user.getUserName();
        return (userName != null) ? userName : String.format("%s %s", user.getLastName(), user.getFirstName());
    }

    /**
     * Отправка ответа
     *
     * @param chatId   id чата
     * @param userName имя пользователя
     * @param text     текст ответа
     */
    private void setAnswer(Long chatId, String userName, String text) {
        SendMessage answer = new SendMessage();
        answer.setText(text);
        answer.setChatId(chatId.toString());

        try {
            execute(answer);
        } catch (TelegramApiException e) {
            //логируем сбой Telegram Bot API, используя userName
        }
    }
}
