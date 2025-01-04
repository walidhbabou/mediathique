package com.mediatheque.mediatheque.Service;


import com.mediatheque.mediatheque.Dto.DocumentDto;
import com.mediatheque.mediatheque.Dto.LivreDto;
import com.mediatheque.mediatheque.Entity.Document;
import com.mediatheque.mediatheque.Entity.Livre;

import com.mediatheque.mediatheque.Repository.DocumentRepository;
import com.mediatheque.mediatheque.Repository.LivreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LivreImp implements LivreService {
    @Autowired
    private LivreRepository livreRepository;
    @Autowired
    private DocumentRepository documentRepository;

    @Override
    public String addLivre(LivreDto livreDto) {
        Document document = livreDto.getDocument();

        if (document == null) {
            return "Document cannot be null";
        }

        Livre livre = new Livre();
        livre.setAuteur(livreDto.getAuteur());
        livre.setDocument(document);

        livreRepository.save(livre);
        return "Livre ajouté avec succès";
    }

    @Override
    public List<LivreDto> getLivres() {
        List<Livre> livres = livreRepository.findAll();
        return livres.stream()
                .map(livre -> convertToLivreDto(livre))
                .collect(Collectors.toList());
    }
    private LivreDto convertToLivreDto(Livre livre) {
        LivreDto livreDto = new LivreDto();
        livreDto.setLivre_id(livre.getLivre_id());
        livreDto.setAuteur(livre.getAuteur());
        livreDto.setDocument(livre.getDocument());

        return livreDto;
    }

    @Override
    public String updateLivre(LivreDto livreDto) {
        Optional<Livre> livreOptional = livreRepository.findById(livreDto.getLivre_id());

        if (!livreOptional.isPresent()) {
            return "Livre non trouvé";
        }

        Livre livre = livreOptional.get();

        livre.setAuteur(livreDto.getAuteur());
        livre.setDocument(livreDto.getDocument());
        livreRepository.save(livre);

        return "Livre mis à jour avec succès";
    }
    @Override
    public LivreDto getLivreById(Long id) {
        Optional<Livre> livreOptional = livreRepository.findById(id);

        if (!livreOptional.isPresent()) {
            throw new RuntimeException("Livre non trouvé avec l'ID: " + id);
        }
        Livre livre = livreOptional.get();
        return convertToLivreDto(livre);
    }
    @Override
    public String deleteLivre(Long id) {
        Optional<Livre> livreOptional = livreRepository.findById(id);

        if (!livreOptional.isPresent()) {
            return "Livre non trouvé avec l'ID: " + id;
        }

        livreRepository.deleteById(id);
        return "Livre supprimé avec succès";
    }

}