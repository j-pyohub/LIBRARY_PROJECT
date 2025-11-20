package com.oopsw.erp_project.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ManagerRepositoryTest {
    @Autowired
    ManagerRepository managerRepository;

    @Test
    public void getManager(){
        System.out.println(managerRepository.findAll());
    }


}
