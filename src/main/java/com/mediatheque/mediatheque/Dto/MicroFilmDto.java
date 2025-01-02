package com.mediatheque.mediatheque.Dto;

import com.mediatheque.mediatheque.Entity.Document;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MicroFilmDto {
    private Long micro_id;
    private Document document;
}
