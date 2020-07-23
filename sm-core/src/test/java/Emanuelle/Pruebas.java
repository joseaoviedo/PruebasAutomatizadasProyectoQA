package Emanuelle;

import com.salesmanager.core.business.utils.CacheUtils;
import com.salesmanager.core.model.reference.currency.Currency;
import org.junit.Assert;
import org.junit.Test;

import javax.inject.Inject;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class Pruebas  extends com.salesmanager.test.common.AbstractSalesManagerCoreTestCase {

    @Inject
    private CacheUtils cache;

    @Test
    public void test_cacheNotNull() throws Exception {

        List countries = countryService.list();
        cache.putInCache(countries, "COUNTRIES");
        List objects = (List) cache.getFromCache("COUNTRIES");
        System.out.println(objects);
        Assert.assertNotNull(objects);

    }

    @Test
    public void test_cacheNull() throws Exception {

        List objects = (List) cache.getFromCache("COUNTRIES");
        Assert.assertNull(objects);

    }

    @Test
    public void test_Currency(){

        Currency currency = currencyService.getByCode("BGN");

        java.util.Currency c = currency.getCurrency();

        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(Locale.US);
        numberFormat.setCurrency(c);
        System.out.println(numberFormat.getCurrency().toString());

    }

}
