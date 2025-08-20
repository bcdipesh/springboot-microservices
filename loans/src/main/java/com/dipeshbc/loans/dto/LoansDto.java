package com.dipeshbc.loans.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

@Schema(
        name = "Loans",
        description = "Schema to hold Loans information"
)
@Data
public class LoansDto {

    @Schema(
            description = "Mobile number of the customer",
            example = "1234567890"
    )
    @NotEmpty(message = "Mobile number cannot be null or empty")
    @Pattern(regexp = "(^[0-9]{10})", message = "Mobile number must be 10 digits")
    private String mobileNumber;

    @Schema(
            description = "Unique 12-digit loan number of the customer",
            example = "345443323212"
    )
    @NotEmpty(message = "Loan number cannot be null or empty")
    @Pattern(regexp = "(^[0-9]{12})", message = "Loan number must be 12 digits")
    private String loanNumber;

    @Schema(
            description = "Loan type of the customer",
            example = "Home Loan"
    )
    @NotEmpty(message = "Loan type cannot be null or empty")
    private String loanType;

    @Schema(
            description = "Total loan amount of the customer",
            example = "100000"
    )
    @NotNull(message = "Total loan cannot be null")
    @Positive(message = "Total loan must be greater than zero")
    private int totalLoan;

    @Schema(
            description = "Total amount paid for loan",
            example = "50000"
    )
    @NotNull(message = "Amount paid cannot be null")
    @PositiveOrZero(message = "Amount paid must be a non-negative number")
    private int amountPaid;

    @Schema(
            description = "Total outstanding amount of loan",
            example = "50000"
    )
    @NotNull(message = "Outstanding amount cannot be null")
    @PositiveOrZero(message = "Outstanding amount must be a non-negative number")
    private int outstandingAmount;
}
