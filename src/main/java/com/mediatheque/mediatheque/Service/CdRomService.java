package com.mediatheque.mediatheque.Service;

import com.mediatheque.mediatheque.Dto.CdRomDto;
import java.util.List;
public interface CdRomService {

        // Ajouter un CD-ROM
        String addCdRom(CdRomDto cdRomDto);

        // Récupérer tous les CD-ROMs
        List<CdRomDto> getAllCdRoms();

        // Mettre à jour un CD-ROM
        String updateCdRom(CdRomDto cdRomDto);

        // Supprimer un CD-ROM
        String deleteCdRom(Long id);


}
