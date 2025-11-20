package com.oopsw.erp_project.repository;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Setter
@Getter
@Table(name = "manager")
public class Manager {
    @Id
    private String managerId;
    @Column(nullable = false)
    private String pw;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String managerName;
    @Column(nullable = false)
    private String phoneNumber;
    @CreationTimestamp
    private Timestamp inDate;
    private Timestamp delDate;
    @Column(nullable = false)
    private String role;
}
