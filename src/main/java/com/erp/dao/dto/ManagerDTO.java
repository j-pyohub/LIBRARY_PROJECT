package com.erp.dao.dto;

import jakarta.persistence.Table;
import lombok.*;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@Table(name = "manager")
public class ManagerDTO {
    private String managerId;
    private String pw;
    private String email;
    private String managerName;
    private String phoneNumber;
    private Timestamp inDate;
    private Timestamp delDate;
    private String role;
}