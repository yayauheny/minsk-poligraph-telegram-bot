package by.yayauheny.printbot.bot;

import by.yayauheny.printbot.dto.ProductDto;
import by.yayauheny.printbot.mapper.ProductMapper;
import by.yayauheny.printbot.service.MessageFactory;
import by.yayauheny.printbot.service.ProductService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

@Log4j2
@Component
public class MinskPoligraphBot extends TelegramLongPollingBot {

    private static final String START = "/start";
    private static final String SERVICES = "/services";
    private static final String FAQ = "/faq";
    private static final String HELP = "/help";

    @Autowired
    private MessageFactory messageFactory;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductMapper productMapper;

    public MinskPoligraphBot(@Value("${bot.token}") String botToken) {
        super(botToken);
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (!update.hasMessage() || !update.getMessage().hasText()) {
            return;
        }
        String message = update.getMessage().getText();
        Long chatId = update.getMessage().getChatId();
        switch (message) {
            case START -> startCommand(chatId);
            case SERVICES -> getAvailableServicesCommand(chatId);
            case FAQ -> faqCommand(chatId);
            case HELP -> helpCommand(chatId);
            default -> unknownCommand(chatId);
        }
    }

    private void unknownCommand(Long chatId) {
        String response = "Не удалось распознать команду";
        sendMessage(chatId, response);
    }

    private void faqCommand(Long chatId) {
        String response = """
                Вопрос: Каким способом можно оплатить ваши услуги физическом лицу?
                        
                Вы можете оплатить услуги центра любым удобным для Вас способом:
                1. При получении в павильоне MinskPoligraph: наличными или любой банковской картой. 
                В том числе в наших павильонах доступна оплата картой Халва, рассрочка составит 1 месяц.
                2. При получении наличными курьеру. Доставка курьером доступна только по г. Минск.""";
        sendMessage(chatId, response);
    }

    @Override
    public String getBotUsername() {
        return "MinskPoligraphBot";
    }

    private void startCommand(Long chatId) {
        String startMessage = """
                 Добро пожаловать в бот Minsk Poligraph!
                \s
                Здесь Вы сможете найти часто задаваемые вопросы и актуальный список услуг, который предоставляет наш печатный центр
                \s
                Воспользуйтесь командами:
                %s - список услуг
                %s - часто задаваемые вопросы (FAQ)
                \s
                Дополнительные команды:
                %s - получение справки
                  """.formatted(SERVICES, FAQ, HELP);
        sendMessage(chatId, startMessage);
    }

    private void getAvailableServicesCommand(Long chatId) {
        StringBuilder sb = new StringBuilder();
        List<ProductDto> products = productMapper.toDtoList(productService.findAllAvailableServices());
        products.forEach(
                product -> {
                    sb.append(messageFactory.convert(product));
                    sb.append("\n\n");
                }
        );

        sendMessage(chatId, sb.toString());
    }

    private void helpCommand(Long chatId) {
        var text = """
                Справочная информация по боту
                                
                Для получения необходимой информации воспользуйтесь командами:
                %s - список услуг
                %s - часто задаваемые вопросы (FAQ)
                """.formatted(SERVICES, FAQ);
        sendMessage(chatId, text);
    }

    private void sendMessage(Long chatId, String message) {
        String chatIdStr = String.valueOf(chatId);
        SendMessage sendMessage = new SendMessage(chatIdStr, message);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            log.error("Cannot send message", e);
        }
    }
}
