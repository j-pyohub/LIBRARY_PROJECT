package com.erp.repository.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "menu_ingredient")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MenuIngredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_ingredient_no")
    private Long menuIngredientNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_no", nullable = false)
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_no", nullable = false)
    private Menu menu;

    @Column(name = "ingredient_quantity", nullable = false)
    private Integer ingredientQuantity;
}