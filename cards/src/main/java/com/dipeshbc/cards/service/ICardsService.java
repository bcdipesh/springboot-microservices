package com.dipeshbc.cards.service;

import com.dipeshbc.cards.exception.ResourceNotFoundException;
import com.dipeshbc.cards.exception.CardAlreadyExistsException;
import com.dipeshbc.cards.dto.CardsDto;

public interface ICardsService {

    /**
     * Creates a new card for a customer.
     *
     * @param mobileNumber The mobile number of the customer associated with this card.
     * @throws CardAlreadyExistsException If a card already exists for the given mobile number.
     */
    void createCard(String mobileNumber);

    /**
     * Fetches the card details of a customer associated with the given mobile number.
     *
     * @param mobileNumber The mobile number of the customer associated with this card.
     * @return A DTO containing the card details.
     * @throws ResourceNotFoundException If no card is found for the given mobile number.
     */
    CardsDto fetchCardDetails(String mobileNumber);

    /**
     * Updates the details of a customer card.
     *
     * @param cardsDto The DTO containing the updated card details.
     * @throws ResourceNotFoundException if no card is found for the card number present in the DTO.
     */
    void updateCardDetails(CardsDto cardsDto);

    /**
     * Deletes the customer card associated with the given mobile number.
     *
     * @param mobileNumber The mobile number of the customer associated with this card.
     * @throws ResourceNotFoundException If no card is found for the given mobile number.
     */
    void deleteCard(String mobileNumber);
}
