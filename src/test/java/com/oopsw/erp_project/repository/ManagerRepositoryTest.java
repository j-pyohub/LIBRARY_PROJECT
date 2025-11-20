package com.oopsw.erp_project.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;

@SpringBootTest
public class ManagerRepositoryTest {
    @Autowired
    private ManagerRepository managerRepository;
    @Test
    public void addManager() {
        if(managerRepository.existsById("hello"))
            throw new RuntimeException("아이디 중복X");
        Manager manager = managerRepository.save(Manager.builder().managerId("hello").pw("123").email("hello@pizza.com").managerName("문석현").phoneNumber("010-1212-2020").role("ROLE_MANAGER").build());
        System.out.println(manager);
    }
}
