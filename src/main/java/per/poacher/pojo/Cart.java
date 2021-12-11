package per.poacher.pojo;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author poacher
 * @create 2021-05-03-20:50
 */
public class Cart {

    private Map<Integer,CartItem> items = new LinkedHashMap<>();

    public int getTotalCount() {
        int totalCount = 0;
        for (CartItem value : items.values()) {
            totalCount += value.getCount();
        }
        return totalCount;
    }

    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice = new BigDecimal(0);
        for (CartItem value : items.values()) {
            totalPrice = totalPrice.add(value.getTotalPrice());
        }
        return totalPrice;
    }

    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", items=" + items +
                '}';
    }

    /**
     * 添加商品
     * @param cartItem 待添加的商品的信息
     */
    public void addItem(CartItem cartItem) {
        CartItem item = items.get(cartItem.getId());
        if (item != null) { //表示商品已经添加过了
            item.setCount(item.getCount() + cartItem.getCount());   //数量追加
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));
            return;
        }
        items.put(cartItem.getId(), cartItem);
    }

    /**
     * 删除商品
     * @param id 待删除的商品的id
     */
    public void deleteItem(int id) {
        items.remove(id);
    }

    /**
     * 清空购物车
     */
    public void clear() {
        items.clear();
    }

    /**
     * 修改商品数量
     * @param id 待修改的商品id
     * @param count 修改的数量
     */
    public void updateCount(int id, int count) {
        CartItem item = items.get(id);
        if (item != null) { //表示商品已经添加过了
            item.setCount(count);   //数量追加
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));
        }
    }
}
