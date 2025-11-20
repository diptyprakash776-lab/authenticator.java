package com.Auth.RoleBaseAuthorization;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Auth.RoleBaseAuthorization.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer>{

	

}
