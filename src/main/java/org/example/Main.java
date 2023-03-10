package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main extends TelegramLongPollingBot {

    public static void main(String[] args) throws TelegramApiException {
        // ��������� � ��������� ������ ���� � ������������� ������
        TelegramBotsApi api = new TelegramBotsApi(DefaultBotSession.class);
        api.registerBot(new Main());
    }

    @Override
    public String getBotUsername() {
        // ���������� ���� ����
        return "AndriiBanderogusBot";
    }

    @Override
    public String getBotToken() {
        // ���������� ������ ����, ���� ��������� �� BotFather � Telegram
        return "6125655769:AAElGFQQjncAGLvRCM1XQJ00s89D-hp7I1s";
    }

    @Override
    public void onUpdateReceived(Update update) {
        // ��������� chatId � ��'���� update
        Long chatId = getChatId(update);

        // ��������� ����������� � ������������� ������ createMessage
        SendMessage message = createMessage("����� ", update);

        // �������� chatId ��� �����������
        message.setChatId(chatId);

        // ³������� �����������
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
        // ��������� ��'���� SendMessage � ��������� ������� �����������
        SendMessage message = new SendMessage();

        // ��������� ���� ����������� �� �������
        String firstName = update.getMessage().getChat().getFirstName();
        String nikName = update.getMessage().getChat().getUserName();


        // ��������� ���� �� ������� ����������� �� ������ �����������
        message.setText(text + firstName + ". ��� �� � ��������: " + nikName);

        // ������������ ������������ ����������� (markdown)
        //message.setParseMode("markdown"); // ������� � �������� "markdown" �������� ���� �� ���� ���������
        return message;
    }
}