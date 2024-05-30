package com.darkwiki.controllers;

import com.darkwiki.model.EntityType;
import com.darkwiki.model.ProductType;
import com.darkwiki.services.*;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Arrays;
import java.util.List;

@Controller
public class DeleteController {

    @Autowired
    OrderService orderService;

    @Autowired
    RegionService regionService;

    @Autowired
    VendorService vendorService;

    @Autowired
    ProductService productService;

    @Autowired
    ProductTypeService productTypeService;

    private List<EntityType> entityTypeList;

    private EntityType entityType;

    private Long entityId;

    @PostConstruct
    public void init() {
        entityTypeList = Arrays.stream(EntityType.values()).toList();
    }

    public DeleteController(OrderService orderService, RegionService regionService, VendorService vendorService, ProductService productService, ProductTypeService productTypeService) {
        this.orderService = orderService;
        this.regionService = regionService;
        this.vendorService = vendorService;
        this.productService = productService;
        this.productTypeService = productTypeService;
    }

    public boolean deleteEntity(EntityType entityType, Long id) {

        boolean succeeded = switch (entityType) {
            case ORDER -> orderService.deleteOrder(id);
            case REGION -> regionService.deleteRegion(id);
            case VENDOR -> vendorService.deleteVendor(id);
            case PRODUCT -> productService.deleteProduct(id);
            case PRODUCT_TYPE -> productTypeService.deleteProductType(id);
        };

        System.out.println(succeeded);
        outputExecutionOutcome(succeeded, entityType, id);

        return succeeded;
    }

    public boolean deleteRegion(Long id) {
        return regionService.deleteRegion(id);
    }

    public boolean deleteVendor(Long id) {
        return vendorService.deleteVendor(id);
    }

    public boolean deleteProduct(Long id) {
        return productService.deleteProduct(id);
    }

    public boolean deleteProductType(Long id) {
        return productTypeService.deleteProductType(id);
    }

    public void outputExecutionOutcome(boolean succeeded, EntityType entityType, Long id) {

        if (succeeded) {
            String outputMessageShort = "Deletion succeeded.";
            String outputMessage = "Entity (%s) %s deleted.".formatted(id, entityType);
            FacesMessage msg = new FacesMessage(outputMessageShort, outputMessage);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            String outputMessageShort = "Deletion failed.";
            String outputMessage = "Entity (%s) %s does not exist.".formatted(id, entityType);

            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, outputMessageShort, outputMessage);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public List<EntityType> getEntityTypeList() {
        return entityTypeList;
    }

    public void setEntityTypeList(List<EntityType> entityTypeList) {
        this.entityTypeList = entityTypeList;
    }

    public EntityType getEntityType() {
        return entityType;
    }

    public void setEntityType(EntityType entityType) {
        this.entityType = entityType;
    }

    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }
}
