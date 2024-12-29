package com.mediatheque.mediatheque.Dto;

import com.mediatheque.mediatheque.Entity.Abonnement;
import com.mediatheque.mediatheque.Entity.Document;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
@Getter
@Setter

public class ConsultationDto {
    private Long consult_id;
    Date date_consult;
    Date date_expiration;
    private Abonnement abonnement;
    private Document document;
}
