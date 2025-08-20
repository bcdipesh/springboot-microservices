package com.dipeshbc.cards.service.impl;

import com.dipeshbc.cards.constants.CardsConstants;
import com.dipeshbc.cards.entity.Cards;
import com.dipeshbc.cards.exception.ResourceNotFoundException;
import com.dipeshbc.cards.exception.CardAlreadyExistsException;
import com.dipeshbc.cards.dto.CardsDto;
import com.dipeshbc.cards.mapper.CardsMapper;
import com.dipeshbc.cards.repository.CardsRepository;
import com.dipeshbc.cards.service.ICardsService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class CardsServiceImpl implements ICardsService {

    private final CardsRepository cardsRepository;

    public CardsServiceImpl(CardsRepository cardsRepository) {
        this.cardsRepository = cardsRepository;
    }

    /**
     * Creates a new card for a customer.
     *
     * @param mobileNumber The mobile number of the customer associated with this card.
     * @throws CardAlreadyExistsException If a card already exists for the given mobile number.
     */
    @Override
    public void createCard(String mobileNumber) {
        Optional<Cards> card = cardsRepository.findByMobileNumber(mobileNumber);
        if (card.isPresent()) {
            throw new CardAlreadyExistsException("A card is already present for this mobile number");
        }

        Cards newCard = createNewCard(mobileNumber);
        cardsRepository.save(newCard);
    }

    private Cards createNewCard(String mobileNumber) {
        Cards newCard = new Cards();
        long randomCardNumber = 100_000_000_000L + new Random().nextInt(900_000_000);
        newCard.setCardNumber(String.valueOf(randomCardNumber));
        newCard.setMobileNumber(mobileNumber);
        newCard.setCardType(CardsConstants.CREDIT_CARD);
        newCard.setTotalLimit(CardsConstants.NEW_CARD_LIMIT);
        newCard.setAmountUsed(0);
        newCard.setAvailableAmount(CardsConstants.NEW_CARD_LIMIT);

        return newCard;
    }

    /**
     * Fetches the card details of a customer associated with the given mobile number.
     *
     * @param mobileNumber The mobile number of the customer associated with this card.
     * @return A DTO containing the card details.
     * @throws ResourceNotFoundException If no card is found for the given mobile number.
     */
    @Override
    public CardsDto fetchCardDetails(String mobileNumber) {
        Cards card = cardsRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Card", "mobileNumber", mobileNumber)
        );

        return CardsMapper.mapToCardsDto(card, new CardsDto());
    }

    /**
     * Updates the details of a customer card.
     *
     * @param cardsDto The DTO containing the updated card details.
     * @throws ResourceNotFoundException if no card is found for the card number present in the DTO.
     */
    @Override
    public void updateCardDetails(CardsDto cardsDto) {
        Cards currCard = cardsRepository.findByCardNumber(cardsDto.getCardNumber()).orElseThrow(
                () -> new ResourceNotFoundException("Card", "cardNumber", cardsDto.getCardNumber())
        );
        Cards newCard = CardsMapper.mapToCards(cardsDto, currCard);
        cardsRepository.save(newCard);
    }

    /**
     * Deletes the customer card associated with the given mobile number.
     *
     * @param mobileNumber The mobile number of the customer associated with this card.
     * @throws ResourceNotFoundException If no card is found for the given mobile number.
     */
    @Override
    public void deleteCard(String mobileNumber) {
        Cards card = cardsRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Card", "mobileNumber", mobileNumber)
        );

        cardsRepository.delete(card);
    }
}
