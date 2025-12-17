package com.example.springboothtml.Server;


import com.example.springboothtml.entity.Product;

import java.util.List;

public interface ProductServer {
    List<Product> selectBytype(String type);
    List<Product> selectAll();
    List<Product> selectByName(String name);
    List<Product> selectQianShi();
}
