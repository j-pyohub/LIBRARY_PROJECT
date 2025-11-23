package com.erp.repository.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class StoreItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long storeItemNo;

    @Column(nullable = false)
    private Long itemNo;
    @Column(nullable = false)
    private Long storeNo;
    private Integer managerLimit;
    private Integer storeLimit;
}
