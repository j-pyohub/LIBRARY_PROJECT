package com.oopsw.erp_project.repository;

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
public class ItemProposal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemProposalNo;

    private String managerId;
    private Long storeNo;
    private Long itemNo;
    private Integer proposalQuantity;
    private String proposalReason;

    @Column(updatable = false)
    @CreationTimestamp
    private Timestamp proposalDate;


    @UpdateTimestamp
    private Timestamp responseDate;
}
