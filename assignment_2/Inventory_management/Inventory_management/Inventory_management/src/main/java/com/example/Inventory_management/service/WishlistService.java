package com.example.Inventory_management.service;

import com.example.Inventory_management.entity.Wishlist;
import com.example.Inventory_management.repository.WishlistRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishlistService {

    private final WishlistRepository wishlistRepository;

    public WishlistService(WishlistRepository wishlistRepository) {
        this.wishlistRepository = wishlistRepository;
    }

    public void add(int userId, int productId) {
        wishlistRepository.addToWishlist(userId, productId);
    }

    public void remove(int userId, int productId) {
        wishlistRepository.removeFromWishlist(userId, productId);
    }

    public List<Wishlist> getByUser(int userId) {
        return wishlistRepository.getUserWishlist(userId);
    }
}
