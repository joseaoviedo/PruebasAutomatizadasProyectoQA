//Jose Oviedo - 2 casos
package com.salesmanager.test.Jose;

import com.salesmanager.core.business.modules.utils.GeoLocationImpl;
import com.salesmanager.core.model.common.Address;
import com.salesmanager.test.configuration.ConfigurationTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {ConfigurationTest.class})
public class GeoLocationImplTest {
    @Autowired
    ApplicationContext context;

    @Test
    public void getAddressValidIP() throws Exception {
        GeoLocationImpl gl = context.getBean(GeoLocationImpl.class);
        String ip = "186.159.159.37";
        Address ad = gl.getAddress(ip);
        String country = ad.getCountry();
        boolean flag = country.equals("CR");
        assertTrue(flag);
    }

    @Test(expected = Exception.class)
    public void getAddressInvalidIP() throws Exception {
        GeoLocationImpl gl = context.getBean(GeoLocationImpl.class);
        String ip = "INVALID IP ADDRESS";
        Address ad = gl.getAddress(ip);

    }
}
