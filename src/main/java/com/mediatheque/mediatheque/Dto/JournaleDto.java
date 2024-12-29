package com.mediatheque.mediatheque.Dto;

import com.mediatheque.mediatheque.Entity.Document;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter

public class JournaleDto {
    private Long  journal_id;
    private Document document;
}
