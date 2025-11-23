package com.erp.repository;

import com.erp.repository.entity.Menu;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    List<Menu> findByMenuCategory(String menuCategory);
    List<Menu> findByReleaseStatus(String releaseStatus);
}
