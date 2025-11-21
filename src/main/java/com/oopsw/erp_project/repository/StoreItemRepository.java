package com.oopsw.erp_project.repository;

import com.oopsw.erp_project.repository.entity.StoreItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreItemRepository extends JpaRepository<StoreItem, Long> {
}
