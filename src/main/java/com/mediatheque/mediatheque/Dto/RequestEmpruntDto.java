package com.mediatheque.mediatheque.Dto;

import com.mediatheque.mediatheque.model.Status;
import lombok.*;

import java.util.Date;
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestEmpruntDto {
    private Long requestId;
    private Date dateRequest;
    private Status status;
    private Long lecteurId;
    private Long documentId;
}
