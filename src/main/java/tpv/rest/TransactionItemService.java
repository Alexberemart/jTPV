package tpv.rest;

import tpv.model.vo.PaymentMethod;
import tpv.model.vo.item.Item;
import tpv.model.vo.Transaction;
import tpv.model.vo.item.impl.TransactionItem;
import tpv.services.ItemServices;
import tpv.services.PaymentMethodServices;
import tpv.services.TransactionITemServices;
import tpv.services.TransactionServices;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;

@Path("transactionItem")
@Produces(MediaType.APPLICATION_JSON)
public class TransactionItemService extends AbstractRestService {

    @POST
    public Response addItem(
            @QueryParam("itemCode") String code,
            @QueryParam("transactionId") String transactionId
            ) throws Exception {

        HashMap<String, Object> result = new HashMap<>();

        Item item = ItemServices.getInstance().getItemByCode(code);
        if (item == null) {
            //TODO: show error
            result.put("status", -1);
            result.put("result", "Item with code " + code + " was not found");
            return ok(result);
        }

        Transaction transaction = new Transaction();

        List<TransactionItem> transactionItemList = TransactionITemServices.getInstance().getTransactionItemsByItemCode(transactionId, code);
        if (transactionItemList == null) {

            transaction = TransactionServices.getInstance().getById(transactionId);
            TransactionItem transactionItem = new TransactionItem();
            transactionItem.setItem(item);
            transactionItem.setQuantity(1);
            transactionItem.setTransaction(transaction);

            transaction.getTransactionItemList().add(transactionItem);

        }else{

            TransactionItem transactionItem = transactionItemList.get(0);
            transactionItem.setQuantity(transactionItem.getQuantity() + 1);
            TransactionITemServices.getInstance().makePersistent(transactionItem);
            transaction = TransactionServices.getInstance().getById(transactionId);
        }

        transaction = TransactionServices.getInstance().makePersistent(transaction);

        result.put("status", 0);
        result.put("result", transaction);
        return ok(result);

    }

    @DELETE
    public Response deleteItem(
            @QueryParam("itemCode") String itemCode,
            @QueryParam("transactionId") String transactionId
    ) throws Exception {

        List<TransactionItem> transactionItemList = TransactionITemServices.getInstance().getTransactionItemsByItemCode(transactionId, itemCode);

        for (TransactionItem transactionItem : transactionItemList){
            if (transactionItem.getQuantity() == 1) {
                TransactionITemServices.getInstance().makeTransient(transactionItem);
            }else{
                transactionItem.setQuantity(transactionItem.getQuantity() - 1);
                TransactionITemServices.getInstance().makePersistent(transactionItem);
            }
        }

        Transaction transaction = TransactionServices.getInstance().getById(transactionId);
        return ok(transaction);
    }

    @POST
    @Path("init")
    public Response createAllItems() throws Exception {

        List<Item> itemList = ItemServices.getInstance().createAllItems();
        List<PaymentMethod> paymentMethodList = PaymentMethodServices.getInstance().createAllItems();
        return ok(itemList);

    }

    @PUT
    @Path("addQuantity")
    public Response addQuantity(
            @QueryParam("transactionItemID") String transactionItemID
    ) throws Exception {

        TransactionItem transactionItem = TransactionITemServices.getInstance().getById(transactionItemID);
        transactionItem.setQuantity(transactionItem.getQuantity() + 1);
        TransactionITemServices.getInstance().makePersistent(transactionItem);
        Transaction transaction = TransactionServices.getInstance().getById(transactionItem.getTransaction().getId());
        return ok(transaction);

    }

    @PUT
    @Path("lessQuantity")
    public Response lessQuantity(
            @QueryParam("transactionItemID") String transactionItemID
    ) throws Exception {

        HashMap<String, Object> result = new HashMap<>();

        TransactionItem transactionItem = TransactionITemServices.getInstance().getById(transactionItemID);
        if (transactionItem.getQuantity() == 1){
            result.put("status", -1);
            result.put("result", "quantity must be greater than zero");
            return ok(result);
        }
        transactionItem.setQuantity(transactionItem.getQuantity() - 1);
        TransactionITemServices.getInstance().makePersistent(transactionItem);
        Transaction transaction = TransactionServices.getInstance().getById(transactionItem.getTransaction().getId());

        result.put("status", 0);
        result.put("result", transaction);
        return ok(result);

    }
}
