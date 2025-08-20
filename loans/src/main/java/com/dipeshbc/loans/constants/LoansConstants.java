package com.dipeshbc.loans.constants;

/**
 * A utility class to hold constant values for the Loans microservice.
 * This class is final and has a private constructor to prevent instantiation.
 */
public final class LoansConstants {

    private LoansConstants() {
        // restrict instantiation
    }

    /**
     * Default loan type for new loans.
     */
    public static final String HOME_LOAN = "Home Loan";

    /**
     * Default loan limit for new loans.
     */
    public static final int NEW_LOAN_LIMIT = 1_00_000;

    /**
     * HTTP status Code 201 (Created) as a string.
     */
    public static final String STATUS_201 = "201";

    /**
     * Success message for a resource creation (HTTP 201).
     */
    public static final String MESSAGE_201 = "Loan created successfully";

    /**
     * HTTP status Code 200 (OK) as a string.
     */
    public static final String STATUS_200 = "200";

    /**
     * Generic success message for a processed request (HTTP 200).
     */
    public static final String MESSAGE_200 = "Request processed successfully";

}