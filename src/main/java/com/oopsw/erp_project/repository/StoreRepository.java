package com.oopsw.erp_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreRepository extends JpaRepository<Store, String> {
    List<Store> findByAddressContaining(String address);
    List<Store> findByStoreNameContaining(String storeName);
    List<Store> findByManager_ManagerNameContaining(String managerName);
    List<Store> findByStoreStatusContaining(String storeStatus);
}
