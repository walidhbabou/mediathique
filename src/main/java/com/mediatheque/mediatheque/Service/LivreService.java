package com.mediatheque.mediatheque.Service;

import com.mediatheque.mediatheque.Dto.LivreDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface LivreService {
    public String addLivre(LivreDto livreDto);
    public List<LivreDto> getLivres();
    public String updateLivre(LivreDto livreDto);
    public LivreDto getLivreById(Long id);
}
