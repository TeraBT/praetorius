package com.bti.controllers;

import com.bti.model.Product;
import com.bti.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductViewController {

//    public void redirectToProductView(int productId) {
//        try {
//            FacesContext.getCurrentInstance().getExternalContext().redirect("product.xhtml?id=" + productId);
//        } catch (IOException e) {
//            e.printStackTrace(); // TODO: log error
//        }
//    }


        @Autowired
        private ProductService productService;

        @GetMapping("/product.xhtml")
        public String viewProductDetails(@RequestParam("id") int productId, Model model) {
            Product product = productService.getProduct(productId).orElse(null);
            model.addAttribute("product", product);
            return "product.xhtml"; // This is the name of the view (productDetails.html or productDetails.jsp)
        }

}
