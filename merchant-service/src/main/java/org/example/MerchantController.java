package org.example;

import org.example.impl.MerchantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/merchant")
public class MerchantController {
    private final MerchantService service;

    public MerchantController(MerchantService service) { this.service = service; }

    record CreateRequest(String name, String email) {}
    record LinkResponse(String url) {}

    @PostMapping
    public ResponseEntity<Merchant> create(@RequestBody CreateRequest req) {
        return ResponseEntity.ok(service.onboard(req.name(), req.email()));
    }

    @PostMapping("/{id}/links")
    public ResponseEntity<LinkResponse> createLink(@PathVariable String id,
                                                   @RequestParam BigDecimal amount) {
        String url = service.createLink(id, amount);
        return ResponseEntity.ok(new LinkResponse(url));
    }
}
