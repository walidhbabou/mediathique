package com.mediatheque.mediatheque.Service;

import com.mediatheque.mediatheque.Dto.CdRomDto;
import com.mediatheque.mediatheque.Entity.CdRom;
import com.mediatheque.mediatheque.Entity.Document;
import com.mediatheque.mediatheque.Repository.CdRomRepository;
import com.mediatheque.mediatheque.Repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CdRomImp implements CdRomService {
    @Autowired
    private CdRomRepository cdRomRepository;

    @Autowired
    private DocumentRepository documentRepository;

    @Override
    public String addCdRom(CdRomDto cdRomDto) {
        try {
            // Vérifiez que le Document existe
            Document document = fetchOrCreateDocument(cdRomDto.getDocument());

            // Créez l'entité CdRom
            CdRom cdRom = new CdRom();
            cdRom.setDocument(document);

            cdRomRepository.save(cdRom);
            return "CD-ROM added successfully";
        } catch (Exception e) {
            return "Error while adding CD-ROM: " + e.getMessage();
        }
    }

    @Override
    public List<CdRomDto> getAllCdRoms() {
        List<CdRom> cdRoms = cdRomRepository.findAll();
        return cdRoms.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public String updateCdRom(CdRomDto cdRomDto) {
        try {
            // Vérifiez si le CD-ROM existe
            Optional<CdRom> existingCdRom = cdRomRepository.findById(cdRomDto.getCd_id());
            if (existingCdRom.isPresent()) {
                // Vérifiez ou mettez à jour le Document associé
                Document document = fetchOrCreateDocument(cdRomDto.getDocument());

                // Mettez à jour les informations du CD-ROM
                CdRom cdRom = existingCdRom.get();
                cdRom.setDocument(document);

                cdRomRepository.save(cdRom);
                return "CD-ROM updated successfully";
            } else {
                return "CD-ROM not found";
            }
        } catch (Exception e) {
            return "Error while updating CD-ROM: " + e.getMessage();
        }
    }

    @Override
    public String deleteCdRom(Long id) {
        try {
            if (cdRomRepository.existsById(id)) {
                cdRomRepository.deleteById(id);
                return "CD-ROM deleted successfully";
            } else {
                return "CD-ROM not found";
            }
        } catch (Exception e) {
            return "Error while deleting CD-ROM: " + e.getMessage();
        }
    }

    // Méthode pour vérifier ou créer un Document
    private Document fetchOrCreateDocument(Document document) {
        if (document.getDocument_id() != null) {
            return documentRepository.findById(document.getDocument_id())
                    .orElseThrow(() -> new RuntimeException("Document not found"));
        } else {
            return documentRepository.save(document);
        }
    }

    // Convertir CdRomDto en CdRom (Entity)
    private CdRom convertToEntity(CdRomDto cdRomDto) {
        CdRom cdRom = new CdRom();
        cdRom.setCd_id(cdRomDto.getCd_id());
        cdRom.setDocument(cdRomDto.getDocument());
        return cdRom;
    }

    // Convertir CdRom (Entity) en CdRomDto
    private CdRomDto convertToDto(CdRom cdRom) {
        CdRomDto cdRomDto = new CdRomDto();
        cdRomDto.setCd_id(cdRom.getCd_id());
        cdRomDto.setDocument(cdRom.getDocument());
        return cdRomDto;
    }
}
