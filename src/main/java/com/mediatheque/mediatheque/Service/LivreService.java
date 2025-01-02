package com.mediatheque.mediatheque.Service;

import com.mediatheque.mediatheque.Dto.LivreDto;

import java.util.List;

public interface LivreService {
    public String addLivre(LivreDto livreDto);
    public List<LivreDto> getLivres();
    public String updateLivre(LivreDto livreDto);
    public LivreDto getLivreById(Long id);
}
