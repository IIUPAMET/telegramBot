package org.example.telegram.commands;

import org.apache.log4j.Logger;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;

public class StartCommand extends AbstractCommand {
    Logger logger = Logger.getLogger(StartCommand.class);

    public StartCommand(String commandIdentifier, String description) {
        super(commandIdentifier, description);
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        logger.info(String.format("User: %s. start executing command %s", user.getId(),
                this.getCommandIdentifier()));
        sendAnswer(absSender, chat.getId(), this.getCommandIdentifier(), user.getUserName(),
                "Lets start! if you need more info about me press /help");
        logger.info(String.format("User: %s. end executing command %s", user.getId(),
                this.getCommandIdentifier()));
    }
}
