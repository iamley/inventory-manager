package com.capitole.service.backend.inventory.manager.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name="prices")
public class PricesDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="price_list")
    private Integer priceList;

    @Column(name="brand_id")
    private Integer brandId;

    @Column(name="start_date")
    private LocalDateTime startDate;

    @Column(name="end_date")
    private LocalDateTime endDate;

    @Column(name="product_id")
    private Long productId;

    @Column(name="priority")
    private Integer priority;

    @Column(name="price")
    private BigDecimal price;

    @Column(name="currency")
    private String money;
}
