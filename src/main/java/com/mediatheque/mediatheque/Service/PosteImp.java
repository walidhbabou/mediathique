package com.mediatheque.mediatheque.Service;

import com.mediatheque.mediatheque.Dto.PosteDto;
import com.mediatheque.mediatheque.Entity.Poste;
import com.mediatheque.mediatheque.Repository.PosteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PosteImp implements PosteService {
    @Autowired
    private PosteRepository posteRepository;

    @Override
    public List<PosteDto> getPostesLibres() {
        List<Poste> postes = posteRepository.findByEtatTrue();
        return postes.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public PosteDto getPosteById(Integer id) {
        Poste poste = posteRepository.findById(id).orElseThrow(() -> new RuntimeException("Poste not found"));
        return convertToDto(poste);
    }

    @Override
    public PosteDto savePoste(PosteDto posteDto) {
        Poste poste = convertToEntity(posteDto);
        Poste savedPoste = posteRepository.save(poste);
        return convertToDto(savedPoste);
    }

    @Override
    public PosteDto updatePoste(Integer id, PosteDto posteDto) {
        Poste existingPoste = posteRepository.findById(id).orElseThrow(() -> new RuntimeException("Poste not found"));
        existingPoste.setNumero(posteDto.getNumero());
        existingPoste.setEtat(posteDto.isEtat());
        existingPoste.setDateConsult(posteDto.getDateConsult());
        existingPoste.setDateExpiration(posteDto.getDateExpiration());
        existingPoste.setAbonnement(posteDto.getAbonnement());
        existingPoste.setDocument(posteDto.getDocument());
        Poste updatedPoste = posteRepository.save(existingPoste);
        return convertToDto(updatedPoste);
    }

    @Override
    public void deletePoste(Integer id) {
        posteRepository.deleteById(id);
    }

    private PosteDto convertToDto(Poste poste) {
        PosteDto dto = new PosteDto();
        dto.setPosteId(poste.getPosteId());
        dto.setNumero(poste.getNumero());
        dto.setEtat(poste.isEtat());
        dto.setDateConsult(poste.getDateConsult());
        dto.setDateExpiration(poste.getDateExpiration());
        dto.setAbonnement(poste.getAbonnement());
        dto.setDocument(poste.getDocument());
        return dto;
    }

    private Poste convertToEntity(PosteDto dto) {
        Poste poste = new Poste();
        poste.setPosteId(dto.getPosteId());
        poste.setNumero(dto.getNumero());
        poste.setEtat(dto.isEtat());
        poste.setDateConsult(dto.getDateConsult());
        poste.setDateExpiration(dto.getDateExpiration());
        poste.setAbonnement(dto.getAbonnement());
        poste.setDocument(dto.getDocument());
        return poste;
    }
}
