
package com.erp.repository.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StoreStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long storeStockNo;
    private Long storeItemNo;

    @Column(updatable = false)
    @CreationTimestamp
    private Timestamp changeDatetime; // 등록 시각 (입고/폐기 발생 시간)
    private Integer changeQuantity;
    private String changeReason;
    private Integer currentQuantity;
    private String disposalReason;
}
