package com.mediatheque.mediatheque.Controller;

import com.mediatheque.mediatheque.Dto.AccountRequestDto;
import com.mediatheque.mediatheque.Entity.AccountRequest;
import com.mediatheque.mediatheque.Service.AccountRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accountrequests")
public class AccountRequestController {

    @Autowired
    private AccountRequestService accountRequestService;

    @PostMapping("/create")
    public ResponseEntity<AccountRequest> createAccountRequest(@RequestBody AccountRequestDto accountRequestDto) {
        AccountRequest newRequest = accountRequestService.createAccountRequest(accountRequestDto);
        return ResponseEntity.ok(newRequest);
    }

    @GetMapping("/all")
    public ResponseEntity<List<AccountRequest>> getAllAccountRequests() {
        List<AccountRequest> requests = accountRequestService.getAllAccountRequests();
        return ResponseEntity.ok(requests);
    }
}