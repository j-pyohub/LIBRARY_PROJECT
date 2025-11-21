package com.erp.repository.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ItemOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemOrderNo;

    private String managerId;
    private Long storeNo;
    private Integer totalItem;
    private Integer totalAmount;
    private String itemOrderStatus;

    @Column(updatable = false)
    @CreationTimestamp
    private Timestamp requestDatetime;

    private Timestamp processDatetime;
}
