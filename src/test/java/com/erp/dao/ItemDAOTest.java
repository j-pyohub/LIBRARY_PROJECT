//package com.oopsw.erp_project.dao;
//
//import org.junit.jupiter.api.MethodOrderer;
//import org.junit.jupiter.api.Order;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestMethodOrder;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import com.oopsw.erp_project.entity.Item;
//import java.sql.Timestamp;
//import java.time.LocalDateTime;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//public class ItemDAOTest {
//
//    @Autowired
//    private ItemDAO itemDAO;
//
//    @Test
//    void 새_품목_등록() {
//        Item item = Item.builder()
//                .itemCode("TEST-001")
//                .itemCategory("테스트카테고리")
//                .itemName("테스트아이템")
//                .ingredientName("테스트재료명")
//                .stockUnit("ea")
//                .supplyUnit("box")
//                .supplier("테스트공급사")
//                .itemPrice(12345)
//                .convertStock(100)
//                .storageType("냉장")
//                .expirationType("입고 후 n일")
//                .expiration(3)
//                .itemImage("/img/test.png")
//                .note("JUnit 등록 테스트")
//                .build();
//
//        int result = itemDAO.addItem(item);
//
//        System.out.println("▶ insertItem result = " + result);
//        assertEquals(1, result);
//    }
//
//    @Test
//    void 품목_수정() {
//        List<Item> list = itemDAO.selectByItemCode("TEST-001");
//        assertFalse(list.isEmpty());
//
//        Item item = list.get(0);
//        item.setItemName("수정된 아이템명");
//        item.setItemPrice(99999);
//        item.setEditDate(Timestamp.valueOf(LocalDateTime.now()));
//
//        int result = itemDAO.updateItem(item);
//
//        System.out.println("▶ updateItem result = " + result);
//        assertEquals(1, result);
//    }
//
//    @Test
//    void selectItemListTest() {
//        List<Item> list = itemDAO.selectItemList();
//
//        System.out.println("▶ selectItemList = " + list.size());
//        list.forEach(System.out::println);
//
//        assertNotNull(list);
//    }
//
//    @Test
//    void selectByCategoryTest() {
//        List<Item> list = itemDAO.selectByCategory("도우");
//
//        System.out.println("▶ selectByCategory(도우) = " + list.size());
//        list.forEach(System.out::println);
//
//        assertNotNull(list);
//    }
//
//    @Test
//    void selectByItemNameTest() {
//        List<Item> list = itemDAO.selectByItemName("도우 230G");
//
//        System.out.println("▶ selectByItemName = " + list.size());
//        list.forEach(System.out::println);
//
//        assertNotNull(list);
//    }
//
//    @Test
//    void selectByItemCodeTest() {
//        List<Item> list = itemDAO.selectByItemCode("DOUGH-M-230G");
//
//        System.out.println("▶ selectByItemCode = " + list.size());
//        list.forEach(System.out::println);
//
//        assertNotNull(list);
//    }
//
//    @Test
//    void selectByIngredientTest() {
//        List<Item> list = itemDAO.selectByIngredient("페퍼로니");
//
//        System.out.println("▶ selectByIngredient = " + list.size());
//        list.forEach(System.out::println);
//
//        assertNotNull(list);
//    }
//
//    @Test
//    void selectItemDetailTest() {
//        Item item = itemDAO.selectItemDetail(1L);
//
//        System.out.println("▶ selectItemDetail = " + item);
//
//        assertNotNull(item);
//    }
//
//    @Test
//    void deleteItemTest() {
//        List<Item> list = itemDAO.selectByItemCode("TEST-001");
//
//        if (!list.isEmpty()) {
//            Long itemNo = list.get(0).getItemNo();
//            int result = itemDAO.deleteItem(itemNo);
//
//            System.out.println("▶ deleteItem result = " + result);
//            assertEquals(1, result);
//        }
//    }
//}
