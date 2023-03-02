package com.project.myshop.domain;

import jakarta.persistence.*;

@Entity
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String productImgUrl;
    private String productTumImgUrl;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
