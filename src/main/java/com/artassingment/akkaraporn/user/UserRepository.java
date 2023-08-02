package com.artassingment.akkaraporn.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Query(
            value = "SELECT id as userId, firstname as firstName, lastname as lastName FROM user WHERE id = ?1 AND role = ?2",
            nativeQuery = true
    )
    UserQueryRepo findUserByFilter(Long id, String role);

}