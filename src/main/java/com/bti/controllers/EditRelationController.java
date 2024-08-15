package com.bti.controllers;

import com.bti.model.Product;
import com.bti.model.ProductType;
import com.bti.model.Region;
import com.bti.model.Vendor;
import com.bti.services.ProductService;
import com.bti.services.ProductTypeService;
import com.bti.services.RegionService;
import com.bti.services.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.*;


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
                            region.get().addToVendorSet(vendor.get());
                        }
                    }
                    case PRODUCT_TO_VENDOR -> {
                        Optional<Vendor> vendor = vendorService.getVendor(operandName2);
                        Optional<Product> product = productService.getProduct(operandName1);
                        if (vendor.isPresent() && product.isPresent()) {
                            vendor.get().addToProductSet(product.get());
                        }
                    }
                    case PRODUCT_TYPE_TO_PRODUCT -> {
                        Optional<Product> product = productService.getProduct(operandName2);
                        Optional<ProductType> productType = productTypeService.getProductType(operandName1);
                        if (productType.isPresent() && product.isPresent()) {
                            productType.get().addToProductSet(product.get());
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
                            region.get().removeFromVendorSet(vendor.get());
                        }
                    }
                    case PRODUCT_TO_VENDOR -> {
                        Optional<Vendor> vendor = vendorService.getVendor(operandName2);
                        Optional<Product> product = productService.getProduct(operandName1);
                        if (vendor.isPresent() && product.isPresent()) {
                            vendor.get().removeFromProductSet(product.get());
                        }
                    }
                    case PRODUCT_TYPE_TO_PRODUCT -> {
                        Optional<Product> product = productService.getProduct(operandName2);
                        Optional<ProductType> productType = productTypeService.getProductType(operandName1);
                        if (productType.isPresent() && product.isPresent()) {
                            productType.get().removeFromProductSet(product.get());
                        }
                    }
                }
            }
        }
    }

    // TODO: Naming convention 'getAll...'
    public Collection<Region> getRegions() {
        return regionService.getAllRegions();
    }

    public Collection<Vendor> getVendors() {
        return vendorService.getAllVendors();
    }

    public Collection<Product> getProducts() {
        return productService.getProductCollection();
    }

    public Collection<ProductType> getProductTypes() {
        return productTypeService.getAllProductTypes();
    }
}
