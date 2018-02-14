import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.toIntExact;

public class TextBot extends TelegramLongPollingBot {

    @Override
    public void onUpdateReceived(Update update) {
        SendMessage sendMessage = new SendMessage().setChatId(update.getMessage().getChatId());

        if(update.hasCallbackQuery()) {
            manageCallbacks(update);
        }else {

            switch (update.getMessage().getText()) {

                case "/start":
                    sendMessage.setText("Hello " + update.getMessage().getFrom().getFirstName() + ". This is a autogenerated message.");
                    break;
                case "How are you?":
                    sendMessage.setText("I am fine, what about you?");
                    break;
                case "Android":
                    sendMessage.setText("Android Selected");
                    break;
                case "IOS":
                    sendMessage.setText("IOS Selected");
                    break;
                case "Web":
                    sendMessage.setText("Web Selected");
                    break;
                case "Social":
                    sendMessage.setText("Social Selected");
                    break;
                case "SEO":
                    sendMessage.setText("SEO Selected");
                    break;
                case "/options":
                    sendMessage.setText("Options");
                   sendCustomKeyboard(update.getMessage().getChatId().toString());
                    break;
                default:
                    sendMessage.setText("invalid input!!");
                    break;
            }
        }

        try {

                sendMessage(sendMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void manageCallbacks(Update update) {
        String call_data = update.getCallbackQuery().getData();

        SendMessage sendMessage=new SendMessage().setChatId(update.getMessage().getChatId());

        switch (call_data) {
            case "Android":
                sendMessage.setText("Android Selected");
                break;
            case "IOS":
                sendMessage.setText("IOS Selected");
                break;
            case "Web":
                sendMessage.setText("Web Selected");
                break;
            case "Social":
                sendMessage.setText("Social Selected");
                break;
            case "SEO":
                sendMessage.setText("SEO Selected");
                break;
        }

        try {
            if (sendMessage.getText() != null)
                sendMessage(sendMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendCustomKeyboard(String chatId) {
        SendMessage message = new SendMessage() // Create a message object object
                .setChatId(chatId);

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();
        rowInline.add(new InlineKeyboardButton().setText("Android").setCallbackData("Android"));
        rowInline.add(new InlineKeyboardButton().setText("IOS").setCallbackData("IOS"));
        rowInline.add(new InlineKeyboardButton().setText("Web").setCallbackData("Web"));
        rowInline.add(new InlineKeyboardButton().setText("Social").setCallbackData("Social"));
        rowInline.add(new InlineKeyboardButton().setText("SEO").setCallbackData("SEO"));
        // Set the keyboard to the markup
        rowsInline.add(rowInline);
        // Add it to the message
        markupInline.setKeyboard(rowsInline);
        message.setReplyMarkup(markupInline);
        try {
            if (message.getText() != null)
            sendMessage(message); // Sending our message object to user
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return null;
    }

    @Override
    public String getBotToken() {
        return "525409959:AAFx0oxgU42WRugZ9w-FolhIZJxYmif87mU";
    }
}
