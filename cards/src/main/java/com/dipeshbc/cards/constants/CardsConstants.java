package com.dipeshbc.cards.constants;

/**
 * A utility class to hold constant values for the Cards microservice.
 * This class is final and has a private constructor to prevent instantiation.
 */
public final class CardsConstants {

    private CardsConstants() {
        // restrict instantiation
    }

    /**
     * Default card type for new cards.
     */
    public static final String CREDIT_CARD = "Credit Card";

    /**
     * Default card limit for new cards.
     */
    public static final int NEW_CARD_LIMIT = 1_00_000;

    /**
     * HTTP status Code 201 (Created) as a string.
     */
    public static final String STATUS_201 = "201";

    /**
     * Success message for a resource creation (HTTP 201).
     */
    public static final String MESSAGE_201 = "Card created successfully";

    /**
     * HTTP status Code 200 (OK) as a string.
     */
    public static final String STATUS_200 = "200";

    /**
     * Generic success message for a processed request (HTTP 200).
     */
    public static final String MESSAGE_200 = "Request processed successfully";

}