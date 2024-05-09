package com.darkwiki.ui.views;

import com.darkwiki.controllers.*;
import com.darkwiki.model.Product;
import com.darkwiki.model.ProductType;
import com.darkwiki.model.Region;
import com.darkwiki.model.Vendor;
import com.darkwiki.services.ProductService;
import com.darkwiki.services.ProductTypeService;
import com.darkwiki.services.RegionService;
import com.darkwiki.services.VendorService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.*;
import java.util.stream.Collectors;

@Component
@RequestScoped
public class EditRelationView {

    @Autowired
    EditRelationController editRelationController;

    private EditRelationController.OperationType operationType;

    private final List<EditRelationController.OperationType> operationTypeSet =
            Arrays.stream(EditRelationController.OperationType.values()).toList();

    private EditRelationController.OperandCombination operandCombination;

    private final List<EditRelationController.OperandCombination> operandCombinationSet =
            Arrays.stream(EditRelationController.OperandCombination.values()).toList();

    private Collection<Region> regions;

    private Collection<Vendor> vendors;

    private Collection<Product> products;

    private Collection<ProductType> productTypes;

    @PostConstruct
    public void init() {
        regions = editRelationController.getRegions();
        vendors = editRelationController.getVendors();
        products = editRelationController.getProducts();
        productTypes = editRelationController.getProductTypes();
    }

    public void updateOperands(EditRelationController.OperationType operationType,
                               EditRelationController.OperandCombination operandCombination) {

        switch (operationType) {
            case ADD -> {
                switch (operandCombination) {
                    case VENDOR_TO_REGION -> {

                    }
                }
            }
            case REMOVE -> {
                switch (operandCombination) {

                }
            }
        }
    }

    public EditRelationController.OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(EditRelationController.OperationType operationType) {
        this.operationType = operationType;
    }

    public EditRelationController.OperandCombination getOperandCombination() {
        return operandCombination;
    }

    public List<EditRelationController.OperationType> getOperationTypeSet() {
        return operationTypeSet;
    }

    public List<EditRelationController.OperandCombination> getOperandCombinationSet() {
        return operandCombinationSet;
    }
}
