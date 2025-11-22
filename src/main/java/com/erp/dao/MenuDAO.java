package com.erp.dao;

import com.erp.dao.dto.MenuDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuDAO {

    void addMenu(MenuDTO menu);

    List<MenuDTO> findByMenuCode(String menuCode);

    void updateMenu(MenuDTO menu);

    void insertMenu(MenuDTO menu);

    void deleteMenu(Long menuNo);

}
