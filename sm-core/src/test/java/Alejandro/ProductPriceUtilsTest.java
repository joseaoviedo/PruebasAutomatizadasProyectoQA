package Alejandro;

import com.salesmanager.core.business.utils.ProductPriceUtils;
import com.salesmanager.core.model.catalog.product.Product;
import com.salesmanager.core.model.catalog.product.price.FinalPrice;
import com.salesmanager.core.model.catalog.product.price.ProductPrice;
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
import java.util.Date;
import java.util.Locale;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
//21 pruebas de la clase ProductPriceUtils
public class ProductPriceUtilsTest{

    ProductPriceUtils PPU = new ProductPriceUtils();

    @MockBean
    Product product;

    @MockBean
    OrderProduct orderProduct;


    @Autowired
    ApplicationContext context;

    @Test
    public void getOrderProductTotalPriceTest(){

        Mockito.when(orderProduct.getOneTimeCharge()).thenReturn(BigDecimal.valueOf(500.00));
        Mockito.when(orderProduct.getProductQuantity()).thenReturn(2);

        BigDecimal resul = PPU.getOrderProductTotalPrice(null,orderProduct);
        System.out.print(resul);

        assertEquals(resul,BigDecimal.valueOf(1000.00));
    }

    @Test
    public void getAdminFormatedAmountTestG() throws Exception {

        String resul = PPU.getAdminFormatedAmount(null,BigDecimal.valueOf(1000));

        assertEquals(resul,("1,000.00"));
    }

    @Test
    public void getAdminFormatedAmountTestB() throws Exception {

        String resul = PPU.getAdminFormatedAmount(null,null);


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

        assertEquals(resul,(""));
    }

    @Test(expected = Exception.class)
    //@Ignore
    public void getStoreFormatedAmountWithCurrencyTestE() throws Exception {

        //Exception LOGGER.error("Cannot create currency or locale instance for store " + store.getCode());
        MerchantStore str = new MerchantStore();
        String resul = PPU.getAdminFormatedAmountWithCurrency(str,BigDecimal.valueOf(5000));
    }

    @Test
    public void getAmountTestG() throws Exception {
        BigDecimal resul = PPU.getAmount("2500");

        BigDecimal expect = new BigDecimal("2500.00");

        assertEquals(resul,expect);
    }

    @Test(expected = Exception.class)
    public void getAmountTestB() throws Exception {
        //Numero Negativo Exception
        PPU.getAmount("-2500");
    }

    @Test
    public void matchPositiveIntegerTestG(){
        boolean resul = PPU.matchPositiveInteger("55");

        assertTrue(resul);
    }

    @Test
    public void matchPositiveIntegerTestB(){
        boolean resul = PPU.matchPositiveInteger("-55");

        assertFalse(resul);
    }

    @Test
    public void getFormatedAmountWithCurrencyTestG() throws Exception {

        com.salesmanager.core.model.reference.currency.Currency c3 = new Currency();
        c3.setCurrency(java.util.Currency.getInstance(Locale.US));

        Locale locale = new Locale("en");

        BigDecimal BigD = new BigDecimal("150");

        String resul = PPU.getFormatedAmountWithCurrency(locale,c3,BigD);

        assertEquals(resul,"USD150.00");
    }

    @Test
    public void getFormatedAmountWithCurrencyTestB() throws Exception {

        com.salesmanager.core.model.reference.currency.Currency c3 = new Currency();
        c3.setCurrency(java.util.Currency.getInstance(Locale.US));

        Locale locale = new Locale("en");

        String resul = PPU.getFormatedAmountWithCurrency(locale,c3,null);

        assertEquals(resul,"");
    }

    @Test
    public void getAdminFormatedAmountWithCurrencyG() throws Exception {
        MerchantStore str = new MerchantStore();
        com.salesmanager.core.model.reference.currency.Currency c3 = new Currency();
        c3.setCurrency(java.util.Currency.getInstance(Locale.US));
        str.setCurrency(c3);

        BigDecimal BigD = new BigDecimal("35");

        String resul = PPU.getAdminFormatedAmountWithCurrency(str,BigD);

        assertEquals(resul,"35.00");
    }

