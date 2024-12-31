package com.mediatheque.mediatheque.Service.User;

import com.mediatheque.mediatheque.Entity.User;
import com.mediatheque.mediatheque.model.Role;

public interface AcountService {
    User addNewUser(String email, String lastename, String password, Role role,  String username);

    User findUserByUsername(String username);
}
