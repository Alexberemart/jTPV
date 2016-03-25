package tpv.services;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tpv.model.dao.ItemDAO;
import tpv.model.vo.item.Item;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:/tpv/context.xml"
})
public class ItemServicesTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    ItemDAO itemDAO;

    Item item = new Item();

    @Before
    public void setUp() {
        item.setUnitPrice((double) 10);
        item.setDescription("my item");
        item.setCode("123");
        itemDAO.makePersistent(item);
    }

    @Test
    public void getItemByCode() {
        Item _item = ItemServices.getInstance().getItemByCode("123");
        Assert.assertNotNull(_item);
        _item = itemDAO.getItemByCode("1234");
        Assert.assertNull(_item);
    }

    @Test
    public void createAllItems() {
        List<Item> itemList = ItemServices.getInstance().createAllItems();
        Assert.assertNotNull(itemList);
    }
}
