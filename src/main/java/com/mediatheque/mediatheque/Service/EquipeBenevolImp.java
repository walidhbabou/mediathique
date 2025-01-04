package com.mediatheque.mediatheque.Service;

import com.mediatheque.mediatheque.Dto.EquipeBenevolDto;
import com.mediatheque.mediatheque.Entity.EquipeBenevol;
import com.mediatheque.mediatheque.Repository.EquipeBenevolRepository;
import com.mediatheque.mediatheque.Service.EquipeBenevolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EquipeBenevolImp implements EquipeBenevolService {

    @Autowired
    private EquipeBenevolRepository equipeBenevolRepository;

    // Méthode pour convertir un DTO en entité
    public EquipeBenevol convertToEntity(EquipeBenevolDto equipeBenevolDto) {
        EquipeBenevol equipeBenevol = new EquipeBenevol();
        equipeBenevol.setBenevol_id(equipeBenevolDto.getBenevol_id());
        equipeBenevol.setEquipe_id(equipeBenevolDto.getEquipe_id());
        equipeBenevol.setLecteur(equipeBenevolDto.getLecteur());
        return equipeBenevol;
    }

    // Méthode pour convertir une entité en DTO
    public EquipeBenevolDto convertToDto(EquipeBenevol equipeBenevol) {
        EquipeBenevolDto equipeBenevolDto = new EquipeBenevolDto();
        equipeBenevolDto.setBenevol_id(equipeBenevol.getBenevol_id());
        equipeBenevolDto.setEquipe_id(equipeBenevol.getEquipe_id());
        equipeBenevolDto.setLecteur(equipeBenevol.getLecteur());
        return equipeBenevolDto;
    }

    @Override
    public EquipeBenevolDto addBenevol(EquipeBenevolDto equipeBenevolDto) {
        // Convertir le DTO en entité
        EquipeBenevol equipeBenevol = convertToEntity(equipeBenevolDto);

        // Sauvegarder l'entité dans la base de données
        equipeBenevol = equipeBenevolRepository.save(equipeBenevol);

        // Convertir l'entité en DTO et retourner
        return convertToDto(equipeBenevol);
    }

    @Override
    public Optional<EquipeBenevolDto> updateBenevol(Long benevolId, EquipeBenevolDto equipeBenevolDto) {
        Optional<EquipeBenevol> equipeBenevolOpt = equipeBenevolRepository.findById(benevolId);
        if (equipeBenevolOpt.isPresent()) {
            EquipeBenevol equipeBenevol = equipeBenevolOpt.get();

            // Mettre à jour les valeurs de l'entité
            equipeBenevol.setEquipe_id(equipeBenevolDto.getEquipe_id());
            equipeBenevol.setLecteur(equipeBenevolDto.getLecteur());

            // Sauvegarder les modifications
            equipeBenevol = equipeBenevolRepository.save(equipeBenevol);

            // Retourner le DTO mis à jour
            return Optional.of(convertToDto(equipeBenevol));
        }
        return Optional.empty();
    }

    @Override
    public boolean deleteBenevol(Long benevolId) {
        Optional<EquipeBenevol> equipeBenevolOpt = equipeBenevolRepository.findById(benevolId);
        if (equipeBenevolOpt.isPresent()) {
            equipeBenevolRepository.delete(equipeBenevolOpt.get());
            return true;
        }
        return false;
    }

    @Override
    public List<EquipeBenevolDto> getAllBenevols() {
        List<EquipeBenevol> equipeBenevolList = equipeBenevolRepository.findAll();
        return equipeBenevolList.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<EquipeBenevolDto> getBenevolById(Long benevolId) {
        Optional<EquipeBenevol> equipeBenevolOpt = equipeBenevolRepository.findById(benevolId);
        return equipeBenevolOpt.map(this::convertToDto);
    }
}
