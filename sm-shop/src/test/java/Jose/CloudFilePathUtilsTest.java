//Jose Oviedo - 4 casos
package com.salesmanager.test.shop.Jose;

import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.shop.application.ShopApplication;
import com.salesmanager.shop.utils.CloudFilePathUtils;
import com.salesmanager.test.shop.common.ServicesTestSupport;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

@SpringBootTest(classes = ShopApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class CloudFilePathUtilsTest extends ServicesTestSupport {
    CloudFilePathUtils CFPU = new CloudFilePathUtils();

    @MockBean
    MerchantStore mockMerchantStore;


    @Test
    public void buildStaticImageUtilsTest(){
        Mockito.when(mockMerchantStore.getCode()).thenReturn("TESTCASE");
        String imgNameTest = "TESTIMAGE";
        String res = CFPU.buildStaticImageUtils(mockMerchantStore, imgNameTest);
        String expected = "/static/files/TESTCASE/TESTIMAGE";
        boolean flag = expected.equals(res);
        assertTrue(flag);
    }

    @Test
    public void buildStaticImageUtilsWithTypeValid(){
        Mockito.when(mockMerchantStore.getCode()).thenReturn("TESTCASE");
        String imgNameTest = "TESTIMAGE";
        String imgType = "jpg";
        String res = CFPU.buildStaticImageUtils(mockMerchantStore,imgType, imgNameTest);
        String expected = "/static/files/TESTCASE/jpg/TESTIMAGE";
        boolean flag = expected.equals(res);
        assertTrue(flag);
    }

    @Test
    public void buildStaticImageUtilsWithTypeNullType(){
        Mockito.when(mockMerchantStore.getCode()).thenReturn("TESTCASE");
        String imgNameTest = "TESTIMAGE";
        String imgType = null;
        String res = CFPU.buildStaticImageUtils(mockMerchantStore,imgType, imgNameTest);
        String expected = "/static/files/TESTCASE/TESTIMAGE";
        boolean flag = expected.equals(res);
        assertTrue(flag);
    }

    @Test
    public void buildStaticImageUtilsWithTypeNullTypeNullImage(){
        Mockito.when(mockMerchantStore.getCode()).thenReturn("TESTCASE");
        String imgNameTest = null;
        String imgType = null;
        String res = CFPU.buildStaticImageUtils(mockMerchantStore,imgType, imgNameTest);
        String expected = "/static/files/TESTCASE/";
        boolean flag = expected.equals(res);
        assertTrue(flag);
    }
}
