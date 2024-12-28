package com.mediatheque.mediatheque.Service.User;

import com.mediatheque.mediatheque.Entity.User;

public interface AcountService {
    User addNewUser(String email, String lastename, String password, String status, String username);

    User findUserByUsername(String username);
}
