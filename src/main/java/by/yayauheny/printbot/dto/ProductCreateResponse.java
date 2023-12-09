package by.yayauheny.printbot.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.format.annotation.NumberFormat;

public record ProductCreateResponse(
        @NumberFormat
        @NotNull
        Long id,
        @NotBlank
        String name,
        @NotBlank
        String description,
        String discountCondition,
        @Digits(integer = 20, fraction = 2)
        @Positive
        double pricePerUnit,
        boolean hasDiscount) {
}
