package com.oopsw.erp_project;

import com.oopsw.erp_project.dao.ItemDAO;
import com.oopsw.erp_project.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

@SpringBootTest
class ItemOrderRepositoryTest {

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

        System.out.println(repoOrder.findAll());
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
        repoOrder.findByStoreNo(Store.builder().storeNo(2L).build()).iterator().forEachRemaining(System.out::println);
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
        List<ItemOrder> orderList = repoOrder.findByItemOrderStatusAndStoreNo("대기", Store.builder().storeNo(2L).build());
        System.out.println(orderList);

        // 발주 요청 no 선택
        if(!orderList.isEmpty()){
            orderList.get(0).setItemOrderStatus("취소"); // 상태 변경
            orderList.get(0).setProcessDatetime(new Timestamp(System.currentTimeMillis())); // 처리 시간
            repoOrder.save(orderList.get(0));
        }
    }

    @Test
    void makeOrder(){
        ItemOrder newOrder = new ItemOrder().builder() // 발주 요청 발생
                .storeNo(Store.builder().storeNo(3L).build())
                .totalItem(2)
                .totalAmount(100000)
                .itemOrderStatus("대기")
                .requestDatetime(new Timestamp(System.currentTimeMillis()))
                .build();
        repoOrder.save(newOrder);
    }

    @Test
    void approveOrder(){
        List<ItemOrder> orderList = repoOrder.findByItemOrderStatusAndStoreNo("대기", Store.builder().storeNo(3L).build());
        System.out.println(orderList);

        // 발주 요청 no 선택
        if(!orderList.isEmpty()){
            orderList.get(0).setManagerId(Manager.builder().managerId("galaxy0712").build());
            orderList.get(0).setItemOrderStatus("승인"); // 상태 변경
            orderList.get(0).setProcessDatetime(new Timestamp(System.currentTimeMillis())); // 처리 시간
            repoOrder.save(orderList.get(0));
        }
    }
}
