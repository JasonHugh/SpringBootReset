/**
 * Created by hdfs on 2016/8/1.
 */

import mybatis.api.ProductApi;
import mybatis.model.Product;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

@EnableAutoConfiguration
@RestController
@RequestMapping("/product")

public class ProductController {
    @RequestMapping("")
    public List<Product> view() {
        InputStream is = ProductController.class.getClassLoader().getResourceAsStream("mybatis/conf.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession session = sessionFactory.openSession();
        ProductApi productApi = session.getMapper(ProductApi.class);

        List<Product> products = productApi.selectProducts();
        return products;
    }
    @RequestMapping("/{id}")
    public String getById(@PathVariable("id") Integer id){
        return "hello";
    }

    public static void main(String[] args) {
        SpringApplication.run(ProductController.class);
    }

}

