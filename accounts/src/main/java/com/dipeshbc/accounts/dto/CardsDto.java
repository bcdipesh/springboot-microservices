package com.dipeshbc.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

@Schema(
        name = "Cards",
        description = "Schema to hold Cards information"
)
@Data
public class CardsDto {

    @Schema(
            description = "Mobile number of the customer",
            example = "1234567890"
    )
    @NotEmpty(message = "Mobile number cannot be null or empty")
    @Pattern(regexp = "(^[0-9]{10})", message = "Mobile number must be 10 digits")
    private String mobileNumber;

    @Schema(
            description = "Unique 12-digit card number of the customer",
            example = "456512345465"
    )
    @NotEmpty(message = "Card number cannot be null or empty")
    @Pattern(regexp = "(^[0-9]{12})", message = "Card number must be 12 digits")
    private String cardNumber;

    @Schema(
            description = "Type of the card",
            example = "Credit Card"
    )
    @NotEmpty(message = "Card type cannot be null or empty")
    private String cardType;

    @Schema(
            description = "Total limit available on the card",
            example = "100000"
    )
    @NotNull(message = "Total limit cannot be null")
    @Positive(message = "Total limit must be a positive number")
    private int totalLimit;

    @Schema(
            description = "Total amount used from the card",
            example = "10000"
    )
    @NotNull(message = "Amount used cannot be null")
    @PositiveOrZero(message = "Amount used must be a non-negative number")
    private int amountUsed;

    @Schema(
            description = "Total available amount on the card",
            example = "90000"
    )
    @NotNull(message = "Available amount cannot be null")
    @PositiveOrZero(message = "Available amount must be a non-negative number")
    private int availableAmount;
}
