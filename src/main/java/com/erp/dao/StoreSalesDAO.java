package com.erp.dao;

import com.erp.dao.dto.StoreSalesDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StoreSalesDAO {
    List<StoreSalesDTO> getStoreSales();
}
