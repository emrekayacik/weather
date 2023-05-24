package com.emrekayacik.weather.repository;

import com.emrekayacik.weather.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findFirstByUsername(String username);


    @Modifying
    @Transactional
    @Query("update User user set user.email = ?2 where user.username = ?1")
    void updateEmailByUsername(String username, String email);
}