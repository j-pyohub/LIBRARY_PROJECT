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
@ToString(exclude = {"managerId", "storeNo", "itemNo"})
public class ItemProposal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemProposalNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id")
    private Manager managerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_no")
    private Store storeNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_no")
    private Item itemNo;

    private Integer proposalQuantity;
    private String proposalReason;

    @Column(updatable = false)
    @CreationTimestamp
    private Timestamp proposalDate;

    private Timestamp responseDate;
}
