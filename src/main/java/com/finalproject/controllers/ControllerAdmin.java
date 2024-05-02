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
        String block = String.valueOf(cardService.block(cardId));
        return block;
    }

    @PostMapping("/unblock")
    public String unblock(String cardId) {
        String unblock = String.valueOf(cardService.unblock(cardId));
        return unblock;
    }

    @GetMapping("/isBlocked")
    public String isBlocked(String cardId) {
        String isUnblocked = String.valueOf(cardService.isBlocked(cardId));
        return isUnblocked;
    }
}
