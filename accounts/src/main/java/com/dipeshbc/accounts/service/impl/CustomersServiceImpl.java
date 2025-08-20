package com.dipeshbc.accounts.service.impl;

import com.dipeshbc.accounts.dto.AccountsDto;
import com.dipeshbc.accounts.dto.CardsDto;
import com.dipeshbc.accounts.dto.LoansDto;
import com.dipeshbc.accounts.entity.Accounts;
import com.dipeshbc.accounts.entity.Customer;
import com.dipeshbc.accounts.exception.ResourceNotFoundException;
import com.dipeshbc.accounts.dto.CustomerDetailsDto;
import com.dipeshbc.accounts.mapper.AccountsMapper;
import com.dipeshbc.accounts.mapper.CustomerMapper;
import com.dipeshbc.accounts.repository.AccountsRepository;
import com.dipeshbc.accounts.repository.CustomerRepository;
import com.dipeshbc.accounts.service.ICustomersService;
import com.dipeshbc.accounts.service.clients.CardsFeignClient;
import com.dipeshbc.accounts.service.clients.LoansFeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CustomersServiceImpl implements ICustomersService {

    private final AccountsRepository accountsRepository;
    private final CustomerRepository customerRepository;
    private final CardsFeignClient cardsFeignClient;
    private final LoansFeignClient loansFeignClient;

    public CustomersServiceImpl(AccountsRepository accountsRepository, CustomerRepository customerRepository,
                                CardsFeignClient cardsFeignClient, LoansFeignClient loansFeignClient) {
        this.accountsRepository = accountsRepository;
        this.customerRepository = customerRepository;
        this.cardsFeignClient = cardsFeignClient;
        this.loansFeignClient = loansFeignClient;
    }


    /**
     * Retrieves the customer, account, loans, and cards details for the given mobile number.
     *
     * @param mobileNumber  The mobile number of the customer.
     * @param correlationId The unique identifier used to trace and correlate requests across services.
     * @return CustomerDetailsDto The DTO containing the customer, account, loans, and cards details.
     * @throws ResourceNotFoundException if no customer is found with the given mobile number.
     */
    @Override
    public CustomerDetailsDto fetchCustomerDetails(String mobileNumber, String correlationId) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Accounts", "customerId", customer.getCustomerId().toString())
        );
        CustomerDetailsDto customerDetailsDto = CustomerMapper.mapToCustomerDetails(customer, new CustomerDetailsDto());
        customerDetailsDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));

        ResponseEntity<CardsDto> cardsDtoResponseEntity = cardsFeignClient.fetchCard(correlationId, mobileNumber);
        if (cardsDtoResponseEntity != null) {
            customerDetailsDto.setCardsDto(cardsDtoResponseEntity.getBody());
        }

        ResponseEntity<LoansDto> loansDtoResponseEntity = loansFeignClient.fetchLoan(correlationId, mobileNumber);
        if (loansDtoResponseEntity != null) {
            customerDetailsDto.setLoansDto(loansDtoResponseEntity.getBody());
        }

        return customerDetailsDto;
    }
}
