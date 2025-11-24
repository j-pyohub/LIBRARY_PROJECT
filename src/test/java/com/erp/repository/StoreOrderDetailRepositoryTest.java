package com.erp.repository;

import com.erp.repository.dto.SalesOrderDTO;
import com.erp.repository.entity.SalesOrder;
import com.erp.repository.entity.Store;
import com.erp.repository.entity.StoreMenu;
import com.erp.repository.entity.StoreOrderDetail;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class StoreOrderDetailRepositoryTest {

    @Autowired
    private StoreOrderDetailRepository storeOrderDetailRepository;

    @Autowired
    private SalesOrderRepository salesOrderRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private StoreMenuRepository storeMenuRepository;

    @Test
    void findDailyMenuSalesTest(){
        System.out.println(storeOrderDetailRepository.findDailyMenuSales(LocalDateTime.of(2024,1,7,0,0   ),LocalDateTime.of(2024,1,8,0,0)));
    }
    @Test
    void findDailyMenuSalesByStore(){
        System.out.println(storeOrderDetailRepository.findDailyMenuSalesByStore(5L,LocalDateTime.of(2024,1,7,0,0   ),LocalDateTime.of(2024,1,8,0,0)));
    }


    @Test
    @Transactional
    @Rollback(false)
    void calculateOrderSummaryTest() {
        //샘플 넣은 거라서 코드가 깁니다..! service에선 예제샘플 넣을 필요 없으니 짧아질 듯..
        Store store = storeRepository.findById(2L)
                .orElseThrow(() -> new RuntimeException("Store not found"));
        SalesOrder order = new SalesOrder();
        order.setStore(store);
        order.setSalesOrderDatetime(LocalDateTime.now());
        order.setSalesOrderAmount(0);
        salesOrderRepository.save(order);

        Long orderNo = order.getSalesOrderNo();

        StoreMenu menu13 = storeMenuRepository.findById(13L)
                .orElseThrow(() -> new RuntimeException("Menu 13 not found"));

        StoreMenu menu14 = storeMenuRepository.findById(14L)
                .orElseThrow(() -> new RuntimeException("Menu 14 not found"));

        StoreOrderDetail detail1 = new StoreOrderDetail();
        detail1.setSalesOrder(order);
        detail1.setStoreMenu(menu13);
        detail1.setMenuCount(2);
        detail1.setMenuPrice(10000);
        storeOrderDetailRepository.save(detail1);

        StoreOrderDetail detail2 = new StoreOrderDetail();
        detail2.setSalesOrder(order);
        detail2.setStoreMenu(menu14);
        detail2.setMenuCount(1);
        detail2.setMenuPrice(8000);
        storeOrderDetailRepository.save(detail2);

        SalesOrderDTO summary = storeOrderDetailRepository.countSalesOrder(orderNo);
        int totalAmount = summary.getSalesOrderAmount();
        int totalMenu = summary.getSalesOrderCount();
        order.setSalesOrderAmount(totalAmount);
        salesOrderRepository.save(order);

        System.out.println("총 판매금액 = " + order.getSalesOrderAmount());
        System.out.println("총 메뉴개수 = " + totalMenu);

    }

    @Test
    @Transactional
    void getStoreOrderDetailTest() {
        Long salesOrderNo = 655L;
        List<StoreOrderDetail> details = storeOrderDetailRepository.getStoreOrderDetail(salesOrderNo);

        StoreOrderDetail d = details.get(0);

        System.out.println("===== [주문 정보] =====");
        System.out.println("주문번호 : " + d.getSalesOrder().getSalesOrderNo());
        System.out.println("주문일시 : " + d.getSalesOrder().getSalesOrderDatetime());
        System.out.println("매장명   : " + d.getSalesOrder().getStore().getStoreName());

        //나중에 dto로 묶어서 뷰 내보내면 됨
        System.out.println("\n===== [주문 상세 목록] =====");
        for (StoreOrderDetail detail : details) {
            System.out.println("상세번호 : " + detail.getStoreOrderDetailNo());
            System.out.println("메뉴명   : " + detail.getStoreMenu().getMenu().getMenuName());
            System.out.println("사이즈   : " + detail.getStoreMenu().getMenu().getSize());
            System.out.println("단가     : " + detail.getMenuPrice());
            System.out.println("수량     : " + detail.getMenuCount());
            System.out.println("총금액   : " + detail.getMenuPrice() * detail.getMenuCount());
            System.out.println("--------------------------------------");
        }
    }
}
