package com.mediatheque.mediatheque.Service;

import com.mediatheque.mediatheque.Dto.AccountRequestDto;
import com.mediatheque.mediatheque.Entity.AccountRequest;
import com.mediatheque.mediatheque.Repository.AccountRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountRequestService {

    @Autowired
    private AccountRequestRepository accountRequestRepository;

    public AccountRequest createAccountRequest(AccountRequestDto accountRequestDto) {
        AccountRequest accountRequest = new AccountRequest();
        accountRequest.setFirstName(accountRequestDto.getFirstName());
        accountRequest.setLastName(accountRequestDto.getLastName());
        accountRequest.setEmail(accountRequestDto.getEmail());
        accountRequest.setPhone(accountRequestDto.getPhone());
        accountRequest.setMessage(accountRequestDto.getMessage());
        return accountRequestRepository.save(accountRequest);
    }

    public List<AccountRequest> getAllAccountRequests() {
        return accountRequestRepository.findAll();
    }
}