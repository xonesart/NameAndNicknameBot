package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main extends TelegramLongPollingBot {

    public static void main(String[] args) throws TelegramApiException {
        // Створення і реєстрація нового бота з використанням токену
        TelegramBotsApi api = new TelegramBotsApi(DefaultBotSession.class);
        api.registerBot(new Main());
    }

    @Override
    public String getBotUsername() {
        // Повернення імені бота
        return "AndriiBanderogusBot";
    }

    @Override
    public String getBotToken() {
        // Повернення токену бота, який отриманий від BotFather в Telegram
        return "6125655769:AAElGFQQjncAGLvRCM1XQJ00s89D-hp7I1s";
    }

    @Override
    public void onUpdateReceived(Update update) {
        // Отримання chatId з об'єкту update
        Long chatId = getChatId(update);

        // Створення повідомлення з використанням методу createMessage
        SendMessage message = createMessage("Привіт ", update);

        // Вказання chatId для повідомлення
        message.setChatId(chatId);

        // Відправка повідомлення
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private Long getChatId(Update update) {
        return update.getMessage().getChatId();

    }


    public SendMessage createMessage(String text, Update update) {
        // Створення об'єкту SendMessage з переданим текстом повідомлення
        SendMessage message = new SendMessage();

        // Отримання імені користувача та нікнейму
        String firstName = update.getMessage().getChat().getFirstName();
        String nikName = update.getMessage().getChat().getUserName();


        // Додавання імені та нікнейму користувача до тексту повідомлення
        message.setText(text + firstName + ". Твій нік в Телеграмі: " + nikName);

        // Встановлення форматування повідомлення (markdown)
        //message.setParseMode("markdown"); // помилка з символом "markdown" Телеграм його не може розпізнати
        return message;
    }
}