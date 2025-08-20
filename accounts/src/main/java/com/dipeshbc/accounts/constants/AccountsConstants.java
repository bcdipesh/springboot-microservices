package com.dipeshbc.accounts.constants;

/**
 * A utility class to hold constant values for the Accounts microservice.
 * This class is final and has a private constructor to prevent instantiation.
 */
public final class AccountsConstants {

    private AccountsConstants() {
        // restrict instantiation
    }

    /**
     * Default account type for new accounts.
     */
    public static final String SAVINGS = "Savings";

    /**
     * Default branch address for new accounts.
     */
    public static final String ADDRESS = "123 Main Street, New York";

    /**
     * HTTP Status Code 201 (Created) as a string.
     */
    public static final String STATUS_201 = "201";

    /**
     * Success message for a resource creation (HTTP 201).
     */
    public static final String MESSAGE_201 = "Account created successfully";

    /**
     * HTTP Status Code 200 (OK) as a string.
     */
    public static final String STATUS_200 = "200";

    /**
     * Generic success message for a processed request (HTTP 200).
     */
    public static final String MESSAGE_200 = "Request processed successfully";

}