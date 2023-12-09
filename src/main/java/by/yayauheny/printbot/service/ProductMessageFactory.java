package by.yayauheny.printbot.service;

import by.yayauheny.printbot.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductMessageFactory extends MessageFactory<ProductDto> {

    @Value("${bot.currency}")
    private String currency;

    @Override
    public String convert(ProductDto product) {
        StringBuilder sb = new StringBuilder();
        sb.append(
                """
                        Услуга: %s
                        Стоимость (шт): %.2f %s
                        """.formatted(product.name(), product.pricePerUnit(), currency));

        return product.hasDiscount()
                ? sb.append("""
                Условие скидки: %s
                """.formatted(product.discountCondition())).toString()
                : sb.toString();
    }
}
