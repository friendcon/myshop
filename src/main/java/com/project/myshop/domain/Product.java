package com.project.myshop.domain;

import com.project.myshop.util.DeliveryType;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Product extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String productName;
    private String productContent;
    private String productPrice;
    @Enumerated(EnumType.STRING)
    private DeliveryType deliveryType;
    private String deliveryFee;

    @ManyToOne
    @JoinColumn(name = "product_category_id")
    private ProductCategory productCategory;

    @OneToMany(mappedBy = "product", orphanRemoval = true)
    private List<ProductImage> productImages = new ArrayList<>();
}
