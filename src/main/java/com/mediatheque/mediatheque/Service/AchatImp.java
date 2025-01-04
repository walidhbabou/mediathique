package com.mediatheque.mediatheque.Service;

import com.mediatheque.mediatheque.Dto.AchatDto;
import com.mediatheque.mediatheque.Entity.Achat;
import com.mediatheque.mediatheque.Repository.AchatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AchatImp implements AchatService {

    @Autowired
    private AchatRepository achatRepository;

    @Override
    public List<AchatDto> getAllAchats() {
        List<Achat> achats = achatRepository.findAll();
        return achats.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public AchatDto getAchatById(Long id) {
        Achat achat = achatRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Achat not found"));
        return convertToDto(achat);
    }

    @Override
    public AchatDto saveAchat(AchatDto achatDto) {
        Achat achat = convertToEntity(achatDto);
        Achat savedAchat = achatRepository.save(achat);
        return convertToDto(savedAchat);
    }

    @Override
    public AchatDto updateAchat(Long id, AchatDto achatDto) {
        Achat existingAchat = achatRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Achat not found"));

        existingAchat.setDate_achat(achatDto.getDate_achat());
        existingAchat.setAbonnement(achatDto.getAbonnement());
        existingAchat.setDocument(achatDto.getDocument());

        Achat updatedAchat = achatRepository.save(existingAchat);
        return convertToDto(updatedAchat);
    }

    @Override
    public void deleteAchat(Long id) {
        achatRepository.deleteById(id);
    }

    private AchatDto convertToDto(Achat achat) {
        AchatDto dto = new AchatDto();
        dto.setAchat_id(achat.getAchat_id());
        dto.setDate_achat(achat.getDate_achat());
        dto.setAbonnement(achat.getAbonnement());
        dto.setDocument(achat.getDocument());
        return dto;
    }

    private Achat convertToEntity(AchatDto dto) {
        Achat achat = new Achat();
        achat.setAchat_id(dto.getAchat_id());
        achat.setDate_achat(dto.getDate_achat());
        achat.setAbonnement(dto.getAbonnement());
        achat.setDocument(dto.getDocument());
        return achat;
    }
}
