package com.dipeshbc.accounts.service;

import com.dipeshbc.accounts.dto.CustomerDetailsDto;
import com.dipeshbc.accounts.exception.ResourceNotFoundException;

public interface ICustomersService {


    /**
     * Retrieves the customer, account, loans, and cards details for the given mobile number.
     *
     * @param mobileNumber The mobile number of the customer.
     * @param correlationId The unique identifier used to trace and correlate requests across services.
     * @return CustomerDetailsDto The DTO containing the customer, account, loans, and cards details.
     * @throws ResourceNotFoundException if no customer is found with the given mobile number.
     */
    CustomerDetailsDto fetchCustomerDetails(String mobileNumber, String correlationId);
}
