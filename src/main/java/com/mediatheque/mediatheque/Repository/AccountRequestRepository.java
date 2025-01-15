package com.mediatheque.mediatheque.Repository;

import com.mediatheque.mediatheque.Entity.AccountRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRequestRepository extends JpaRepository<AccountRequest, Long> {
}
