package tpv.rest;

import tpv.model.vo.Transaction;
import tpv.services.TransactionPaymentServices;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("transactionPayment")
@Produces(MediaType.APPLICATION_JSON)
public class TransactionPaymentService extends AbstractRestService {

    @POST
    public Response addPayment(
            @QueryParam("paymentMethod") String paymentMethod,
            @QueryParam("transactionId") String transactionId,
            @QueryParam("amount") Float amount
            ) throws Exception {

        Transaction transaction = TransactionPaymentServices.getInstance().addPayment(paymentMethod, transactionId, amount);
        return ok(transaction);

    }
}
