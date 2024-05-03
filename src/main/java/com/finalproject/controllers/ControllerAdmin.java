package com.finalproject.controllers;

import com.finalproject.card.AbstractCard;
import com.finalproject.services.CardService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@AllArgsConstructor
public class ControllerAdmin {

    private ControllerTerminal controllerTerminal;
    private CardService cardService;

    @PatchMapping("/block")
    public String block(String cardId) {
        cardService.block(cardId);
        return "true";
    }

    @PostMapping("/unblock")
    public String unblock(String cardId) {
        cardService.unblock(cardId);
        return "false";
    }

    @GetMapping("/isBlocked")
    public String isBlocked(String cardId) {
        return cardService.isBlocked(cardId).toString();
    }
}
