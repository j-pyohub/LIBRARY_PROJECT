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
    private final MenuRepository menuRepository;
    private MenuDAO menuDAO;

    //메뉴 조회
    public List<MenuDTO.MenuDTO> getAllMenu() {
        return menuDAO.getAllMenu();
    }

}
