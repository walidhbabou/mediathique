package com.mediatheque.mediatheque.Service;

import com.mediatheque.mediatheque.Dto.EquipeBenevolDto;
import java.util.List;
import java.util.Optional;

public interface EquipeBenevolService {
    EquipeBenevolDto addBenevol(EquipeBenevolDto equipeBenevolDto);
    Optional<EquipeBenevolDto> updateBenevol(Long benevolId, EquipeBenevolDto equipeBenevolDto);
    boolean deleteBenevol(Long benevolId);
    List<EquipeBenevolDto> getAllBenevols();
    Optional<EquipeBenevolDto> getBenevolById(Long benevolId);
}
