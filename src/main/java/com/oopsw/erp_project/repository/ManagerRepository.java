package com.oopsw.erp_project.repository;

import com.oopsw.erp_project.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, String> {
}
