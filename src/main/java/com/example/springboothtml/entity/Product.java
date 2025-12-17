package com.example.springboothtml.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Product {
    private Long id;                // 商品ID（主键）
    private String name;            // 商品名称（对应索引idx_name）
    private String description;     // 商品描述
    private BigDecimal price;       // 商品价格
    private String imgUrl;          // 商品图片URL
    private String categoryType;    // 分类标识（对应索引idx_category_type）
    private Integer stock;          // 库存
    private Integer sales;          // 销量
    private Integer sort;           // 排序权重
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
