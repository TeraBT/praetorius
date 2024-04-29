package com.darkwiki.communication.controllers;

import com.darkwiki.communication.dataObjects.CommunicationTestData;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommunicationTestController {
    private CommunicationTestData sentData = new CommunicationTestData("");
    private String extractedMsg;

    @PostMapping("/communication-test-interface")
    public HttpStatus getCommunicationTestData(@RequestBody String msg) {
        sentData = new CommunicationTestData(msg);
        extractedMsg = msg;
        return HttpStatus.OK;
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
