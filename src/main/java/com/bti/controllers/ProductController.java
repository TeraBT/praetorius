package com.bti.controllers;

import com.bti.model.Product;
import com.bti.services.OrderService;
import com.bti.services.ProductService;
import jakarta.faces.context.FacesContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Collection;

@Controller
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    OrderService orderService;

//    public String initProductView(Long productId) {
//        return "product/" + productId;
//    }

//    @GetMapping("/product/{productId}")
//    public String initProductView(@PathVariable long productId) {
//        System.out.println("Product ID: " + productId);
//        this.product = productService.getProduct(productId).orElse(null);
//        System.out.println(product);
//        return "product";
//    }

    @GetMapping("/product/{product-id}")
    public String initProductView(@PathVariable("product-id") long productId, HttpServletRequest request) {
        Product product = productService.getProduct(productId).orElse(null);
        try {
            HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            httpSession.setAttribute("product", product);
        } catch (Exception e) {
            request.getSession().setAttribute("product", product);
        }
        return "product";
    }

    @GetMapping("/product/{product-id}/edit")
    public String initProductEditView(@PathVariable("product-id") long productId, HttpServletRequest request) {
        Product product = productService.getProduct(productId).orElse(null);
        try {
            HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            httpSession.setAttribute("product", product);
        } catch (Exception e) {
            request.getSession().setAttribute("product", product);
        }
        return "vendor/product-edit";
    }

    public Collection<Product> getAllProducts() {
        return productService.getProductCollection();
    }

    public void saveProduct(String name) {
        Product product = new Product();
        product.setName(name);
        productService.saveProduct(product);
    }

    public void saveProduct(Product product) {
        productService.saveProduct(product);
    }

}
