package io.github.amine2k0.portfolio.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

/**
 * Client input for creating or updating a holding.
 * Digit limits mirror the column precision/scale on {@code Holding}, so oversized
 * values are rejected as 400 here instead of failing as 500 in the database.
 */
public record HoldingRequest(

        @NotBlank
        @Size(max = 10)
        @Pattern(regexp = "[A-Za-z0-9.]+", message = "must contain only letters, digits or dots")
        String symbol,

        @NotNull
        @Positive
        @Digits(integer = 13, fraction = 6)
        BigDecimal quantity,

        @NotNull
        @Positive
        @Digits(integer = 15, fraction = 4)
        BigDecimal averageBuyPrice
) {
}
