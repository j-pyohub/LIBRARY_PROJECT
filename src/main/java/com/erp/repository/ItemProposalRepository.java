package com.erp.repository;

import com.erp.repository.entity.ItemProposal;
import com.erp.repository.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemProposalRepository extends JpaRepository<ItemProposal, Long> {
    Iterable<Object> findByStoreNo(Store storeNo);
}
