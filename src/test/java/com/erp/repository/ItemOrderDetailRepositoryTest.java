package com.erp.repository;

import com.erp.repository.entity.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

@SpringBootTest
public class ItemOrderDetailRepositoryTest {

    @Autowired
    private ItemOrderDetailRepository repoDetail;

    @Autowired
    private ItemRepository repoItem;

    @Autowired
    private ItemOrderRepository repoOrder;

    @Autowired
    private StoreItemRepository repoStoreItem;

    @Autowired
    private StoreStockRepository repoStoreStock;

    @Test
    void getAlItemOrderDetail(){
        repoDetail.findByItemOrderNo(ItemOrder.builder().itemOrderNo(1L).build()).forEach(System.out::println);
    }

    // 발주 요청 생성(관리자)
    private ItemOrder makeOrder(){
        ItemOrder newOrder = ItemOrder.builder() // 발주 요청 발생
                .storeNo(Store.builder().storeNo(3L).build()) // 요청한 직영점 정보
                .totalItem(2)
                .totalAmount(100000)
                .itemOrderStatus("대기")
                .requestDatetime(new Timestamp(System.currentTimeMillis()))
                .build();
        return repoOrder.save(newOrder);
    }

    // 주문 상세 입력
    @Test
    void makeItemOrderDetail(){
        ItemOrder itemOrder = makeOrder();
        Item item = repoItem.getItemByIngredientName("피자 도우 L");

        ItemOrderDetail orderDetail = ItemOrderDetail
                .builder()
                .itemNo(item)
                .itemOrderNo(itemOrder)
                .orderDetailQuantity(20)
                .orderDetailPrice(item.getItemPrice() * 20)
                .build();

        repoDetail.save(orderDetail);
    }

    // 입고 처리
    @Test
    void receiveItemOrderDetail(){

        ItemOrder itemOrder = repoOrder.findByItemOrderNo(58L);
        ItemOrderDetail orderDetail = repoDetail.findItemOrderDetailByItemOrderDetailNo(itemOrder.getItemOrderNo()); // 아이템 번호 입력


        // 재고 수량 변경
        StoreItem storeItem = repoStoreItem.findByStoreNoAndItemNo(itemOrder.getStoreNo().getStoreNo(), orderDetail.getItemNo().getItemNo()).get(0);
        StoreStock storeStock = repoStoreStock.findFirstByStoreItemNoOrderByStoreStockNoDesc(storeItem.getStoreItemNo()); // 현재 수량 데이터 획득
        storeStock.setChangeDatetime(new Timestamp(System.currentTimeMillis()));
        storeStock.setChangeQuantity(orderDetail.getOrderDetailQuantity());
        storeStock.setChangeReason("입고");
        storeStock.setCurrentQuantity(storeStock.getChangeQuantity() + orderDetail.getOrderDetailQuantity());
        storeStock.setDisposalReason(null);
        repoStoreStock.save(storeStock); // 입고 수량 반영

        // 발주 상세 상태 변경
        orderDetail.setReceiveDatetime(new Timestamp(System.currentTimeMillis()));
        orderDetail.setReceiveQuantity(orderDetail.getOrderDetailQuantity());
        repoDetail.save(orderDetail);
    }
}
