package tpv.model.dao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tpv.model.vo.item.Item;
import tpv.model.vo.Transaction;
import tpv.model.vo.item.impl.TransactionItem;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:/tpv/context.xml"
})
public class TransactionDAOTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    ItemDAO itemDAO;

    @Autowired
    TransactionDAO transactionDAO;

    Item item = new Item();

    @Before
    public void setUp() {
        //TODO: Hacer este setup de la BBDD con dbunit
        item.setUnitPrice((double) 10);
        item.setDescription("my item");
        item.setCode("123");
        itemDAO.makePersistent(item);
    }

    @Test
    public void makePersistent() {
        Transaction transaction = new Transaction();

        TransactionItem transactionItem = new TransactionItem();
        transactionItem.setTransaction(transaction);
        transactionItem.setItem(item);
        transactionItem.setQuantity(1);

        transaction.getTransactionItemList().add(transactionItem);
        transactionDAO.makePersistent(transaction);
    }
}
