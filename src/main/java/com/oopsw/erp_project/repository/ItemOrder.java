package com.oopsw.erp_project.repository;

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
public class ItemOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemOrderNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id")
    private Manager managerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_no")
    private Store storeNo;

    private Integer totalItem;
    private Integer totalAmount;
    private String itemOrderStatus;

    @Column(updatable = false)
    @CreationTimestamp
    private Timestamp requestDatetime;

    private Timestamp processDatetime;
}
