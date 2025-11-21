package com.erp.dao;

import com.erp.dao.dto.ManagerDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ManagerDAOTest {
    @Autowired
    ManagerDAO managerDAO;

    @Test
    void addManager() {
        ManagerDTO manager = ManagerDTO.builder()
                .managerId("test123")
                .pw("1234")
                .email("test@test.com")
                .managerName("테스트매니저")
                .phoneNumber("010-1111-2222")
                .role("ROLE_MANAGER")
                .build();

        if ("test123".equals(manager.getManagerId())){
            System.out.println("아이디 중복");
            return;
        }
        managerDAO.addManager(manager);
    }

    @Test
    void setManager() {
        ManagerDTO manager = new ManagerDTO();
        manager.setManagerId("test123");
        manager.setPw("1233");
        manager.setEmail("test12@test.com");
        manager.setManagerName("김삿갓");
        manager.setPhoneNumber("010-1111-2222");

        managerDAO.setManager(manager);

    }

    @Test
    void removeManager() {
        ManagerDTO manager = new ManagerDTO();
        manager.setManagerId("test123");
        managerDAO.removeManager(manager);
    }

    @Test
    void getManagers() {
        System.out.println(managerDAO.getManagers());

    }
}
