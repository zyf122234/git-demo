package com.example.springboothtml.Mapper;

import com.example.springboothtml.entity.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProductMapper {
    @Select("SELECT * FROM product WHERE category_type = #{type}")
    List<Product> selectBytype(String type);

    @Select("SELECT * FROM product")
    List<Product> selectAll();

    @Select("SELECT * FROM product WHERE name like CONCAT('%', #{name}, '%') ")
    List<Product> selectByName(String name);

    @Select("SELECT * FROM product ORDER BY id limit 10")
    List<Product> selectQianShi();

}
