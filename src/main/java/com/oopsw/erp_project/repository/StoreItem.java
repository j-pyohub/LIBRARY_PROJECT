package com.oopsw.erp_project.repository;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StoreItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long storeItemNo;

    private Long itemNo;
    private Long storeNo;
    private Integer managerLimit;
    private Integer storeLimit;
}
