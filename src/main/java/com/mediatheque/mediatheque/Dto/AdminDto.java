package com.mediatheque.mediatheque.Dto;

import com.mediatheque.mediatheque.Entity.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter

public class AdminDto {
    private Long admin_id;
    private User user;
}
