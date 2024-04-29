package com.darkwiki.communication.controllers;

import com.darkwiki.communication.dataObjects.AccessPointCommunicationData;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccessPointCommunicationController {

    @PostMapping("/ap-comm-interface")
    public HttpStatus getCommunicationTestData(@RequestBody AccessPointCommunicationData apCommData) {
//        sentData = new CommunicationTestData(msg);
//        extractedMsg = msg;
        System.out.println(apCommData);
        return HttpStatus.OK;
    }

}
