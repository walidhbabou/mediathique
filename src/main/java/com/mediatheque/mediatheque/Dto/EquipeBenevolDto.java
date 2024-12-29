package com.mediatheque.mediatheque.Dto;

import com.mediatheque.mediatheque.Entity.Lecteur;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter

public class EquipeBenevolDto {
    private Long benevol_id;
    private Long equipe_id;
    private Lecteur lecteur;
}
