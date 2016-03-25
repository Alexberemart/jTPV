package tpv.services;

import tpv.model.dao.ItemDAO;
import tpv.model.enums.DiscountType;
import tpv.model.vo.item.Item;
import tpv.util.ApplicationContextProvider;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class ItemServices {

    @Autowired
    ItemDAO itemDAO;

    public static ItemServices getInstance() {
        return (ItemServices) ApplicationContextProvider.getInstance().getBean("itemServices");
    }

    public List<Item> createAllItems() {
        List<Item> itemList = new ArrayList<>();

        Item item = new Item();
        item.setCode("125");
        item.setDescription("item#125");
        item.setUnitPrice(1.23);
        item.setTaxRate(21.0);
        itemList.add(item);

        item = new Item();
        item.setCode("124");
        item.setDescription("item#124");
        item.setUnitPrice(4.99);
        item.setTaxRate(10.0);
        itemList.add(item);

        item = new Item();
        item.setCode("123");
        item.setDescription("item#123");
        item.setUnitPrice(10.52);
        item.setDiscountType(DiscountType.THREE_PER_TWO);
        item.setTaxRate(4.0);
        itemList.add(item);

        item = new Item();
        item.setCode("122");
        item.setDescription("item#122");
        item.setUnitPrice(0.67);
        item.setDiscountType(DiscountType.SEVENTY_OFF);
        item.setTaxRate(21.0);
        itemList.add(item);

        item = new Item();
        item.setCode("121");
        item.setDescription("item#121");
        item.setUnitPrice(2.05);
        item.setTaxRate(4.0);
        itemList.add(item);

        for (Item _item : itemList) {
            itemDAO.makePersistent(_item);
        }

        return itemList;
    }

    public Item getItemByCode(String itemCode) {
        return itemDAO.getItemByCode(itemCode);
    }
}
