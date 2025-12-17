package com.example.springboothtml.Controller;

import com.example.springboothtml.Common.Result;
import com.example.springboothtml.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.springboothtml.Server.ProductServer;

import java.util.List;

import static com.example.springboothtml.Common.Result.success;

@RestController
@RequestMapping("/product")
//前端跨域处理
@CrossOrigin(origins = "http://localhost:8080")
public class ProductController {
    @Autowired
    private ProductServer productServer;

   @GetMapping("/type")
   public Result<Object> product(@RequestParam String type){
       if (type.equals("all")){
           List<Product> products = productServer.selectAll();
           return Result.success(products,"查询成功");
       }else {
           List<Product> products = productServer.selectBytype(type);
           if (products != null){
               return Result.success(products,"查询成功");
           }
       }
       return Result.fail("查询失败");
   }

   //模糊查询
    @GetMapping("/like")
    public Result<Object> like(@RequestParam String name){
        List<Product> products = productServer.selectByName(name);
        System.out.println(products);
        if (products != null){
            return Result.success(products,"查询成功");
        }
        return Result.fail("查询失败");
    }

    //查询前10条数据
    @GetMapping("/qianShi")
    public Result<Object> qianShi(){
        List<Product> products = productServer.selectQianShi();
        System.out.println( products);
        if (products != null){
            return Result.success(products,"查询成功");
        }
        return Result.fail("查询失败");
    }


}
