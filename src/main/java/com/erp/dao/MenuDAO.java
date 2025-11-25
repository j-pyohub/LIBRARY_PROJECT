package com.erp.dao;

import com.erp.dao.dto.MenuDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuDAO {

    void setMenu(MenuDTO menu);

    void addMenu(MenuDTO menu);

    void removeMenu(Long menuNo);

    List<MenuDTO> getAllMenu();

    List<MenuDTO> getMenuByCategory(String menuCategory);

    List<MenuDTO> getMenuByReleaseStatus(String releaseStatus);

    List<MenuDTO> findByMenuCode(String menuCode);





}
