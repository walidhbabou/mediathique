package com.mediatheque.mediatheque.Service;

import com.mediatheque.mediatheque.Dto.RequestEmpruntDto;
import com.mediatheque.mediatheque.Entity.RequestEmprunt;

import java.util.List;

public interface RequestEmpruntService {
    RequestEmpruntDto createRequest(RequestEmpruntDto requestEmpruntDto);
    List<RequestEmpruntDto> getAllRequests();
    RequestEmpruntDto getRequestById(Long id);
    RequestEmpruntDto updateRequestStatus(Long id, String status);
    void deleteRequest(Long id);
}