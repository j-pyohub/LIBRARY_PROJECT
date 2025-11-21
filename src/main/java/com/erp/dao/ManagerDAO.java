package com.erp.dao;

import com.erp.dao.dto.ManagerDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ManagerDAO {
    void addManager(ManagerDTO manager);
    void setManager(ManagerDTO manager);
    void removeManager(ManagerDTO manager);
}
