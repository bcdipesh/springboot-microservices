package com.dipeshbc.accounts.service;

import com.dipeshbc.accounts.dto.CustomerDto;

public interface IAccountsService {


    /**
     * Creates a new account for a customer.
     *
     * @param customerDto The DTO containing customer and initial account information.
     * @throws com.dipeshbc.accounts.exception.CustomerAlreadyExistsException if a customer with the given mobile
     *                                                                        number already exists.
     */
    void createAccount(CustomerDto customerDto);

    /**
     * Fetches the account and customer details for a given mobile number.
     *
     * @param mobileNumber The mobile number of the customer.
     * @return A DTO containing the customer and their account details.
     * @throws com.dipeshbc.accounts.exception.ResourceNotFoundException if no customer is found with the given
     *                                                                   mobile number.
     */
    CustomerDto fetchAccount(String mobileNumber);


    /**
     * Updates the details of an existing customer and their account.
     *
     * @param customerDto The DTO containing the updated customer and account information. The account number within
     *                    the DTO is used to identify the account to update.
     * @throws com.dipeshbc.accounts.exception.ResourceNotFoundException if the account or associated customer is not
     *                                                                   found.
     */
    void updateAccount(CustomerDto customerDto);


    /**
     * Deletes the account and customer details associated with a given mobile number.
     *
     * @param mobileNumber The mobile number of the customer to be deleted.
     * @throws com.dipeshbc.accounts.exception.ResourceNotFoundException if no customer is found with the given
     *                                                                   mobile number.
     */
    void deleteAccount(String mobileNumber);
}
