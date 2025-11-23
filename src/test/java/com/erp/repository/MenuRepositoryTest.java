package com.erp.repository;

import com.erp.repository.dto.MenuDTO;
import com.erp.repository.dto.MenuIngredientDTO;
import com.erp.repository.entity.Menu;
import com.erp.repository.entity.MenuIngredient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
public class MenuRepositoryTest {
    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private MenuIngredientRepository menuIngredientRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void getAllMenu() {
        System.out.println(menuRepository.findAll());
    }

    @Test
    public void getMenuByCategory() {
        System.out.println(menuRepository.findByMenuCategory("음료"));
    }

    @Test
    public void getMenuByReleaseStatus() {
        System.out.println(menuRepository.findByReleaseStatus("출시 예정"));
    }

    @Test
    @Transactional
    public void getMenuDetailTest(){
        Long menuNo = 1L;

        Menu menu = menuRepository.findById(menuNo)
                .orElseThrow(() -> new RuntimeException("메뉴 없음 (PK=1L 확인 필요)"));

        List<MenuIngredient> ingredientList =
                menuIngredientRepository.findByMenu_MenuNo(menuNo);

        List<MenuIngredientDTO> ingredientDTOList =
                ingredientList.stream()
                        .map(i -> {
                            MenuIngredientDTO dto = new MenuIngredientDTO();
                            dto.setItemNo(i.getItem().getItemNo());
                            dto.setMenuNo(menuNo);
                            dto.setIngredientName(i.getItem().getIngredientName());
                            dto.setStockUnit(i.getItem().getStockUnit());
                            dto.setQuantity(i.getIngredientQuantity());
                            return dto;
                        })
                        .toList();

        MenuDTO dto = MenuDTO.builder()
                .menuNo(menu.getMenuNo())
                .menuName(menu.getMenuName())
                .menuCode(menu.getMenuCode())
                .menuCategory(menu.getMenuCategory())
                .size(menu.getSize())
                .releaseStatus(menu.getReleaseStatus())
                .menuExplain(menu.getMenuExplain())
                .menuImage(menu.getMenuImage())
                .menuPrice(Integer.valueOf(menu.getMenuPrice()))
                .ingredients(ingredientDTOList)
                .build();

        System.out.println(dto.toString());
    }
}
