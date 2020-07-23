//Jose Oviedo - 2 casos
package com.salesmanager.test.shop.Jose;

import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.shop.application.ShopApplication;
import com.salesmanager.shop.utils.LocaleUtils;
import com.salesmanager.test.shop.common.ServicesTestSupport;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import com.salesmanager.core.model.reference.currency.Currency;
import java.util.Locale;

import static org.junit.Assert.assertTrue;

@SpringBootTest(classes = ShopApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class LocaleUtilsTest extends ServicesTestSupport {
    @MockBean
    MerchantStore mockMerchantStore;

    @MockBean
    Currency mockCurrency;

    @Test
    public void getLocaleValidISO(){
        Mockito.when(mockCurrency.getCode()).thenReturn("CRI");
        Mockito.when(mockMerchantStore.getCurrency()).thenReturn(mockCurrency);
        Locale l = LocaleUtils.getLocale(mockMerchantStore);
        String countryName = "Costa Rica";
        boolean flag = countryName.equals(l.getDisplayCountry());
        assertTrue(flag);
    }

    @Test
    /*
    * Esta prueba verifica que la funcion devuelva el Locale default
    * en caso de que se especifique un codigo ISO invalido, en este
    * caso el Locale invalido es de Estados Unidos
     */
    public void getLocaleInvalidISO(){
        //Corresponde a un ISO invalido
        Mockito.when(mockCurrency.getCode()).thenReturn("INVALID ISO");
        Mockito.when(mockMerchantStore.getCurrency()).thenReturn(mockCurrency);
        //La funcion va a retornal el Locale por defecto(Estados Unidos)
        Locale l = LocaleUtils.getLocale(mockMerchantStore);
        String defaultCountry = "US";
        boolean flag = defaultCountry.equals(l.getCountry());
        assertTrue(flag);
    }
}
