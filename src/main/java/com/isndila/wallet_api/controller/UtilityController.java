package com.isndila.wallet_api.controller;

import com.isndila.wallet_api.dto.UtilityPurchaseRequest;
import com.isndila.wallet_api.dto.UtilityPurchaseResponse;
import com.isndila.wallet_api.entity.UtilityProduct;
import com.isndila.wallet_api.service.UtilityService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/utilities")
public class UtilityController {
    private final UtilityService utilityService;

    public UtilityController(UtilityService utilityService){
        this.utilityService = utilityService;
    }

    @GetMapping("/products")
    public ResponseEntity<List<UtilityProduct>> listProducts(){
        return ResponseEntity.ok(utilityService.getAllProducts());
    }

    @PostMapping("/purchase")
    public ResponseEntity<UtilityPurchaseResponse> purchase(@Valid @RequestBody UtilityPurchaseRequest request){
        return ResponseEntity.ok(utilityService.purchaseUtility(request));
    }
}
