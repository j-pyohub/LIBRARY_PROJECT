package com.oopsw.erp_project.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.util.List;

@SpringBootTest
//@Transactional   // 테스트 후 자동 롤백
public class ItemRepositoryTest {

    @Autowired
    ItemRepository itemRepository;

    // 1) 품목 등록
    @Test
    void testCreateItem() {

        Item item = Item.builder()
                .itemCode("TEST100")
                .itemCategory("도우")
                .itemName("테스트 도우")
                .ingredientName("밀가루")
                .stockUnit("EA")
                .supplyUnit("BOX")
                .supplier("테스트업체")
                .itemPrice(5000)
                .convertStock(50)
                .storageType("냉동")
                .expirationType("일")
                .expiration(30)
                .inDate(new Timestamp(System.currentTimeMillis()))
                .build();

        Item saved = itemRepository.save(item);

        System.out.println("▶ 등록된 품목: " + saved);

        Assertions.assertNotNull(saved.getItemNo());
    }

    // 2) 품목 수정
    @Test
    void testUpdateItem() {

        Item item = itemRepository.save(
                Item.builder()
                        .itemCode("TEST200")
                        .itemCategory("소스")
                        .itemName("테스트 소스")
                        .ingredientName("토마토")
                        .stockUnit("EA")
                        .supplyUnit("CAN")
                        .supplier("A공급업체")
                        .itemPrice(7000)
                        .convertStock(10)
                        .storageType("냉장")
                        .expirationType("개월")
                        .expiration(6)
                        .build()
        );

        item.setItemPrice(9900);
        item.setSupplier("B공급업체");

        Item updated = itemRepository.save(item);

        System.out.println("▶ 수정된 품목: " + updated);

        Assertions.assertEquals(9900, updated.getItemPrice());
        Assertions.assertEquals("B공급업체", updated.getSupplier());
    }

    // 3) 품목 삭제
    @Test
    void testDeleteItem() {
        Item item = itemRepository.save(
                Item.builder()
                        .itemCode("TEST300")
                        .itemCategory("치즈")
                        .itemName("모짜렐라 치즈")
                        .ingredientName("치즈")
                        .stockUnit("EA")
                        .supplyUnit("BOX")
                        .supplier("치즈공장")
                        .itemPrice(15000)
                        .convertStock(20)
                        .storageType("냉장")
                        .expirationType("일")
                        .expiration(10)
                        .build()
        );

        Long deleteId = item.getItemNo();
        itemRepository.deleteById(deleteId);

        System.out.println("▶ 삭제된 품목 ID: " + deleteId);

        Assertions.assertTrue(itemRepository.findById(deleteId).isEmpty());
    }

    // 4) 품목 전체 목록 조회
    @Test
    void testFindAllItems() {

        itemRepository.save(Item.builder()
                .itemCode("LIST01")
                .itemCategory("야채")
                .itemName("양파")
                .ingredientName("양파")
                .stockUnit("EA")
                .supplyUnit("BAG")
                .supplier("농산물유통")
                .itemPrice(3000)
                .convertStock(30)
                .storageType("상온")
                .expirationType("일")
                .expiration(15)
                .build()
        );

        List<Item> list = itemRepository.findAll();

        System.out.println("▶ 전체 품목 목록");
        list.forEach(System.out::println);

        Assertions.assertFalse(list.isEmpty());
    }

    // 5) 품목 코드 검색
    @Test
    void testFindByItemCode() {

        itemRepository.save(Item.builder()
                .itemCode("CODE999")
                .itemCategory("소스")
                .itemName("칠리소스")
                .ingredientName("칠리")
                .stockUnit("EA")
                .supplyUnit("BOX")
                .supplier("소스마켓")
                .itemPrice(4000)
                .convertStock(40)
                .storageType("상온")
                .expirationType("일")
                .expiration(180)
                .build()
        );

        List<Item> result = itemRepository.findByItemCode("CODE");

        System.out.println("▶ 코드 검색 결과:");
        result.forEach(System.out::println);

        Assertions.assertFalse(result.isEmpty());
    }

    // 6) 품목명 검색
    @Test
    void testFindByName() {

        itemRepository.save(Item.builder()
                .itemCode("NM01")
                .itemCategory("토핑")
                .itemName("파프리카")
                .ingredientName("파프리카")
                .stockUnit("EA")
                .supplyUnit("PACK")
                .supplier("야채야채")
                .itemPrice(6000)
                .convertStock(50)
                .storageType("냉장")
                .expirationType("일")
                .expiration(7)
                .build()
        );

        List<Item> result = itemRepository.findByItemName("파");

        System.out.println("▶ 이름 검색 결과:");
        result.forEach(System.out::println);

        Assertions.assertFalse(result.isEmpty());
    }

    // 7) 재료명 검색
    @Test
    void testFindByIngredient() {

        itemRepository.save(Item.builder()
                .itemCode("ING500")
                .itemCategory("소스")
                .itemName("마늘 소스")
                .ingredientName("마늘")
                .stockUnit("EA")
                .supplyUnit("BOX")
                .supplier("소스공장")
                .itemPrice(2000)
                .convertStock(20)
                .storageType("냉장")
                .expirationType("일")
                .expiration(30)
                .build()
        );

        List<Item> result = itemRepository.findByIngredientName("마늘");

        System.out.println("▶ 재료 검색 결과:");
        result.forEach(System.out::println);

        Assertions.assertFalse(result.isEmpty());
    }

}
