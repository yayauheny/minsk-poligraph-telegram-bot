package by.yayauheny.printbot.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.springframework.format.annotation.NumberFormat;

public record ProductDto(
        @NotBlank
        String name,
        @NotBlank
        String description,
        String discountCondition,
        double pricePerUnit,
        boolean hasDiscount) {
}
