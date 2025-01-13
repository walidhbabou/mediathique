package com.mediatheque.mediatheque.Dto;

import com.mediatheque.mediatheque.Entity.User;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LecteurDto {
    private Long lecteurId;
    private User user;
    private List<AbonnementDto> abonnements = new ArrayList<>(); // Include the list of abonnements

    public LecteurDto(Long lecteurId, User user) {
        this.lecteurId = lecteurId;
        this.user = user;
    }
}
