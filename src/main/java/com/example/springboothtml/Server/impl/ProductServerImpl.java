package com.example.springboothtml.Server.impl;

import com.example.springboothtml.Mapper.ProductMapper;
import com.example.springboothtml.Server.ProductServer;
import com.example.springboothtml.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServerImpl implements ProductServer {
    @Autowired
    private ProductMapper productMapper;
    @Override
    public List<Product> selectBytype(String type) {
        return productMapper.selectBytype(type);
    }

    @Override
    public List<Product> selectAll() {
        List<Product> products = productMapper.selectAll();
//        System.out.println(products);
        return products;
    }

    //模糊查询
    @Override
    public List<Product> selectByName(String name) {
        return productMapper.selectByName(name);
    }

    @Override
    public List<Product> selectQianShi() {
        return productMapper.selectQianShi();
    }
}
