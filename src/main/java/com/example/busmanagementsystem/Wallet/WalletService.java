package com.example.busmanagementsystem.Wallet;

import com.example.busmanagementsystem.Wallet.Wallet;
import com.example.busmanagementsystem.Wallet.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WalletService {
    private final WalletRepository walletRepository;

    @Autowired
    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    public List<Wallet> getWallets() {
        return walletRepository.findAll();
    }

    public void addNewWallet(Wallet wallet) {
        Optional<Wallet> optionalWallet =  walletRepository.findWalletBySid(wallet.getSid());
        if (optionalWallet.isPresent()) {
            throw new IllegalStateException("Account Already Exists. Please Try Signing In.");
        } else {
            walletRepository.save(wallet);
        }
    }

    public void deleteWalletBySid(Integer walletId) {
        Optional<Wallet> optionalWallet =  walletRepository.findWalletBySid(walletId);
        if (optionalWallet.isPresent()) {
            walletRepository.deleteById(optionalWallet.get().getId());
        } else {
            throw new IllegalStateException("Wallet with ID "+walletId+" does not exist!");
        }
    }
}
