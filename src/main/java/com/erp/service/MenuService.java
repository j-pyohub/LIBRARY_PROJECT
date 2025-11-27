package com.erp.service;

import com.erp.dao.MenuDAO;
import com.erp.repository.MenuRepository;
import com.erp.service.dto.MenuDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuService {
    private final MenuRepository menuRepository;
    private MenuDAO menuDAO;

    //메뉴 조회
    public List<MenuDTO> getAllMenu() {
        return menuDAO.getAllMenu();
    }

}
