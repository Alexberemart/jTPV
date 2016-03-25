package tpv.model.dao;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tpv.model.vo.item.Item;
import tpv.model.vo.Transaction;
import tpv.model.vo.item.impl.TransactionItem;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:/tpv/context.xml"
})
public class TransactionItemDAOTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    ItemDAO itemDAO;

    @Autowired
    TransactionDAO transactionDAO;

    @Autowired
    TransactionItemDAO transactionItemDAO;

    Item item = new Item();
    Transaction transaction = new Transaction();

    @Before
    public void setUp() {
        //TODO: Hacer este setup de la BBDD con dbunit
        item.setUnitPrice((double) 10);
        item.setDescription("my item");
        item.setCode("123");
        itemDAO.makePersistent(item);

        TransactionItem transactionItem = new TransactionItem();
        transactionItem.setItem(item);
        transactionItem.setTransaction(transaction);
        transactionItem.setQuantity(1);

        transaction.getTransactionItemList().add(transactionItem);
        transactionDAO.makePersistent(transaction);
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

    @Test(expected = DataIntegrityViolationException.class)
    public void makePersistentWithoutQuantity() {
        Transaction transaction = new Transaction();

        TransactionItem transactionItem = new TransactionItem();
        transactionItem.setTransaction(transaction);
        transactionItem.setItem(item);

        transaction.getTransactionItemList().add(transactionItem);
        transactionDAO.makePersistent(transaction);
    }

    @Test
    public void getTransactionItemsByItemCode() {
        List<TransactionItem> result = transactionItemDAO.getTransactionItemsByItemCode(transaction.getId(), "123");
        Assert.assertEquals(1, result.size());
    }
}
