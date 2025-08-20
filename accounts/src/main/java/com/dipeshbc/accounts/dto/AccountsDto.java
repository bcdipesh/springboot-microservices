package com.dipeshbc.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Schema(
        name = "Accounts",
        description = "Schema to hold Account information"
)
@Data
public class AccountsDto {

    @Schema(
            description = "Unique 10-digit account number of the EazyBank account",
            example = "3454433232"
    )
    @Digits(integer = 10, fraction = 0, message = "Account number must be 10 digits")
    private Long accountNumber;

    @Schema(
            description = "Account type of the EazyBank account",
            example = "Savings"
    )
    @NotEmpty(message = "Account type cannot be null or empty")
    private String accountType;

    @Schema(
            description = "Branch address of the EazyBank branch",
            example = "123 Main Street, New York"
    )
    @NotEmpty(message = "Branch address cannot be null or empty")
    private String branchAddress;
}
