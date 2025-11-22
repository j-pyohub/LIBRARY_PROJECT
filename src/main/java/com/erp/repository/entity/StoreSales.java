package com.erp.repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Setter
@Getter
@ToString
@Table(name = "store_sales")
public class StoreSales {
    @Id
    private long storeSalesNo;
    private long storeNo;
    private Date salesDate;
    private Integer salesPrice;

}
