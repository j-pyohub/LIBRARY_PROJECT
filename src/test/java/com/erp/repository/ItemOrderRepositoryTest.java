package com.erp.repository;

import com.erp.dao.ItemDAO;
import com.erp.dto.ItemOrderDTO;
import com.erp.repository.entity.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

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

    @Autowired
    private ItemRepository repoItem;

    /* 발주 내역 조회 */
    // 전체 발주 내역 조회
    @Test
    void getAllItemsOrder() {
        repoOrder.findAll(
                PageRequest.of(0, 8, Sort.by("itemOrderNo").descending())
        ).forEach(System.out::println);
    }

    // 기간 발주 내역 조회
    @Test
    void getItemsOrderByDate(){
        repoOrder.findByRequestDatetimeBetween(
                LocalDate.of(2025, 11, 12).atStartOfDay(),
                LocalDate.of(2025, 11, 20).atStartOfDay(),
                PageRequest.of(0, 8, Sort.by("itemOrderNo").descending())
        ).forEach(System.out::println);
    }

    // 요일별 발주 내역 조회
    @Test
    void getItemsOrderByDay(){
        // 일: 1, 월: 2, 화: 3, 수: 4, 목: 5, 금: 6, 토: 7
        repoOrder.findByRequestDatetimeDay(
                4,
                PageRequest.of(0, 8, Sort.by("itemOrderNo").descending())
        ).forEach(System.out::println);
    }

    // 발주 요청자(직영점) 별 조회
    @Test
    void getItemOrderByStore(){
        repoOrder.findByStoreNo(
                Store.builder().storeNo(2L).build(),
                PageRequest.of(0, 8, Sort.by("itemOrderNo").descending())
        ).forEach(System.out::println);
    }

    // 발주 상태별 조회
    @Test
    void getItemOrderByStatus(){
        // status: 승인, 대기, 반려, 취소
        repoOrder.findByItemOrderStatus(
                "승인",
                PageRequest.of(0, 8, Sort.by("itemOrderNo").descending())
        ).forEach(System.out::println);
    }

    /// //////////////////////////////////////////////////////////////

    /* 발주 상세 조회 모달 */
    // 발주 상세 보기(발주번호에 해당하는)
    @Test
    void getItemOrderDetailByItemOrder(){
        repoDetail.findByItemOrderNo(
                ItemOrder.builder().itemOrderNo(1L).build()
        ).forEach(System.out::println);
    }

    // 발주 요청 취소
    @Test
    void cancelItemOrder(){
        // 대기 중 발주 선택
        List<ItemOrder> orderList = repoOrder.findByItemOrderStatusAndStoreNo(
                "대기",
                Store.builder().storeNo(2L).build() // 직영점 번호 지정
        );

        // 선택한 발주 요청 번호 데이터 상태 취소 변경
        if(!orderList.isEmpty()){
            orderList.get(0).setItemOrderStatus("취소"); // 상태 변경
            orderList.get(0).setProcessDatetime(new Timestamp(System.currentTimeMillis())); // 처리 시간
            repoOrder.save(orderList.get(0));
        }
    }

    // 발주 요청 승인
    @Test
    void approveOrder(){
        // 대기 중 발주 선택
        List<ItemOrder> orderList = repoOrder.findByItemOrderStatusAndStoreNo(
                "대기",
                Store.builder().storeNo(3L).build() // 직영점 번호 지정
        );

        // 선택한 발주 요청 번호 데이터 상태 승인 변경
        if(!orderList.isEmpty()){
            orderList.get(0).setManagerId(Manager.builder().managerId("galaxy0712").build());
            orderList.get(0).setItemOrderStatus("승인"); // 상태 변경
            orderList.get(0).setProcessDatetime(new Timestamp(System.currentTimeMillis())); // 처리 시간
            repoOrder.save(orderList.get(0));
        }
    }

    // 발주 요청 반려
    @Test
    void declineOrder(){
        // 대기 중 발주 선택
        List<ItemOrder> orderList = repoOrder.findByItemOrderStatusAndStoreNo(
                "대기",
                Store.builder().storeNo(3L).build() // 직영점 번호 지정
        );

        // 선택한 발주 요청 번호 데이터 상태 반려 변경
        if(!orderList.isEmpty()){
            orderList.get(0).setManagerId(Manager.builder().managerId("galaxy0712").build());
            orderList.get(0).setItemOrderStatus("반려");
            orderList.get(0).setProcessDatetime(new Timestamp(System.currentTimeMillis()));
            repoOrder.save(orderList.get(0));
        }
    }


    // 발주 요청 생성(관리자)
    @Test
    void makeItemOrder(){
        ItemOrder newOrder = ItemOrder.builder() // 발주 요청 발생
                .storeNo(Store.builder().storeNo(3L).build()) // 요청한 직영점 정보
                .totalItem(2)
                .totalAmount(100000)
                .itemOrderStatus("대기")
                .requestDatetime(new Timestamp(System.currentTimeMillis()))
                .build();
        repoOrder.save(newOrder);
    }

    // 승인 목록 조회
    @Test
    void getApprovedOrder(){
        repoOrder.findByItemOrderStatus("승인", PageRequest.of(0, 8)).forEach(System.out::println);
    }

    // 승인 목록 상세 조회
    @Test
    void getApprovedOrderDetail(){
        Page<ItemOrderDTO> order = repoOrder.findByItemOrderStatus("승인", PageRequest.of(0, 8)); // itemOrder 번째 선택

        Long itemOrderNo = order.getContent().iterator().next().getItemOrderNo();
        repoDetail.findByItemOrderNo(ItemOrder.builder().itemOrderNo(itemOrderNo).build()).forEach(System.out::println);
    }
}
