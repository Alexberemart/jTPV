package tpv.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tpv.model.dao.ItemDAO;
import tpv.model.dao.PaymentMethodDAO;
import tpv.model.enums.DiscountType;
import tpv.model.vo.PaymentMethod;
import tpv.model.vo.item.Item;
import tpv.util.ApplicationContextProvider;

import java.util.ArrayList;
import java.util.List;

@Component("paymentMethodServices")
public class PaymentMethodServices {

    @Autowired
    PaymentMethodDAO paymentMethodDAO;

    public static PaymentMethodServices getInstance() {
        return (PaymentMethodServices) ApplicationContextProvider.getInstance().getBean("paymentMethodServices");
    }

    public List<PaymentMethod> createAllItems() {
        List<PaymentMethod> paymentMethodList = new ArrayList<>();

        PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethod.setCode("TARJETA");
        paymentMethodList.add(paymentMethod);

        paymentMethod = new PaymentMethod();
        paymentMethod.setCode("METALICO");
        paymentMethodList.add(paymentMethod);

        for (PaymentMethod _paymentMethod : paymentMethodList) {
            paymentMethodDAO.makePersistent(_paymentMethod);
        }

        return paymentMethodList;
    }

    public PaymentMethod getByPaymentMethodCode(String paymentMethodCode) {
        return paymentMethodDAO.getPaymentMethodCode(paymentMethodCode);
    }
}
