package com.erp.dao;

import com.erp.dao.dto.StoreDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StoreDAO {
    List<StoreDTO> getStores();
    List<StoreDTO> getStoresByAddress(String address);
    List<StoreDTO> getStoresByStoreName(String storeName);

}
