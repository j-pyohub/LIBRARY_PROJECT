package com.oopsw.erp_project.repository;

import com.oopsw.erp_project.repository.entity.Manager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@SpringBootTest
public class ManagerRepositoryTest {
    @Autowired
    private ManagerRepository managerRepository;
    @Test
    public void addManager() {
        if(managerRepository.existsById("storeManager1"))
            throw new RuntimeException("아이디 중복X");
        Manager manager = managerRepository.save(Manager.builder().managerId("storeManager1").pw("123").email("storeManager1@pizza.com").managerName("문석현").phoneNumber("010-1212-1111").role("ROLE_STORE").build());
        System.out.println(manager);
    }

    @Test
    @Transactional
    @Rollback(false)
    public void setManager() {
        Manager manager = managerRepository.findById("hello").get();
        manager.setPw("123456"); //수정 가능한 다른 것들도 조건절 해서 추가하면 됨
        System.out.println(manager);

    }

    @Test
    @Transactional
    @Rollback(false)
    public void removeManager() {
        Manager manager = managerRepository.findById("hello").get();
        manager.setDelDate(Timestamp.valueOf(LocalDateTime.now())); //수정 가능한 다른 것들도 조건절 해서 추가하면 됨
        System.out.println(manager);
    }
    @Test
    public void getManager(){
        System.out.println(managerRepository.findAll());
    }
}
