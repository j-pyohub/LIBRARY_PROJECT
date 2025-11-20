package com.oopsw.erp_project.repository;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "manager")
public class Manager {
    @Id
    private String managerId;
    private String pw;
    private String email;
    private String managerName;
    private String phoneNumber;
    @CreationTimestamp
    private Timestamp inDate;
    private Timestamp delDate;
    private String role;

}
