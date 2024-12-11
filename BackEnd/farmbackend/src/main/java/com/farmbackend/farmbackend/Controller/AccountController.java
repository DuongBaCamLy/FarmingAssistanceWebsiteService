package com.farmbackend.farmbackend.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.farmbackend.farmbackend.DTO.ApiResponse;
import com.farmbackend.farmbackend.DTO.LoginResponse;
import com.farmbackend.farmbackend.Entities.Account;
import com.farmbackend.farmbackend.Service.AccountService;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping
public ResponseEntity<List<Account>> getAllAccounts() {
    List<Account> accounts = accountService.getAllAccounts();
    return ResponseEntity.ok(accounts);
}

    // Create an account
    @PostMapping
    public Account createAccount(@RequestBody Account account) {
        return accountService.createAccount(account);
    }

    
    // Get account by username
    @GetMapping("/username/{username}")
    public Optional<Account> getAccountByUsername(@PathVariable String username) {
        return accountService.getAccountByUsername(username);
    }

    // Get account by email
    @GetMapping("/email/{email}")
    public Optional<Account> getAccountByEmail(@PathVariable String email) {
        return accountService.getAccountByEmail(email);
    }

    // Delete an account
    @DeleteMapping("/{accountId}")
    public void deleteAccount(@PathVariable int accountId) {
        accountService.deleteAccount(accountId);
    }

    // Update an account
    @PutMapping("/{accountId}")
    public Account updateAccount(@PathVariable int accountId, @RequestBody Account updatedAccount) {
        return accountService.updateAccount(accountId, updatedAccount);

    }

    @PutMapping("/username/{username}")
    public Account updateAccount(@PathVariable String username, @RequestBody Account updatedAccount) {
        return accountService.updateAccount(username, updatedAccount);

    }
}