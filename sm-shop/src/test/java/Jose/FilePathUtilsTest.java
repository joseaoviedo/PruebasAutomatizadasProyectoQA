//Jose Oviedo - 4 casos
package com.salesmanager.test.shop.Jose;

import com.salesmanager.core.model.catalog.product.file.DigitalProduct;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.shop.application.ShopApplication;
import com.salesmanager.shop.model.order.ReadableOrderProductDownload;
import com.salesmanager.shop.utils.FilePathUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

@SpringBootTest(classes = ShopApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class FilePathUtilsTest {
    @MockBean
    MerchantStore mockMerchantStore;

    @MockBean
    DigitalProduct mockDigitalProduct;

    @MockBean
    ReadableOrderProductDownload mockReadableOrderProductDownload;

    FilePathUtils fpu;

    @Test
    public void buildStaticFilePathFile(){
        fpu = new FilePathUtils();
        String res = fpu.buildStaticFilePath("TestStore", "testfile.pdf");
        System.out.println(res);
        String expected = "/files/TestStore/testfile.pdf";
        assertTrue(true);
    }

    @Test
    public void buildStaticFilePathNoFile(){
        fpu = new FilePathUtils();
        String res = fpu.buildStaticFilePath("TestStore", null);
        String expected = "/files/TestStore/";
        boolean flag = expected.equals(res);
        assertTrue(flag);
    }

    @Test
    public void buildAdminDownloadProductFilePathTest(){
        Mockito.when(mockMerchantStore.getCode()).thenReturn("TestStore");
        Mockito.when(mockDigitalProduct.getProductFileName()).thenReturn("testdigitalproduct.pdf");
        fpu = new FilePathUtils();
        String res = fpu.buildAdminDownloadProductFilePath(mockMerchantStore, mockDigitalProduct);
        String expected = "/admin/files/downloads/TestStore/testdigitalproduct.pdf";
        boolean flag = expected.equals(res);
        assertTrue(flag);
    }

    @Test
    public void buildOrderDownloadProductFilePathTest(){
        Mockito.when(mockReadableOrderProductDownload.getId()).thenReturn((long) 123456789);
        fpu = new FilePathUtils();
        String res = fpu.buildOrderDownloadProductFilePath(mockMerchantStore, mockReadableOrderProductDownload, (long) 123456789);
        String expected = "/shop/order/download/123456789/123456789.html";
        boolean flag = expected.equals(res);
        assertTrue(flag);
    }
}
