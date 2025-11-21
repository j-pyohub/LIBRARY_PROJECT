package com.oopsw.erp_project;

import com.oopsw.erp_project.dao.ItemDAO;
import com.oopsw.erp_project.repository.ItemOrderDetailRepository;
import com.oopsw.erp_project.repository.ItemOrderRepository;
import com.oopsw.erp_project.repository.ItemRepository;
import com.oopsw.erp_project.vo.ItemOrder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
class ErpProjectApplicationTests {

    @Autowired
    private ItemDAO dao;

    @Autowired
    private ItemOrderRepository repoOrder;

    @Autowired
    private ItemOrderDetailRepository repoDetail;

    @Test
    void myBatisTest() {
        System.out.println(dao.getItems());
    }

    // 전체 발주 내역 조회
    @Test
    void getAllItemsOrder() {
        repoOrder.findAll().iterator().forEachRemaining(System.out::println);
    }

    // 기간 발주 내역 조회
    @Test
    void getItemsOrderByDate(){
        LocalDate today = LocalDate.of(2025, 11, 12);
        repoOrder.findByRequestDatetimeBetween(today.atStartOfDay(), today.plusDays(1).atStartOfDay()).iterator().forEachRemaining(System.out::println);
    }

    // 요잉별 발주 내역 조회
    @Test
    void getItemsOrderByDay(){
        // 일: 1, 월: 2, 화: 3, 수: 4, 목: 5, 금: 6, 토: 7
        repoOrder.findByRequestDatetimeDay(4).iterator().forEachRemaining(System.out::println);
    }

    @Test
    void getItemOrderByStore(){
        repoOrder.findByStoreNo(2L).iterator().forEachRemaining(System.out::println);
    }

    @Test
    void getItemOrderByStatus(){
        // status: 승인, 대기, 반려, 취소
        repoOrder.findByItemOrderStatus("승인").iterator().forEachRemaining(System.out::println);
    }

    @Test
    void getAlItemOrderDetail(){
        repoDetail.findAll().iterator().forEachRemaining(System.out::println);
    }

    @Test
    void cancelItemOrder(){
        List<ItemOrder> orderList = repoOrder.findByItemOrderStatusAndStoreNo("대기", 8L);
        System.out.println(orderList);
        if(!orderList.isEmpty()){
            orderList.get(0).setItemOrderStatus("취소");
            repoOrder.save(orderList.get(0));
        }
    }

    @Test
    void makeOrder(){
        repoOrder.save(new ItemOrder().);
    }

}
