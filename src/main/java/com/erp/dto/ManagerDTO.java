package com.erp.dto;

import lombok.*;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
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