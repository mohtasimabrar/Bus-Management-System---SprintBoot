package com.example.busmanagementsystem.Wallet;

import com.example.busmanagementsystem.Wallet.Wallet;
import com.example.busmanagementsystem.Wallet.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/wallet")
public class WalletController {

    private final WalletService walletService;

    @Autowired
    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @GetMapping
    public List<Wallet> getWallets () {
        return walletService.getWallets();
    }

    @PostMapping
    public void registerNewWallet(@RequestBody Wallet wallet) {
        walletService.addNewWallet(wallet);
    }

    @DeleteMapping(path = "{walletSid}")
    public void deleteWallet(@PathVariable("walletSid") Integer walletSid){
        walletService.deleteWalletBySid(walletSid);
    }
}
