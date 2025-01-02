package com.mediatheque.mediatheque.Dto;

import com.mediatheque.mediatheque.Entity.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
public class MicroFilmDto {
    private Long micro_id;
    private Document document;
}
