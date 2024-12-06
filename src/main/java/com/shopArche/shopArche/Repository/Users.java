package com.shopArche.shopArche.Repository;

import com.shopArche.shopArche.model.SecuretyPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Users extends JpaRepository<SecuretyPost, Integer> {
    public SecuretyPost findByEmail(String email);
}
