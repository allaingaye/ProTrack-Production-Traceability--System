package com.example.ProTrack.Repository;

import com.example.ProTrack.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByRoleIgnoreCase(String role);
    List<User> findByStatusIgnoreCase(String status);
    Optional<User> findByEmail(String email);
}
