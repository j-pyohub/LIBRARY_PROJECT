package com.oopsw.erp_project.repository;

import com.oopsw.erp_project.repository.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    List<Menu> findByMenuCategory(String menuCategory);
    List<Menu> findByReleaseStatus(String releaseStatus);
}