    @Test
    public void getAdminFormatedAmountWithCurrencyB() throws Exception {
        MerchantStore str = new MerchantStore();
        com.salesmanager.core.model.reference.currency.Currency c3 = new Currency();
        c3.setCurrency(java.util.Currency.getInstance(Locale.US));
        str.setCurrency(c3);

        String resul = PPU.getAdminFormatedAmountWithCurrency(str,null);

        assertEquals(resul,"");
    }

    @Test
    public void hasDiscountTestG(){
        ProductPrice PP = new ProductPrice();
        Date SD = new Date(120,06,01);
        Date ED = new Date(120,07,30);

        PP.setProductPriceSpecialStartDate(SD);
        PP.setProductPriceSpecialEndDate(ED);
        boolean resul = PPU.hasDiscount(PP);

        assertTrue(resul);
    }

    @Test
    public void hasDiscountTestB(){
        ProductPrice PP = new ProductPrice();
        //year = year -1900
        Date SD = new Date(121,06,01);
        Date ED = new Date(121,07,30);

        PP.setProductPriceSpecialStartDate(SD);
        PP.setProductPriceSpecialEndDate(ED);
        boolean resul = PPU.hasDiscount(PP);

        assertFalse(resul);
    }

    @Test
    public void discountPriceTest(){

        FinalPrice FP = new FinalPrice();
        ProductPrice PP = new ProductPrice();
        BigDecimal BigD = new BigDecimal("15");
        BigDecimal BigD2 = new BigDecimal("30");
        PP.setProductPriceSpecialAmount(BigD);
        PP.setProductPriceAmount(BigD2);
        FP.setProductPrice(PP);
        BigDecimal BigD3 = new BigDecimal("25");
        FP.setOriginalPrice(BigD3);

        PPU.discountPrice(FP);

        assertEquals(FP.getDiscountPercent(),50);
    }

    @Test
    public void getFormatedAmountWithCurrencyTest2G() throws Exception {
        MerchantStore str = new MerchantStore();
        com.salesmanager.core.model.reference.currency.Currency c3 = new Currency();
        c3.setCurrency(java.util.Currency.getInstance(Locale.US));
        str.setCurrency(c3);
        BigDecimal BigD = new BigDecimal("17");
        Locale locale = new Locale("en");

        String result = PPU.getFormatedAmountWithCurrency(str,BigD,locale);

        //System.out.print("\n");
        //System.out.print(result);
        //System.out.print("\n");

        assertEquals(result,"17.00");
    }

    @Test(expected = Exception.class)
    public void getFormatedAmountWithCurrencyTest2E() throws Exception {
        MerchantStore str = new MerchantStore();
        com.salesmanager.core.model.reference.currency.Currency c3 = new Currency();
        c3.setCurrency(java.util.Currency.getInstance(Locale.US));
        str.setCurrency(c3);
        Locale locale = new Locale("en");

        String result = PPU.getFormatedAmountWithCurrency(str,null,locale);
    }

    @Test
    public void getFormatedAmountWithCurrencyTest3G() throws Exception {

        com.salesmanager.core.model.reference.currency.Currency cur = new Currency();
        cur.setCurrency(java.util.Currency.getInstance(Locale.UK));
        BigDecimal BigD = new BigDecimal("43.99");

        String result = PPU.getFormatedAmountWithCurrency(cur,BigD);

        assertEquals(result,"43.99");
    }

    @Test
    public void getFormatedAmountWithCurrencyTest3B() throws Exception {

        com.salesmanager.core.model.reference.currency.Currency cur = new Currency();
        cur.setCurrency(java.util.Currency.getInstance(Locale.UK));

        String result = PPU.getFormatedAmountWithCurrency(cur,null);

        assertEquals(result,"");
    }
}
