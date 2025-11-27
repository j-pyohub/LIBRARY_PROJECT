package com.erp.service;

import com.erp.dao.MenuDAO;
import com.erp.dto.MenuDTO;
import com.erp.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuService {
    private final MenuDAO menuDAO;

    //메뉴 조회
    public List<MenuDTO> getAllMenu() {
        return menuDAO.getAllMenu();
    }

    //조건 검색
    //카테고리만
    public List<MenuDTO> getMenuByCategory(String menuCategory) {
        return menuDAO.getMenuByCategory(menuCategory);
    }
    //출시상태만
    public List<MenuDTO> getMenuByReleaseStatus(String releaseStatus) {
        return menuDAO.getMenuByReleaseStatus(releaseStatus);
    }
    //카테고리&출시상태
    public List<MenuDTO> getMenuByCategoryAndReleaseStatus(String menuCategory, String releaseStatus) {
        return menuDAO.getMenuByCategoryAndReleaseStatus(menuCategory, releaseStatus);
    }

}
