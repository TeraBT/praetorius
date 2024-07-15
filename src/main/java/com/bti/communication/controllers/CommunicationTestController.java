package com.bti.communication.controllers;

import com.bti.communication.dataObjects.CommunicationTestData;
import com.bti.services.OrderService;
import com.bti.services.RegionService;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class CommunicationTestController {
    private final OrderService orderService;
    private final RegionService regionService;
    private CommunicationTestData sentData = new CommunicationTestData("");
    private String extractedMsg;

    public CommunicationTestController(OrderService orderService, RegionService regionService) {
        this.orderService = orderService;
        this.regionService = regionService;
    }

    @PostMapping("/communication-test-interface")
    public String getCommunicationTestData(@RequestBody String msg) {
        sentData = new CommunicationTestData(msg);
        extractedMsg = msg;
//        return HttpStatus.OK;
        return "hello world";
    }

    @GetMapping("/order-get-data")
    public Set<String> getOrder() {
        Set<String> idRefMapping = orderService.getAllOrders().stream().map(
                o -> ("ID: " + o.getId() + "; Ref.:" + o.getOrderReference())
        ).collect(Collectors.toSet());
        return idRefMapping;
    }

    @GetMapping("/region")
    public Set<String> getRegion() {
        Set<String> regionSet = regionService.getAllRegions().stream().map(
                o -> ("ID: " + o.getId() + "; Name:" + o.getName())
        ).collect(Collectors.toSet());
        return regionSet;
    }

    public CommunicationTestData getSentData() {
        return sentData;
    }

    public void setSentData(CommunicationTestData sentData) {
        this.sentData = sentData;
    }

    public String getExtractedMsg() {
        return extractedMsg;
    }

    public void setExtractedMsg(String extractedMsg) {
        this.extractedMsg = extractedMsg;
    }
}
