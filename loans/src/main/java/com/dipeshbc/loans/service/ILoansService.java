package com.dipeshbc.loans.service;

import com.dipeshbc.loans.dto.LoansDto;
import com.dipeshbc.loans.exception.LoanAlreadyExistsException;
import com.dipeshbc.loans.exception.ResourceNotFoundException;

public interface ILoansService {

    /**
     * Creates a new loan for a customer.
     *
     * @param mobileNumber The mobile number of the customer associated with this loan.
     * @throws LoanAlreadyExistsException If a loan is already present for the provided mobile number.
     */
    void createLoan(String mobileNumber);


    /**
     * Fetches the loan details of a customer for the given mobile number.
     *
     * @param mobileNumber The mobile number of the customer associated with this loan.
     * @return A DTO containing the loan details.
     * @throws ResourceNotFoundException If the loan details are not found for the given mobile number.
     */
    LoansDto fetchLoanDetails(String mobileNumber);

    /**
     * Updates the details of a customer loan.
     *
     * @param loansDto The DTO containing the updated loan details.
     * @throws ResourceNotFoundException If no loan is found for the loan number present in the DTO.
     */
    void updateLoanDetails(LoansDto loansDto);

    /**
     * Deletes the customer loan associated with the given mobile number.
     *
     * @param mobileNumber The mobile number of the customer associated with this loan.
     * @throws ResourceNotFoundException If the loan details are not found for the given mobile number.
     */
    void deleteLoan(String mobileNumber);
}
