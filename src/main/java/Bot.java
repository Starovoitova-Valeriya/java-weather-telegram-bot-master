import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Bot extends TelegramLongPollingBot {

    public static void main(String[] args) throws TelegramApiException {

        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        try {
            telegramBotsApi.registerBot(new Bot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


    public void sendMsg(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);

        sendMessage.setChatId(message.getChatId());

        sendMessage.setReplyToMessageId(message.getMessageId());

        sendMessage.setText(text);
        try {
            setButtons(sendMessage); // putting buttons in message
            execute(sendMessage);
        } catch (TelegramApiException e) {
        }
    }
    public void onUpdateReceived(Update update) { // for messages receiving through LongPoll
        Model model = new Model();
        Message message = update.getMessage();
        if(message !=null && message.hasText()){
            switch (message.getText()){
                case "/start" : sendMsg(message, "Hi! I'm bot that shows the weather, pls write your city in English and I'll show your the weather");
                        break;
                case "hi" : sendMsg(message, "Hi! I'm bot that shows the weather, pls write your city in English and I'll show your the weather");
                    break;
                case "hello" : sendMsg(message, "Hi! I'm bot that shows the weather, pls write your city in English and I'll show your the weather");
                    break;
                case "привет" : sendMsg(message, "Hi! I'm bot that shows the weather, pls write your city in English and I'll show your the weather");
                    break;
                case "погода" : sendMsg(message, "Hi! I'm bot that shows the weather, pls write your city in English and I'll show your the weather");
                    break;
                case "weather" : sendMsg(message, "Hi! I'm bot that shows the weather, pls write your city in English and I'll show your the weather");
                    break;
                case "Hi" : sendMsg(message, "Hi! I'm bot that shows the weather, pls write your city in English and I'll show your the weather");
                    break;
                case "Hello" : sendMsg(message, "Hi! I'm bot that shows the weather, pls write your city in English and I'll show your the weather");
                    break;
                case "Привет" : sendMsg(message, "Hi! I'm bot that shows the weather, pls write your city in English and I'll show your the weather");
                    break;
                case "Погода" : sendMsg(message, "Hi! I'm bot that shows the weather, pls write your city in English and I'll show your the weather");
                    break;
                case "Weather" : sendMsg(message, "Hi! I'm bot that shows the weather, pls write your city in English and I'll show your the weather");
                    break;
                case "/help" : sendMsg(message, "You should type in the name of the city in English and get the result");
                    break;
                case "/about" : sendMsg(message, "Bot made by Valeryia Starovoitova. Bot takes weather from OpenWeather.");
                    break;
                default:
                    try {
                        sendMsg(message, Weather.getWeather(message.getText(), model));
                    } catch (IOException e) {
                        sendMsg(message, "This city no founded, please check the data");
                    }
            }
        }
    }


    public void setButtons(SendMessage sendMessage){
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup(); // new keyboard
        sendMessage.setReplyMarkup(replyKeyboardMarkup); // buttons = message
        replyKeyboardMarkup.setSelective(true); // for whom
        replyKeyboardMarkup.setResizeKeyboard(true);// buttons amount
        replyKeyboardMarkup.setOneTimeKeyboard(false);// show or hide keyboard after message

        List<KeyboardRow> keyboardRowList = new ArrayList<>(); // making buttons
        KeyboardRow keyboardFirstRow = new KeyboardRow();

        keyboardFirstRow.add(new KeyboardButton("/help"));
        keyboardFirstRow.add(new KeyboardButton("/about"));

        keyboardRowList.add(keyboardFirstRow);
        replyKeyboardMarkup.setKeyboard(keyboardRowList);
    }

    public String getBotUsername() { // return botUserName
        return "Weather_Graduate_Project";
    }

    public String getBotToken() { // token, when made a bot
        return "5438633081:AAFK3xQ9XFNslbsxdgmvNSxiHPf4NgTJkYY";
    }
}
