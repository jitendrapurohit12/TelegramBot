import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;

public class Main {

    public static void main(String args[]){

        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi=new TelegramBotsApi();

        try{
            telegramBotsApi.registerBot(new TextBot());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
