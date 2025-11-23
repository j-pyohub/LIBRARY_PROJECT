
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
@ToString
public class StoreStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long storeStockNo;

    @Column(nullable = false)
    private Long storeItemNo;

    @Column(updatable = false, nullable = false)
    @CreationTimestamp
    private Timestamp changeDatetime; // 등록 시각 (입고/폐기 발생 시간)

    @Column(nullable = false)
    private Integer changeQuantity;
    @Column(nullable = false)
    private String changeReason;
    @Column(nullable = false)
    private Integer currentQuantity;
    private String disposalReason;
}
