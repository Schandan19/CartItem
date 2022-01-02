package com.src.cartitem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import com.src.cartitem.cartItemsQual;
import com.src.cartitem.CartItems;
import java.util.ArrayList;
import java.util.List;

@Component
@ComponentScan(basePackageClasses = {com.src.cartitem.CartItems.class})
public class Repositorycart implements CartRepository{
    List<CartItems> cartIt=new ArrayList<CartItems>();
    CartItems cI;
    @Autowired
    @Override
    public List<CartItems> addCart(CartItems cI) {
        this.cI=cI;
        cartIt.add(cI);
        return cartIt;
    }
    @Override
    public String findCart(int id) {
        System.out.println("Specified Values:"+cartIt.get(id).toString());
        return cartIt.get(id).toString();
    }
    @Override
    public List<CartItems> showCartAll(){
        System.out.println("All Values:"+cartIt.toString());
        return cartIt;
    }
    @Override
    public List<CartItems> deleteCart(int id){
        cartIt.remove(id);
        System.out.println("Cart Items After Deletion:"+cartIt.toString());
        return cartIt;
    }
    @Override
    public List<CartItems> updateCart(int id,CartItems cI){
        cartIt.set(id,cI);
        System.out.println("Cart Items After Update:"+cartIt.toString());
        return cartIt;
    }
}
