package Alejandro;

import com.salesmanager.core.business.utils.ProductPriceUtils;
import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.order.orderproduct.OrderProduct;
import com.salesmanager.core.model.reference.country.Country;
import com.salesmanager.core.model.reference.currency.Currency;
import com.salesmanager.core.model.reference.language.Language;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Locale;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)

public class ProductPriceUtilsTest{

    ProductPriceUtils PPU = new ProductPriceUtils();

    @MockBean
    Product product;

    @MockBean
    MerchantStore store;

    @MockBean
    OrderProduct orderProduct;

    @Autowired
    ApplicationContext context;

    @Test
    public void getOrderProductTotalPriceTest(){

        Mockito.when(orderProduct.getOneTimeCharge()).thenReturn(BigDecimal.valueOf(500.00));
        Mockito.when(orderProduct.getProductQuantity()).thenReturn(2);

        BigDecimal resul = PPU.getOrderProductTotalPrice(null,orderProduct);
        //System.out.print(resul);

        assertEquals(resul,BigDecimal.valueOf(1000.00));
    }

    @Test
    public void getAdminFormatedAmountTestG() throws Exception {

        String resul = PPU.getAdminFormatedAmount(null,BigDecimal.valueOf(1000));

        //System.out.print(resul);

        assertEquals(resul,("1,000.00"));
    }

    @Test
    public void getAdminFormatedAmountTestB() throws Exception {

        String resul = PPU.getAdminFormatedAmount(null,null);

        //System.out.print(resul);

        assertEquals(resul,(""));
    }

    @Test
    //@Ignore
    public void getStoreFormatedAmountWithCurrencyTestG() throws Exception {

        MerchantStore str = new MerchantStore();
        com.salesmanager.core.model.reference.currency.Currency c3 = new Currency();
        c3.setCurrency(java.util.Currency.getInstance(Locale.US));
        str.setCurrency(c3);

       com.salesmanager.core.model.reference.language.Language lan = new Language();
       lan.setCode("en");
       str.setDefaultLanguage(lan);

        com.salesmanager.core.model.reference.country.Country con = new Country();
        con.setIsoCode("US");
        str.setCountry(con);

        String resul = PPU.getAdminFormatedAmountWithCurrency(str,BigDecimal.valueOf(5000));

        //System.out.print(resul);

        assertEquals(resul,("5,000.00"));
    }

    @Test
    public void getStoreFormatedAmountWithCurrencyTestB() throws Exception {

        MerchantStore str = new MerchantStore();
        com.salesmanager.core.model.reference.currency.Currency c3 = new Currency();
        c3.setCurrency(java.util.Currency.getInstance(Locale.US));
        str.setCurrency(c3);

        com.salesmanager.core.model.reference.language.Language lan = new Language();
        lan.setCode("en");
        str.setDefaultLanguage(lan);

        com.salesmanager.core.model.reference.country.Country con = new Country();
        con.setIsoCode("US");
        str.setCountry(con);

        String resul = PPU.getAdminFormatedAmountWithCurrency(str,null);

        //System.out.print(resul);

        assertEquals(resul,(""));
    }
}
