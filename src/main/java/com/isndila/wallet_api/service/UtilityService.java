package com.isndila.wallet_api.service;

import com.isndila.wallet_api.dto.UtilityPurchaseRequest;
import com.isndila.wallet_api.dto.UtilityPurchaseResponse;
import com.isndila.wallet_api.entity.UtilityProduct;
import com.isndila.wallet_api.entity.Wallet;
import com.isndila.wallet_api.repo.UtilityProductRepo;
import com.isndila.wallet_api.repo.WalletRepo;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UtilityService {
    private final WalletRepo walletRepo;
    private final UtilityProductRepo productRepo;
    private final NotificationService notificationService;

    public UtilityService(WalletRepo walletRepo, UtilityProductRepo productRepo, NotificationService notificationService){
        this.walletRepo = walletRepo;
        this.productRepo = productRepo;
        this.notificationService = notificationService;
    }

    public List<UtilityProduct> getAllProducts(){
        return productRepo.findAll();
    }

    @Transactional
    public UtilityPurchaseResponse purchaseUtility(UtilityPurchaseRequest request){
        Wallet wallet = walletRepo.findById(request.getWalletId())
                .orElseThrow(() -> new RuntimeException("Target system wallet container mapping not found"));
        UtilityProduct product = productRepo.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException("Selected utility tier component matching variant unavailable"));

        if (wallet.getBalance().compareTo(product.getPrice()) < 0){
            throw new IllegalArgumentException("Insufficient wallet balance");
        }

        wallet.setBalance(wallet.getBalance().subtract(product.getPrice()));
        walletRepo.save(wallet);

        String tokenPayload = generatedAssetToken(product.getType());

        notificationService.creationNotification(
                wallet.getUser(),
                "Purchase Confirmed: " + product.getName(),
                "Successfully debited " + wallet.getCurrency() + " " + product.getPrice() + ". Token: " + tokenPayload,
                "TRANSACTION"
        );

        return new UtilityPurchaseResponse("SUCCESS", product.getName(),product.getPrice(),tokenPayload);
    }

    private String generatedAssetToken(String type){
        if ("ELECTRICITY".equalsIgnoreCase(type)){
            return String.format("%04d-%04d-%04d-%04d-%04d",
                    (int)(Math.random()*10000),(int)(Math.random()*10000),
                    (int)(Math.random()*10000), (int)(Math.random()*10000),
                    (int)(Math.random()*10000)
                    );
        }

        return "PIN-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}
