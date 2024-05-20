package com.finalproject.controllers;

import com.finalproject.MockData;
import com.finalproject.services.CardService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@AllArgsConstructor
public class ControllerAdmin {

    private CardService cardService;
    private MockData mockData;

    @PostMapping("/block")
    public String block(String cardId, String terminalId) {
        return cardService.block(cardId, terminalId);
    }

    @PostMapping("/unblock")
    public String unblock(String cardId, String terminalId) {
        return cardService.unblock(cardId, terminalId);
    }

    @GetMapping("/isBlocked")
    public String isBlocked(String cardId) {
        return cardService.isBlocked(cardId);
    }

    @PostMapping("/generateMockData")
    public String generate(long numberOfRecords) {
        mockData.generateMockData(numberOfRecords);
        return "Data was generated";
    }
}
