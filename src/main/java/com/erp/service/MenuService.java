package com.erp.service;

import com.erp.controller.exception.NoMenuException;
import com.erp.dao.MenuDAO;
import com.erp.dto.MenuDTO;
import com.erp.dto.MenuIngredientDTO;
import com.erp.repository.MenuIngredientRepository;
import com.erp.repository.MenuRepository;
import com.erp.repository.entity.Menu;
import com.erp.repository.entity.MenuIngredient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuService {
    private final MenuDAO menuDAO;
    private final MenuRepository menuRepository;
    private final MenuIngredientRepository menuIngredientRepository;

    //전체 메뉴 조회
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

    //상세조회
    @Transactional
    public MenuDTO getMenuDetail(Long menuNo) {
        Menu menu = menuRepository.findById(menuNo)
                .orElseThrow(() -> new NoMenuException("없는 메뉴입니다."));
        List<MenuIngredient> menuIngredient = menuIngredientRepository.findByMenu_MenuNo(menuNo);

        List<MenuIngredientDTO> menuIngredientDTOList = menuIngredient.stream()
                .map(i -> {
                    MenuIngredientDTO dto = new MenuIngredientDTO();
                    dto.setItemNo(i.getItem().getItemNo());
                    dto.setMenuNo(menuNo);
                    dto.setIngredientName(i.getItem().getIngredientName());
                    dto.setStockUnit(i.getItem().getStockUnit());
                    dto.setQuantity(i.getIngredientQuantity());
                    return dto;
                }).toList();

        MenuDTO menuDTO = MenuDTO.builder()
                .menuNo(menu.getMenuNo())
                .menuName(menu.getMenuName())
                .menuCode(menu.getMenuCode())
                .menuCategory(menu.getMenuCategory())
                .size(menu.getSize())
                .releaseStatus(menu.getReleaseStatus())
                .menuExplain(menu.getMenuExplain())
                .menuImage(menu.getMenuImage())
                .menuPrice(menu.getMenuPrice())
                .ingredients(menuIngredientDTOList)
                .build();

        return menuDTO;
    }
}
