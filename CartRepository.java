package com.src.cartitem;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository {
    public List<CartItems> addCart(CartItems c);
    public String findCart(int id);
    public List<CartItems> showCartAll();
    public List<CartItems> deleteCart(int id);
    public List<CartItems> updateCart(int id,CartItems c);
    //public void addCart(CartItems c);
    //public CartItems findCart(int id);
    //public void deleteCart(int id);
}





