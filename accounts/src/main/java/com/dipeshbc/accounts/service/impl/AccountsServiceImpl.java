package com.dipeshbc.accounts.service.impl;

import com.dipeshbc.accounts.constants.AccountsConstants;
import com.dipeshbc.accounts.dto.AccountsDto;
import com.dipeshbc.accounts.dto.CustomerDto;
import com.dipeshbc.accounts.entity.Accounts;
import com.dipeshbc.accounts.entity.Customer;
import com.dipeshbc.accounts.exception.CustomerAlreadyExistsException;
import com.dipeshbc.accounts.exception.ResourceNotFoundException;
import com.dipeshbc.accounts.mapper.AccountsMapper;
import com.dipeshbc.accounts.mapper.CustomerMapper;
import com.dipeshbc.accounts.repository.AccountsRepository;
import com.dipeshbc.accounts.repository.CustomerRepository;
import com.dipeshbc.accounts.service.IAccountsService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class AccountsServiceImpl implements IAccountsService {

    private final AccountsRepository accountsRepository;
    private final CustomerRepository customerRepository;

    public AccountsServiceImpl(AccountsRepository accountsRepository, CustomerRepository customerRepository) {
        this.accountsRepository = accountsRepository;
        this.customerRepository = customerRepository;
    }

    /**
     * Creates a new account for a customer. If the customer already exists, it throws an exception.
     *
     * @param customerDto The DTO containing customer and initial account information.
     */
    @Override
    public void createAccount(CustomerDto customerDto) {
        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customerDto.getMobileNumber());
        if (optionalCustomer.isPresent()) {
            throw new CustomerAlreadyExistsException("Customer already registered with the given mobile number: " + customerDto.getMobileNumber());
        }

        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
        Customer savedCustomer = customerRepository.save(customer);
        accountsRepository.save(createNewAccount(savedCustomer));
    }

    /**
     * Creates a new account entity for a given customer.
     * This method generates a random 10-digit account number.
     *
     * @param customer The Customer entity for whom the account is being created.
     * @return A new Accounts entity with default values.
     */
    private Accounts createNewAccount(Customer customer) {
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1_000_000_000L + new Random().nextInt(900_000_000);

        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);
        return newAccount;
    }

    /**
     * Fetches the account and customer details for a given mobile number.
     *
     * @param mobileNumber The mobile number of the customer.
     * @return A DTO containing the customer and their account details.
     */
    @Override
    public CustomerDto fetchAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Accounts", "customerId", customer.getCustomerId().toString())
        );
        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
        customerDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));

        return customerDto;
    }

    /**
     * Updates the details of an existing customer and their account.
     * The update is performed only if the `accountsDto` inside the `customerDto` is not null.
     *
     * @param customerDto The DTO containing the updated customer and account information.
     */
    @Override
    public void updateAccount(CustomerDto customerDto) {
        AccountsDto accountsDto = customerDto.getAccountsDto();
        if (accountsDto != null) {
            Accounts accounts = accountsRepository.findById(accountsDto.getAccountNumber()).orElseThrow(
                    () -> new ResourceNotFoundException("Account", "AccountNumber", accountsDto.getAccountNumber().toString())
            );

            AccountsMapper.mapToAccounts(accountsDto, accounts);
            accounts = accountsRepository.save(accounts);

            Long customerId = accounts.getCustomerId();
            Customer customer = customerRepository.findById(customerId).orElseThrow(
                    () -> new ResourceNotFoundException("Customer", "CustomerID", customerId.toString())
            );

            CustomerMapper.mapToCustomer(customerDto, customer);
            customerRepository.save(customer);
        }

    }

    /**
     * Deletes the account and customer details associated with a given mobile number.
     *
     * @param mobileNumber The mobile number of the customer to be deleted.
     */
    @Override
    public void deleteAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "MobileNumber", mobileNumber)
        );

        accountsRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());
    }
}
