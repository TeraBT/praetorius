package com.darkwiki.controllers;

import com.darkwiki.model.Product;
import com.darkwiki.model.ProductType;
import com.darkwiki.model.Region;
import com.darkwiki.model.Vendor;
import com.darkwiki.services.ProductService;
import com.darkwiki.services.ProductTypeService;
import com.darkwiki.services.RegionService;
import com.darkwiki.services.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.*;
import java.util.stream.Collectors;


@Controller
public class EditRelationController {

    @Autowired
    RegionService regionService;

    @Autowired
    VendorService vendorService;

    @Autowired
    ProductService productService;

    @Autowired
    ProductTypeService productTypeService;

    public enum OperationType {
        ADD, REMOVE
    }

    public enum OperandCombination {
        VENDOR_TO_REGION("Vendor to/from Region"),
        PRODUCT_TO_VENDOR("Product to/from Vendor"),
        PRODUCT_TYPE_TO_PRODUCT("Product Type to/from Product");

        private final String description;

        OperandCombination(String description) {
            this.description = description;
        }

        @Override
        public String toString() {
            return description;
        }
    }

    public void executeOperation(OperationType operationType, OperandCombination operandCombination,
                                 String operandName1, String operandName2) {

        switch (operationType) {
            case ADD -> {
                switch (operandCombination) {
                    case VENDOR_TO_REGION -> {
                        Optional<Region> region = regionService.getRegion(operandName2);
                        Optional<Vendor> vendor = vendorService.getVendor(operandName1);
                        if (region.isPresent() && vendor.isPresent()) {
                            region.get().addVendor(vendor.get());
                        }
                    }
                    case PRODUCT_TO_VENDOR -> {
                        Optional<Vendor> vendor = vendorService.getVendor(operandName2);
                        Optional<Product> product = productService.getProduct(operandName1);
                        if (vendor.isPresent() && product.isPresent()) {
                            vendor.get().addProduct(product.get());
                        }
                    }
                    case PRODUCT_TYPE_TO_PRODUCT -> {
                        Optional<Product> product = productService.getProduct(operandName2);
                        Optional<ProductType> productType = productTypeService.getProductType(operandName1);
                        if (productType.isPresent() && product.isPresent()) {
                            productType.get().addProduct(product.get());
                        }
                    }
                }
            }
            case REMOVE -> {
                switch (operandCombination) {
                    case VENDOR_TO_REGION -> {
                        Optional<Region> region = regionService.getRegion(operandName2);
                        Optional<Vendor> vendor = vendorService.getVendor(operandName1);
                        if (region.isPresent() && vendor.isPresent()) {
                            region.get().removeVendor(vendor.get());
                        }
                    }
                    case PRODUCT_TO_VENDOR -> {
                        Optional<Vendor> vendor = vendorService.getVendor(operandName2);
                        Optional<Product> product = productService.getProduct(operandName1);
                        if (vendor.isPresent() && product.isPresent()) {
                            vendor.get().removeProduct(product.get());
                        }
                    }
                    case PRODUCT_TYPE_TO_PRODUCT -> {
                        Optional<Product> product = productService.getProduct(operandName2);
                        Optional<ProductType> productType = productTypeService.getProductType(operandName1);
                        if (productType.isPresent() && product.isPresent()) {
                            productType.get().removeProduct(product.get());
                        }
                    }
                }
            }
        }
    }

    public Collection<Region> getRegions() {
        return regionService.getAllRegions();
    }

    public Collection<Vendor> getVendors() {
        return vendorService.getAllVendors();
    }

    public Collection<Product> getProducts() {
        return productService.getAllProducts();
    }

    public Collection<ProductType> getProductTypes() {
        return productTypeService.getAllProductTypes();
    }
}
