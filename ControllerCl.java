package com.src.cartitem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.src.cartitem.Repositorycart;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import com.src.cartitem.CartItems;

import javax.sql.DataSource;

@RestController
@ComponentScan(basePackageClasses = {com.src.cartitem.Repositorycart.class,com.src.cartitem.CartItems.class,
        com.src.cartitem.CartDataSourceEmbedded.class})
public class ControllerCl {

private Repositorycart repositorycart;
private CartItems cI;
private static final String SELECT_ALL_CART = "SELECT id,brand,model FROM CART_ITEMS";
private static final String INSERT_CART = "INSERT INTO CART_ITEMS(id,brand,model) VALUES(?,?,?)";
private static final String UPDATE_CART = "UPDATE CART_ITEMS SET model=? WHERE id=?";
private static final String DELETE_CART = "DELETE FROM CART_ITEMS WHERE id=?";
private static final String SELECT_SPEC_CART = "SELECT id,brand,model FROM CART_ITEMS WHERE ID=?";
private JdbcTemplate jdbcTemplate;
private CartDataSourceEmbedded c;
private DataSource ds;
    final class CartRMapper implements RowMapper<CartItems> {
        public CartItems mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new CartItems(rs.getInt("id"),
                    rs.getString("brand"),
                    rs.getString("Model"));
        }
    }
    public ControllerCl(Repositorycart repositorycart,CartDataSourceEmbedded c){
        this.ds=c.embeddedDataSource();
        this.repositorycart=repositorycart;
        this.jdbcTemplate=new JdbcTemplate(ds);
        try{
            System.out.println(ds.getClass());
        }
        catch(Exception ex){}
    }
    CartItems cA=new CartItems(1,"Alpha","M");
    CartItems cB=new CartItems(2,"Beret","N");
    CartItems cC=new CartItems(4,"Mosaic","O");
    CartItems cD=new CartItems(7,"Alliance","P");

    CartItems cX=new CartItems();
    //Basic CRUD using embedded database
    /*
    public @ResponseBody int cartItemsAddED(){
        return jdbcTemplate.update(INSERT_CART,cA.getId(),cA.getBrand(),cA.getModel());
    }
    public void deleteCart(int id){
        jdbcTemplate.update(DELETE_CART,id);
    }
    @RequestMapping(value="/cartED", produces="application/json", method = GET)
    public @ResponseBody List<CartItems> cartItemsShowED() {
        return jdbcTemplate.query(SELECT_ALL_CART, new CartRMapper());
    }
    */
    //Basic CRUD without database
    //@ConditionalOnClass({CartItems.class})
    //public @ResponseBody List<CartItems> cartItemsAdd(CartItems cI) { this.cI=cI; repositorycart.addCart(cI);
    @RequestMapping(value="/add", produces="application/json", method = GET)
    public @ResponseBody List<CartItems> cartItemsAdd() {
        repositorycart.addCart(cA);
        repositorycart.addCart(cB);
        repositorycart.addCart(cC);
        repositorycart.addCart(cD);
        repositorycart.showCartAll().remove(0);
        return repositorycart.showCartAll();
    }
    @RequestMapping(value="/cart", produces="application/json", method = GET)
    public @ResponseBody List<CartItems> cartItemsShow(){
        //jdbcTemplate.query(SELECT_ALL_CART, new CartRMapper());
        return repositorycart.showCartAll();
    }
    @RequestMapping(value="/delete", produces="application/json", method = GET)
    public @ResponseBody List<CartItems> cartItemsDel(@RequestParam("id") int cId){
        return repositorycart.deleteCart(cId);
    }
    @RequestMapping(value="/find", produces="application/json", method = GET)
    public @ResponseBody String cartItemsShowSpe(@RequestParam("id") int cId){
        return repositorycart.findCart(cId);
    }
    @RequestMapping(value="/update", produces="application/json", method = GET)
    public @ResponseBody List<CartItems> cartItemsUpdate(@RequestParam("id") int cId){
        cX.setBrand("Qu");
        cX.setModel("Pi");
        cX.setId(cId);
        repositorycart.updateCart(cId,cX);
        return repositorycart.showCartAll();
    }
//----------------------------------------------------------------
    //CRUD Using MVC
    /*
    @RequestMapping(value="/dispspecify",method=GET)
    //@RequestMapping(method=GET)
    public String cartItemSpecificDisp(@RequestParam(value = "id") int cartId, Model model){
        model.addAttribute(cartRepositoryE.findCart(1));
        return "dispspecify";
    }

   @RequestMapping(value="/", method=GET,produces="text/html")
    public String cartItemDisp(@RequestParam("id") int cartId, Model model){
        model.addAttribute(cartRepositoryE.findCart(cartId));
        return "index";
    }
    @RequestMapping(method=GET)
    public String cartItemDisp(Model model){
        //cartRepositoryE.showCartAll().forEach(System.out::println);
        model.addAttribute(cartRepositoryE.showCartAll());
        return "disp";
    }
//,produces="text/html"
//@ResponseBody
    //public @ResponseBody String cartItemAdd(Model model){
    @RequestMapping(value="/", method=GET)
    public String cartItemAdd(Model model){
        //model.addAttribute(cartRepositoryE.addCart(new CartItems()));
        return "index";
    }

    @RequestMapping(value="/delete", method=GET)
    public String cartItemDelete(@RequestParam(value = "id") int cartId,Model model){
        model.addAttribute(cartRepositoryE.deleteCart(cartId));
        return "delete";
    }
    */
}
