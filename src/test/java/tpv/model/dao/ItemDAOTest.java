package tpv.model.dao;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tpv.model.vo.item.Item;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:/tpv/context.xml"
})
public class ItemDAOTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    ItemDAO itemDAO;

    Item item = new Item();

    @Before
    public void setUp() {
        item.setUnitPrice((double) 10);
        item.setDescription("my item");
        item.setCode("123");
    }

    @Test
    public void makePersistent() {
        itemDAO.makePersistent(item);
    }

    @Test
    public void getItemByCode() {
        makePersistent();
        Item _item = itemDAO.getItemByCode("123");
        Assert.assertNotNull(_item);
        _item = itemDAO.getItemByCode("1234");
        Assert.assertNull(_item);
    }
}
