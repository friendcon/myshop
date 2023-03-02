package com.project.myshop.domain;

import com.project.myshop.util.ProductCategoryName;
import jakarta.persistence.*;

@Entity
public class ProductCategory {
    @Id
    @Enumerated(EnumType.STRING)
    private ProductCategoryName id;
}
