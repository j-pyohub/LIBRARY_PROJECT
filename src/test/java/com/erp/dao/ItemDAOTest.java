package com.erp.dao;

import com.erp.repository.entity.Item;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ItemDAOTest {

    @Autowired
    private ItemDAO itemDAO;

    @Test
    void 새_품목_등록() {

        Item item = Item.builder()
                .itemCode("TEST-001")
                .itemCategory("테스트카테고리")
                .itemName("테스트아이템")
                .ingredientName("테스트재료명")
                .stockUnit("ea")
                .supplyUnit("box")
                .supplier("테스트공급사")
                .itemPrice(12345)
                .convertStock(100)
                .storageType("냉장")
                .expirationType("입고 후 n일")
                .expiration(3)
                .itemImage("/img/test.png")
                .note("JUnit 등록 테스트")
                .build();

        int result = itemDAO.addItem(item);

        System.out.println("▶ addItem result = " + result);
        assertEquals(1, result);
    }

    @Test
    void 품목_수정() {
        List<Item> list = itemDAO.getByItemCode("TEST-001");
        assertFalse(list.isEmpty());

        Item item = list.get(0);

        Long itemNo = item.getItemNo();
        assertNotNull(itemNo);

        // 수정 필드들 적용
        item.setItemCategory("수정된카테고리");
        item.setItemCode("EDIT-001");
        item.setItemName("수정된 품목명");
        item.setIngredientName("수정된 재료명");
        item.setStockUnit("g");
        item.setSupplyUnit("box");
        item.setConvertStock(777);
        item.setItemPrice(99999);
        item.setSupplier("수정된 공급사");
        item.setStorageType("냉동");
        item.setExpirationType("제조일자 기준");
        item.setExpiration(30);
        item.setItemImage("/img/edited.png");
        item.setNote("수정된 테스트 비고");

        // edit_date는 XML에서 NOW()로 업데이트

        int result = itemDAO.setItem(item);
        assertEquals(1, result);

        // 수정된 값 검증
        Item updated = itemDAO.getItemDetail(itemNo);
        assertEquals("EDIT-001", updated.getItemCode());
        assertEquals("수정된 품목명", updated.getItemName());

        System.out.println("▶ 수정된 item = " + updated);
    }

    @Test
    void 전체_품목_목록_조회() {
        List<Item> list = itemDAO.getItemList();

        System.out.println("▶ getItemList = " + list.size());
        list.forEach(System.out::println);

        assertNotNull(list);
    }

    @Test
    void 카테고리_검색() {
        List<Item> list = itemDAO.getByCategory("도우");

        System.out.println("▶ getByCategory(도우) = " + list.size());
        list.forEach(System.out::println);

        assertNotNull(list);
    }


    @Test
    void 품목명_검색() {
        List<Item> list = itemDAO.getByItemName("도우");

        System.out.println("▶ getByItemName = " + list.size());
        list.forEach(System.out::println);

        assertNotNull(list);
    }


    @Test
    void 품목코드_검색() {
        List<Item> list = itemDAO.getByItemCode("DOUGH");

        System.out.println("▶ getByItemCode = " + list.size());
        list.forEach(System.out::println);

        assertNotNull(list);
    }


    @Test
    void 재료명_검색() {
        List<Item> list = itemDAO.getByIngredient("페퍼로니");

        System.out.println("▶ getByIngredient = " + list.size());
        list.forEach(System.out::println);

        assertNotNull(list);
    }


    @Test
    void 품목_상세_보기() {
        Item item = itemDAO.getItemDetail(1L);

        System.out.println("▶ getItemDetail = " + item);

        assertNotNull(item);
    }


    @Test
    void 품목_삭제() {

        List<Item> list = itemDAO.getByItemCode("EDIT-001");

        if (!list.isEmpty()) {
            Long itemNo = list.get(0).getItemNo();

            int result = itemDAO.removeItem(itemNo);

            System.out.println("▶ removeItem result = " + result);
            assertEquals(1, result);
        }
    }
}
