package com.dipeshbc.loans.service.impl;

import com.dipeshbc.loans.constants.LoansConstants;
import com.dipeshbc.loans.dto.LoansDto;
import com.dipeshbc.loans.entity.Loans;
import com.dipeshbc.loans.exception.LoanAlreadyExistsException;
import com.dipeshbc.loans.exception.ResourceNotFoundException;
import com.dipeshbc.loans.mapper.LoansMapper;
import com.dipeshbc.loans.repository.LoansRepository;
import com.dipeshbc.loans.service.ILoansService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class LoansServiceImpl implements ILoansService {

    private final LoansRepository loansRepository;

    public LoansServiceImpl(LoansRepository loansRepository) {
        this.loansRepository = loansRepository;
    }

    /**
     * Creates a new loan for a customer.
     *
     * @param mobileNumber The mobile number of the customer associated with the loan.
     * @throws LoanAlreadyExistsException If a loan is already present for the provided mobile number.
     */
    @Override
    public void createLoan(String mobileNumber) {
        Optional<Loans> loan = loansRepository.findByMobileNumber(mobileNumber);
        if (loan.isPresent()) {
            throw new LoanAlreadyExistsException("Loan already exists for this mobile number");
        }

        Loans newLoan = createNewLoan(mobileNumber);
        loansRepository.save(newLoan);
    }

    private Loans createNewLoan(String mobileNumber) {
        Loans newLoan = new Loans();
        newLoan.setMobileNumber(mobileNumber);
        long randomLoanNumber = 100_000_000_000L + new Random().nextInt(900_000_000);
        newLoan.setLoanNumber(String.valueOf(randomLoanNumber));
        newLoan.setLoanType(LoansConstants.HOME_LOAN);
        newLoan.setTotalLoan(LoansConstants.NEW_LOAN_LIMIT);
        newLoan.setAmountPaid(0);
        newLoan.setOutstandingAmount(LoansConstants.NEW_LOAN_LIMIT);

        return newLoan;
    }

    /**
     * Fetches the loan details of a customer for a given mobile number.
     *
     * @param mobileNumber The mobile number of the customer associated with the loan.
     * @return A DTO containing the loan details.
     * @throws ResourceNotFoundException If the loan details are not found for the given mobile number.
     */
    @Override
    public LoansDto fetchLoanDetails(String mobileNumber) {
        Loans loan = loansRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "mobileNumber", mobileNumber)
        );

        return LoansMapper.mapToLoansDto(loan, new LoansDto());
    }

    /**
     * Updates the details of a loan for a customer.
     *
     * @param loansDto The DTO containing the updated loan details.
     * @throws ResourceNotFoundException If no loan is found for the loan number present in the DTO.
     */
    @Override
    public void updateLoanDetails(LoansDto loansDto) {
        Loans currLoan = loansRepository.findByLoanNumber(loansDto.getLoanNumber()).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "loanNumber", loansDto.getLoanNumber())
        );
        Loans updatedLoan = LoansMapper.mapToLoans(loansDto, currLoan);
        loansRepository.save(updatedLoan);
    }

    /**
     * Deletes the loan details of a customer associated with a given mobile number.
     *
     * @param mobileNumber The mobile number of the customer associated with the loan.
     * @throws ResourceNotFoundException If the loan details are not found for the given mobile number.
     */
    @Override
    public void deleteLoan(String mobileNumber) {
        Loans loan = loansRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Loan", "mobileNumber", mobileNumber)
        );
        loansRepository.delete(loan);
    }
}
