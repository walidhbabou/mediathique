package com.mediatheque.mediatheque.Dto;

import com.mediatheque.mediatheque.Entity.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter

public class LecteurDto {
    private Long lecteurId;
    private User user;
    private List<AbonnementDto> abonnements; // Include the list of abonnements

}
