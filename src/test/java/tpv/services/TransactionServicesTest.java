package tpv.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tpv.model.dao.ItemDAO;
import tpv.model.dao.TransactionDAO;
import tpv.model.vo.item.Item;
import tpv.model.vo.Transaction;
import tpv.model.vo.item.impl.TransactionItem;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:/tpv/context.xml"
})
public class TransactionServicesTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    ItemDAO itemDAO;

    @Autowired
    TransactionDAO transactionDAO;

    Item item = new Item();
    Transaction transaction = new Transaction();

    @Before
    public void setUp() {
        item.setUnitPrice((double) 10);
        item.setDescription("my item");
        item.setCode("123");
        itemDAO.makePersistent(item);

        TransactionItem transactionItem = new TransactionItem();
        transactionItem.setItem(item);
        transactionItem.setQuantity(1);
        transactionItem.setTransaction(transaction);

        transaction.getTransactionItemList().add(transactionItem);
        TransactionServices.getInstance().makePersistent(transaction);
    }

    @Test
    public void makePersistent(){
        TransactionServices.getInstance().makePersistent(transaction);
    }

    @Test
    public void createAllItems() {
    }
}
