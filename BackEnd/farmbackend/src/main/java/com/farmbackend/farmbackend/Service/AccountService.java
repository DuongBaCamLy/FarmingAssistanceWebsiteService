package com.farmbackend.farmbackend.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.farmbackend.farmbackend.Entities.Account;
import com.farmbackend.farmbackend.Repository.AccountRepository;

@Service

public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    // Create a new account
    public Account createAccount(Account account) {
        if (accountRepository.existsByUsername(account.getUsername())) {
            throw new IllegalArgumentException("Username already exists!");
        }
        if (accountRepository.existsByEmail(account.getEmail())) {
            throw new IllegalArgumentException("Email already exists!");
        }
        return accountRepository.save(account);
    }

    // Find an account by username
    public Optional<Account> getAccountByUsername(String username) {
        return accountRepository.findByUsername(username);
    }

    // Find an account by email
    public Optional<Account> getAccountByEmail(String email) {
        return accountRepository.findByEmail(email);
    }

    // Delete an account by ID
    public void deleteAccount(int accountId) {
        accountRepository.deleteById(accountId);
    }

    // Update account details
    public Account updateAccount(int accountId, Account updatedAccount) {
        return accountRepository.findById(accountId).map(existingAccount -> {
            existingAccount.setUsername(updatedAccount.getUsername());
            existingAccount.setEmail(updatedAccount.getEmail());
            existingAccount.setPassword(updatedAccount.getPassword());
            // existingAccount.setAccountRole(updatedAccount.getAccountRole());
            return accountRepository.save(existingAccount);
        }).orElseThrow(() -> new IllegalArgumentException("Account not found!"));
    }

    public Account updateAccount(String username, Account updatedAccount) {
        return accountRepository.findByUsername(username).map(existingAccount -> {
            existingAccount.setFirstName(updatedAccount.getFirstName());
            existingAccount.setLastName(updatedAccount.getLastName());
            existingAccount.setEmail(updatedAccount.getEmail());
            existingAccount.setPhone(updatedAccount.getPhone());
            if (updatedAccount.getPassword() != null) {
                existingAccount.setPassword(updatedAccount.getPassword()); // Make sure the password is hashed before saving.
            }
            if (updatedAccount.getStatus() != null) {
                existingAccount.setStatus(updatedAccount.getStatus());
            }
            if (updatedAccount.getBirthday() != null) {
                existingAccount.setBirthday(updatedAccount.getBirthday());
            }
            return accountRepository.save(existingAccount);
        }).orElseThrow(() -> new IllegalArgumentException("Account not found!"));
    }

    public Account login(String username, String password) {
        Optional<Account> result = accountRepository.findByUsername(username);

        if (result.isPresent() && result.get().getPassword().equals(password)) {

            return result.get();
        }

        return null;
    }

}
