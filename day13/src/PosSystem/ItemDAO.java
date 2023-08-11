package PosSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

public class ItemDAO {
// CRUD 기능
// 싱글톤
   private static ItemDAO instance = new ItemDAO();
   public static ItemDAO getInstance() {
      return instance;
   }
   
   // 아이템 가져오기
   public Vector<String> getItem(){
      Vector<ItemDTO> dbitemlist = getAllItem();
      Vector<String> itemlist = new Vector<String>();
      for(ItemDTO item : dbitemlist) {
         itemlist.add(item.getItem_name());
      }
      return itemlist;
      
   }
   
   
   // 모든 상품 가져오기
   public Vector<ItemDTO> getAllItem(){
      Vector<ItemDTO> list = new Vector<ItemDTO>();
      Connection conn = null;
      PreparedStatement ps = null;
      ResultSet rs = null;
      String price = null;
      String sql = "select * from item";
      try {
         conn = DBConnect.connect();
         ps = conn.prepareStatement(sql);
         rs = ps.executeQuery();
         
         while(rs.next()) {
            ItemDTO item = new ItemDTO();
            item.setId(rs.getInt("id"));
            item.setItem_name(rs.getString("item_name"));
            item.setItem_stock(rs.getInt("item_stock"));
            item.setItem_price(rs.getInt("item_price"));
            list.add(item);
         }
         
      }catch(Exception e) {
         e.printStackTrace();
      }finally {
         DBConnect.close();
      }
      return list;
      
   }
   
   
   // 상품 이름으로 가격 가져오기
   public String getprice(String item_name) {
      Connection conn = null;
      PreparedStatement ps = null;
      ResultSet rs = null;
      
      String price = null;
      String sql = "select item_price from item where item_name=?";
      try {
         conn = DBConnect.connect();
         ps = conn.prepareStatement(sql);
         ps.setString(1, item_name);
         rs = ps.executeQuery();
         if(rs.next()) {
            price = Integer.toString(rs.getInt("item_price"));
         }
      }catch(Exception e) {
         e.printStackTrace();
      }finally {
         DBConnect.close();
      }
      return price;
   }
   
   // 상품 이름으로 수량가져오기
   
   public String getStock(String item_name) {
      Connection conn = null;
      PreparedStatement ps = null;
      ResultSet rs = null;
      String stock = null;
      String sql = "select item_stock from item where item_name = ?";
      
      try {
         conn = DBConnect.connect();
         ps = conn.prepareStatement(sql);
         ps.setString(1, item_name);
         rs = ps.executeQuery();
         if(rs.next())
            stock = Integer.toString(rs.getInt("item_stock"));
      }catch(Exception e) {
         e.printStackTrace();
      }finally {
         DBConnect.close();
      }
      
      return stock;
            
   }
   
   
   // 수량 업데이트
   public void updateStock(String total, String stock, String name) {
      Connection conn = null;
      PreparedStatement ps = null;
      String sql = "update item set item_stock = ? - ? where item_name=?";
      try {
         conn = DBConnect.connect();
         ps = conn.prepareStatement(sql);
         ps.setString(1, total);
         ps.setString(2, stock);
         ps.setString(3, name);
         ps.executeUpdate();
         
      }catch(Exception e) {
         e.printStackTrace();
      }finally {
         DBConnect.close();
      }
      
   }
   
   // 상품 추가
   public boolean insertStock(ItemDTO item) {
      Connection conn = null;
      PreparedStatement ps = null;
      String sql = 
      "insert into item(item_name,item_stock, item_price) values (?,?,?)";
      boolean result = false;
      
      try {
         conn = DBConnect.connect();
         ps = conn.prepareStatement(sql);
         ps.setString(1, item.getItem_name());
         ps.setInt(2, item.getItem_stock());
         ps.setInt(3, item.getItem_price());
         int r = ps.executeUpdate();
         if(r > 0)
            result = true;
               
      }catch(Exception e) {
         e.printStackTrace();
      }finally {
         DBConnect.close();
      }
      return result;
   }
   
   // 상품 수정
   public boolean updateitem(ItemDTO item) {
      Connection conn = null;
      PreparedStatement ps = null;
      
      String sql =
      "update item set item_name=?, item_stock=?, item_price=? where(id=?)";
      boolean result = false;
      try {
         conn = DBConnect.connect();
         ps = conn.prepareStatement(sql);
         ps.setString(1, item.getItem_name());
         ps.setInt(2, item.getItem_stock());
         ps.setInt(3, item.getItem_price());
         ps.setInt(4, item.getId());
         int r = ps.executeUpdate();
         if(r > 0)
            result = true;
         
      }catch(Exception e) {
         e.printStackTrace();
      }finally {
         DBConnect.close();
      }
      return result;
   }
   
   // 상품 삭제
   public boolean deleteItem(int id) {
      Connection conn = null;
      PreparedStatement ps = null;
      String sql = "delete from item where id=?";
      boolean result = false;
      try {
         conn = DBConnect.connect();
         ps = conn.prepareStatement(sql);
         ps.setInt(1, id);
         int r = ps.executeUpdate();
         if(r > 0)
            result=true;
      }catch(Exception e) {
         e.printStackTrace();
      }finally {
         DBConnect.close();
      }
      return result;
   }

}

