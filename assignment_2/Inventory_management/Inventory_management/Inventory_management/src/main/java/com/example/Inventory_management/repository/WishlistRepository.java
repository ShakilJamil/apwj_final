package com.example.Inventory_management.repository;


import com.example.Inventory_management.entity.Wishlist;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WishlistRepository {

    private final JdbcTemplate jdbcTemplate;

    public WishlistRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addToWishlist(int userId, int productId) {
        jdbcTemplate.update("INSERT INTO wishlists (user_id, product_id) VALUES (?, ?)", userId, productId);
    }

    public void removeFromWishlist(int userId, int productId) {
        jdbcTemplate.update("DELETE FROM wishlists WHERE user_id = ? AND product_id = ?", userId, productId);
    }

    public List<Wishlist> getUserWishlist(int userId) {
        return jdbcTemplate.query("SELECT * FROM wishlists WHERE user_id = ?",
                (rs, rowNum) -> {
                    Wishlist wishlist = new Wishlist();
                    wishlist.setId(rs.getInt("id"));
                    wishlist.setUserId(rs.getInt("user_id"));
                    wishlist.setProductId(rs.getInt("product_id"));
                    return wishlist;
                },
                userId);
    }
}
