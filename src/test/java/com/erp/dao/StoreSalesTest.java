package com.erp.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class StoreSalesTest {
    @Autowired
    StoreSalesDAO storeSalesDAO;

    @Test
    void getStoreSalesTest() {
        System.out.println(storeSalesDAO.getStoreSales("2025-12-18","2025-12-30"));
    }
}
