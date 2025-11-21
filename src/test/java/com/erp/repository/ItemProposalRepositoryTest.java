package com.erp.repository;

import com.erp.repository.entity.Item;
import com.erp.repository.entity.ItemProposal;
import com.erp.repository.entity.Manager;
import com.erp.repository.entity.Store;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;

@SpringBootTest
public class ItemProposalRepositoryTest {
    @Autowired
    private ItemProposalRepository repo;

    @Test
    public void findAllProposal() {
        repo.findAll().forEach(System.out::println);
    }
    @Test
    public void findProposalByStoreNo() {
        repo.findByStoreNo(Store.builder().storeNo(1L).build()).forEach(System.out::println);
    }

    @Test
    public void itemPropose() {
        ItemProposal proposal = ItemProposal.builder()
                .managerId(Manager.builder().managerId("abc12").build()) // 관리자id
                .storeNo(Store.builder().storeNo(1L).build()) // 매장id
                .itemNo(Item.builder().itemNo(30L).build()) // 품목id
                .proposalQuantity(4) // 수량
                .proposalReason("test") // 사유
                .proposalDate(new Timestamp(System.currentTimeMillis()))
                .build();
        repo.save(proposal);
    }
}
