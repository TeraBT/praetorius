package com.bti.controllers;

import com.bti.auxiliaries.OrderReferenceGenerator;
import com.bti.dto.ProductDto;
import com.bti.dto.RegionDto;
import com.bti.dto.RegionDtoSetDto;
import com.bti.dto.VendorDto;
import com.bti.model.*;
import com.bti.services.OrderService;
import com.bti.services.ProductTypeService;
import com.bti.services.RegionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RestController
@CrossOrigin(origins = "*")
public class OrderController {

    @Autowired
    OrderService orderService;

    private final OrderReferenceGenerator orderReferenceGenerator = new OrderReferenceGenerator();
    @Autowired
    private RegionService regionService;
    @Autowired
    private ProductTypeService productTypeService;

    @GetMapping("/void-api/order")
    public ResponseEntity<String> getOrderInitData() throws JsonProcessingException {

        Set<RegionDto> regionDtoSet = new HashSet<>();

        for (Region region : regionService.getAllRegions()) {
            Set<VendorDto> vendorDtoSet = getVendorDtoSet(region);
            RegionDto regionDto = new RegionDto(region.getId(), region.getName(), vendorDtoSet);
            regionDtoSet.add(regionDto);
        }

        RegionDtoSetDto regionDtoSetDto = new RegionDtoSetDto(regionDtoSet);

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .serializeNulls()
                .create();

        String s = gson.toJson(regionDtoSetDto);
        return ResponseEntity.ok(s);
    }

    private static @NotNull Set<VendorDto> getVendorDtoSet(Region region) {
        Set<VendorDto> vendorDtoSet = new HashSet<>();

        for (Vendor vendor : region.getVendorSet()) {
            Set<ProductDto> productDtoSet = new HashSet<>();

            for (Product product : vendor.getProductSet()) {
                Optional<ProductType> productType = Optional.ofNullable(product.getProductType());
                ProductDto productDto = new ProductDto(product.getId(),
                        product.getName(), productType.map(ProductType::getName).orElse("NULL"));
                productDtoSet.add(productDto);
            }

            VendorDto vendorDto = new VendorDto(vendor.getId(), vendor.getName(), productDtoSet);
            vendorDtoSet.add(vendorDto);
        }
        return vendorDtoSet;
    }

    private String testData;

    public String getTestData() {
        return testData;
    }

    public void setTestData(String testData) {
        this.testData = testData;
    }

    @PostMapping("void-api/order")
    public ResponseEntity<String> createOrderTest(@RequestBody String testData) {
        this.testData = testData;
        return ResponseEntity.ok("Parsed correctly. Cf. /order-post.xhtml");
    }

    public Collection<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    public void saveOrder(String vendorName, String productName, double amount) {
        Order order = new Order();
//        Vendor vendor = new Vendor();
//        vendor.setName(vendorName);
//        Product product = new Product();
        order.setCreateTimestamp(LocalDateTime.now(ZoneId.of("UTC+0")));
//        order.setVendor(vendorName);
//        order.setProduct(productName);
        order.setAmount(amount);

        while (true) {
            String orderReference = orderReferenceGenerator.generateOrderReference();
            if (orderService.getOrderByOrderReference(orderReference).isEmpty()) {
                order.setOrderReference(orderReference);
                break;
            }
        }

        orderService.saveOrder(order);
    }

    public void saveOrder(Order order) {
        orderService.saveOrder(order);
    }
}
