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

public class AchatDto {
    private Long achat_id;
    Date date_achat;
    private Abonnement abonnement;
    private Document document;
}
