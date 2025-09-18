package com.smartballot.repo;

import com.smartballot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByStudentId(String studentId);
}
