package com.mediatheque.mediatheque.Service;

import com.mediatheque.mediatheque.Dto.PosteDto;
import com.mediatheque.mediatheque.Entity.Poste;

import java.util.List;

public interface PosteService {
    List<PosteDto> getPostesLibres();
    PosteDto getPosteById(Integer id);
    PosteDto savePoste(PosteDto posteDto);
    PosteDto updatePoste(Integer id, PosteDto posteDto);
    void deletePoste(Integer id);
}