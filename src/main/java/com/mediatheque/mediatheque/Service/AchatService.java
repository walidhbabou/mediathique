package com.mediatheque.mediatheque.Service;

import com.mediatheque.mediatheque.Dto.AchatDto;

import java.util.List;

public interface AchatService {
    List<AchatDto> getAllAchats();
    AchatDto getAchatById(Long id);
    AchatDto saveAchat(AchatDto achatDto);
    AchatDto updateAchat(Long id, AchatDto achatDto);
    void deleteAchat(Long id);
}
