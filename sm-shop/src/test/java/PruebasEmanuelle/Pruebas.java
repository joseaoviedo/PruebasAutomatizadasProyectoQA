package PruebasEmanuelle;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.salesmanager.core.business.constants.Constants;
import com.salesmanager.core.model.customer.CustomerGender;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.shop.application.ShopApplication;
import com.salesmanager.shop.model.catalog.SearchProductList;
import com.salesmanager.shop.model.catalog.SearchProductRequest;
import com.salesmanager.shop.model.catalog.category.*;
import com.salesmanager.shop.model.catalog.manufacturer.PersistableManufacturer;
import com.salesmanager.shop.model.catalog.manufacturer.ReadableManufacturer;
import com.salesmanager.shop.model.catalog.product.*;
import com.salesmanager.shop.model.customer.PersistableCustomer;
import com.salesmanager.shop.model.customer.address.Address;
import com.salesmanager.shop.model.customer.optin.PersistableCustomerOptin;
import com.salesmanager.shop.model.references.PersistableAddress;
import com.salesmanager.shop.model.security.PersistableGroup;
import com.salesmanager.shop.model.shoppingcart.PersistableShoppingCartItem;
import com.salesmanager.shop.model.shoppingcart.ReadableShoppingCart;
import com.salesmanager.shop.model.shoppingcart.ReadableShoppingCartItem;
import com.salesmanager.shop.model.store.PersistableMerchantStore;
import com.salesmanager.shop.model.store.ReadableMerchantStore;
import com.salesmanager.shop.model.user.PersistableUser;
import com.salesmanager.shop.model.user.ReadableUser;
import com.salesmanager.shop.model.user.UserPassword;
import com.salesmanager.shop.store.security.AuthenticationRequest;
import com.salesmanager.shop.store.security.AuthenticationResponse;
import com.salesmanager.test.shop.common.ServicesTestSupport;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpServerErrorException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.springframework.http.HttpStatus.*;


public class Pruebas  extends ServicesTestSupport {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void postAddCartTest() throws InterruptedException {

        ReadableProduct product = super.readyToWorkProduct("addToCart");

        PersistableShoppingCartItem cartItem = new PersistableShoppingCartItem();
        cartItem.setProduct(product.getId());
        cartItem.setQuantity(1);

        HttpEntity<PersistableShoppingCartItem> cartEntity = new HttpEntity<>(cartItem, getHeader());
        ResponseEntity<ReadableShoppingCart> response = testRestTemplate.postForEntity(String.format("/api/v1/cart/"), cartEntity, ReadableShoppingCart.class);

        assertNotNull(response);
        assertThat(response.getStatusCode(), is(CREATED));

    }

    @Test
    public void postNullCartTest(){

        PersistableShoppingCartItem cartItem = null;

        HttpEntity<PersistableShoppingCartItem> cartEntity = new HttpEntity<>(cartItem, getHeader());
        ResponseEntity<ReadableShoppingCart> response = testRestTemplate.postForEntity(String.format("/api/v1/cart/"), cartEntity, ReadableShoppingCart.class);

        assertNotNull(response);
        assertThat(response.getStatusCode(), is(INTERNAL_SERVER_ERROR));
    }

    @Test
    public void postEmptyCartTest(){

        PersistableShoppingCartItem cartItem = new PersistableShoppingCartItem();

        HttpEntity<PersistableShoppingCartItem> cartEntity = new HttpEntity<>(cartItem, getHeader());
        ResponseEntity<ReadableShoppingCart> response = testRestTemplate.postForEntity(String.format("/api/v1/cart/"), cartEntity, ReadableShoppingCart.class);

        assertThat(response.getStatusCode(), is(SERVICE_UNAVAILABLE));
    }

    @Test
    public void getCategoryTest() throws Exception {
        HttpEntity<String> httpEntity = new HttpEntity<>(getHeader());

        ResponseEntity<ReadableCategoryList> response = testRestTemplate.exchange(String.format("/api/v1/category/"), HttpMethod.GET,
                httpEntity, ReadableCategoryList.class);
        if (response.getStatusCode() != HttpStatus.OK) {
            throw new Exception(response.toString());
        } else {
            final List<ReadableCategory> categories = response.getBody().getCategories();
            assertNotNull(categories);
        }
    }

    @Test
    public void postCategory() throws Exception {

        PersistableCategory newCategory = new PersistableCategory();
        newCategory.setCode("javascript");
        newCategory.setSortOrder(1);
        newCategory.setVisible(true);
        newCategory.setDepth(4);

        Category parent = new Category();

        newCategory.setParent(parent);

        CategoryDescription description = new CategoryDescription();
        description.setLanguage("en");
        description.setName("Javascript");
        description.setFriendlyUrl("javascript");
        description.setTitle("Javascript");

        List<CategoryDescription> descriptions = new ArrayList<>();
        descriptions.add(description);

        newCategory.setDescriptions(descriptions);

        final ObjectWriter writer = new ObjectMapper().writer().withDefaultPrettyPrinter();
        final String json = writer.writeValueAsString(newCategory);

        HttpEntity<String> entity = new HttpEntity<>(json, getHeader());

        ResponseEntity response = testRestTemplate.postForEntity("/api/v1/private/category", entity, PersistableCategory.class);
        PersistableCategory cat = (PersistableCategory) response.getBody();
        assertThat(response.getStatusCode(), is(CREATED));
        assertNotNull(cat.getId());

    }

    @Test
    public void postCategory1() throws Exception {

        final ObjectWriter writer = new ObjectMapper().writer().withDefaultPrettyPrinter();
        final String json = writer.writeValueAsString(null);

        HttpEntity<String> entity = new HttpEntity<>(json, getHeader());

        ResponseEntity response = testRestTemplate.postForEntity("/api/v1/private/category", entity, PersistableCategory.class);
        assertThat(response.getStatusCode(), is(INTERNAL_SERVER_ERROR));

    }

    @Test
    public void deleteCategory() throws Exception {

        final HttpEntity<String> httpEntity = new HttpEntity<>(getHeader());

        testRestTemplate.exchange("/services/DEFAULT/category/100", HttpMethod.DELETE, httpEntity, Category.class);
    }

    @Test
    public void registerCustomer() {

        PersistableCustomer testCustomer = new PersistableCustomer();

        testCustomer.setEmailAddress("customer_test@test.com");
        testCustomer.setPassword("customer_test");
        testCustomer.setGender(CustomerGender.M.name());
        testCustomer.setLanguage("en");

        Address billing = new Address();

        billing.setFirstName("customer_test");
        billing.setLastName("customer_test");
        billing.setCountry("BE");
        testCustomer.setBilling(billing);
        testCustomer.setStoreCode(Constants.DEFAULT_STORE);
        HttpEntity<PersistableCustomer> entity = new HttpEntity<>(testCustomer, getHeader());

        ResponseEntity<PersistableCustomer> response = testRestTemplate.postForEntity("/api/v1/customer/register", entity, PersistableCustomer.class);
        assertThat(response.getStatusCode(), is(OK));

    }

    @Test
    public void registerNullCustomer() {

        PersistableCustomer testCustomer = null;
        HttpEntity<PersistableCustomer> entity = new HttpEntity<>(testCustomer, getHeader());

        ResponseEntity<PersistableCustomer> response = testRestTemplate.postForEntity("/api/v1/customer/register", entity, PersistableCustomer.class);
        assertThat(response.getStatusCode(), is(INTERNAL_SERVER_ERROR));

    }

    @Test
    public void registerWithoutAddressCustomer() {

        PersistableCustomer testCustomer = new PersistableCustomer();

        testCustomer.setEmailAddress("customer1@test.com");
        testCustomer.setPassword("clear123");
        testCustomer.setGender(CustomerGender.M.name());
        testCustomer.setLanguage("en");

        testCustomer.setStoreCode(Constants.DEFAULT_STORE);
        HttpEntity<PersistableCustomer> entity = new HttpEntity<>(testCustomer, getHeader());

        ResponseEntity<PersistableCustomer> response = testRestTemplate.postForEntity("/api/v1/customer/register", entity, PersistableCustomer.class);
        assertThat(response.getStatusCode(), is(INTERNAL_SERVER_ERROR));

    }

    @Test
    public void registerWithNullAddressCustomer() {

        PersistableCustomer testCustomer = new PersistableCustomer();

        testCustomer.setEmailAddress("customer1@test.com");
        testCustomer.setPassword("clear123");
        testCustomer.setGender(CustomerGender.M.name());
        testCustomer.setLanguage("en");

        Address billing = new Address();
        testCustomer.setStoreCode(Constants.DEFAULT_STORE);
        HttpEntity<PersistableCustomer> entity = new HttpEntity<>(testCustomer, getHeader());

        ResponseEntity<PersistableCustomer> response = testRestTemplate.postForEntity("/api/v1/customer/register", entity, PersistableCustomer.class);
        assertThat(response.getStatusCode(), is(INTERNAL_SERVER_ERROR));

    }

    @Test
    public void loginCustomer() {


        PersistableCustomer testCustomer = new PersistableCustomer();

        testCustomer.setEmailAddress("customer1@test.com");
        testCustomer.setPassword("clear123");
        testCustomer.setGender(CustomerGender.M.name());
        testCustomer.setLanguage("en");

        Address billing = new Address();

        billing.setFirstName("customer1");
        billing.setLastName("ccstomer1");
        billing.setCountry("BE");
        testCustomer.setBilling(billing);
        testCustomer.setStoreCode(Constants.DEFAULT_STORE);

        HttpEntity<PersistableCustomer> entity = new HttpEntity<>(testCustomer, getHeader());

        ResponseEntity<PersistableCustomer> response = testRestTemplate.postForEntity("/api/v1/customer/register", entity, PersistableCustomer.class);
        assertThat(response.getStatusCode(), is(OK));

        // created customer can login

        ResponseEntity<AuthenticationResponse> loginResponse = testRestTemplate.postForEntity("/api/v1/customer/login", new HttpEntity<>(new AuthenticationRequest("customer1@test.com", "clear123")),
                AuthenticationResponse.class);
        assertThat(loginResponse.getStatusCode(), is(OK));
        assertNotNull(loginResponse.getBody().getToken());

    }

    @Test
    public void loginNotRegisterCustomer() throws Exception{

        try {

            ResponseEntity<AuthenticationResponse> loginResponse = testRestTemplate.postForEntity("/api/v1/customer/login", new HttpEntity<>(new AuthenticationRequest("customer1@test.com", "clear123")),
                    AuthenticationResponse.class);
            assertThat(loginResponse.getStatusCode(), is(NOT_FOUND));
            assertNotNull(loginResponse.getBody().getToken());
        }catch (NullPointerException e){
            assertTrue(true);
        }
    }

    @Test
    public void createProductTest() throws Exception {

        PersistableProduct product = new PersistableProduct();
        ArrayList<Category> categories = new ArrayList<>();
        product.setCategories(categories);
        ProductSpecification specifications = new ProductSpecification();
        specifications.setManufacturer(com.salesmanager.core.model.catalog.product.manufacturer.Manufacturer.DEFAULT_MANUFACTURER);
        product.setProductSpecifications(specifications);
        product.setPrice(BigDecimal.TEN);
        product.setSku("123");
        HttpEntity<PersistableProduct> entity = new HttpEntity<>(product, getHeader());

        ResponseEntity<PersistableProduct> response = testRestTemplate.postForEntity("/api/v1/private/product?store=" + Constants.DEFAULT_STORE, entity, PersistableProduct.class);
        assertThat(response.getStatusCode(), is(CREATED));
    }

    @Test
    public void createNullProductTest(){

        PersistableProduct product = null;
        HttpEntity<PersistableProduct> entity = new HttpEntity<>(product, getHeader());

        ResponseEntity<PersistableProduct> response = testRestTemplate.postForEntity("/api/v1/private/product?store=" + Constants.DEFAULT_STORE, entity, PersistableProduct.class);
        assertThat(response.getStatusCode(), is(INTERNAL_SERVER_ERROR));

    }

    @Test
    public void createNonValueProductTest(){

        PersistableProduct product = new PersistableProduct();
        HttpEntity<PersistableProduct> entity = new HttpEntity<>(product, getHeader());

        ResponseEntity<PersistableProduct> response = testRestTemplate.postForEntity("/api/v1/private/product?store=" + Constants.DEFAULT_STORE, entity, PersistableProduct.class);
        assertThat(response.getStatusCode(), is(BAD_REQUEST));

    }

    @Test
    public void searchItem(){

        PersistableProduct product = super.product("TESTPRODUCT");

        HttpEntity<PersistableProduct> entity = new HttpEntity<>(product, getHeader());

        ResponseEntity<PersistableProduct> response = testRestTemplate.postForEntity("/api/v1/private/product?store=" + Constants.DEFAULT_STORE, entity, PersistableProduct.class);
        assertThat(response.getStatusCode(), is(CREATED));

        SearchProductRequest searchRequest = new SearchProductRequest();
        searchRequest.setQuery("TEST");
        HttpEntity<SearchProductRequest> searchEntity = new HttpEntity<>(searchRequest, getHeader());


        ResponseEntity<SearchProductList> searchResponse = testRestTemplate.postForEntity("/api/v1/search?store=" + Constants.DEFAULT_STORE, searchEntity, SearchProductList.class);
        assertThat(searchResponse.getStatusCode(), is(OK));

    }

    @Test
    public void searchItem1(){

        SearchProductRequest searchRequest = new SearchProductRequest();
        searchRequest.setQuery("query_test");
        HttpEntity<SearchProductRequest> searchEntity = new HttpEntity<>(searchRequest, getHeader());

        ResponseEntity<SearchProductList> searchResponse = testRestTemplate.postForEntity("/api/v1/search?store=" + Constants.DEFAULT_STORE, searchEntity, SearchProductList.class);
        assertThat(searchResponse.getStatusCode(), is(NOT_FOUND));

    }

    @Test
    public void testCreateStore() throws Exception {

        PersistableAddress address = new PersistableAddress();
        address.setAddress("121212 simple address");
        address.setPostalCode("12345");
        address.setCountry("US");
        address.setCity("FT LD");
        address.setStateProvince("FL");

        PersistableMerchantStore createdStore = new PersistableMerchantStore();
        createdStore.setCode("test");
        createdStore.setCurrency("CAD");
        createdStore.setDefaultLanguage("en");
        createdStore.setEmail("test@test.com");
        createdStore.setName("test");
        createdStore.setPhone("444-555-6666");
        createdStore.setSupportedLanguages(Arrays.asList("en"));
        createdStore.setAddress(address);

        final HttpEntity<PersistableMerchantStore> httpEntity = new HttpEntity<PersistableMerchantStore>(createdStore, getHeader());

        ResponseEntity<ReadableMerchantStore> response = testRestTemplate.exchange(String.format("/api/v1/private/store/"), HttpMethod.POST, httpEntity, ReadableMerchantStore.class);

        if (response.getStatusCode() != HttpStatus.OK) {
            throw new Exception(response.toString());
        } else {
            final ReadableMerchantStore store = response.getBody();
            assertNotNull(store);
        }
    }

    @Test
    public void testCreateStoreWithoutAdress() throws Exception {

        PersistableMerchantStore createdStore = new PersistableMerchantStore();
        createdStore.setCode("test");
        createdStore.setCurrency("CAD");
        createdStore.setDefaultLanguage("en");
        createdStore.setEmail("test@test.com");
        createdStore.setName("test");
        createdStore.setPhone("444-555-6666");
        createdStore.setSupportedLanguages(Arrays.asList("en"));

        final HttpEntity<PersistableMerchantStore> httpEntity = new HttpEntity<PersistableMerchantStore>(createdStore, getHeader());

        ResponseEntity<ReadableMerchantStore> response = testRestTemplate.exchange(String.format("/api/v1/private/store/"), HttpMethod.POST, httpEntity, ReadableMerchantStore.class);

        if (response.getStatusCode() != HttpStatus.OK) {
            assertTrue(true);
        } else {
            final ReadableMerchantStore store = response.getBody();
            assertNotNull(store);
        }
    }

    @Test
    public void testCreateStoreWithNullAdress() throws Exception {

        PersistableAddress address = new PersistableAddress();

        PersistableMerchantStore createdStore = new PersistableMerchantStore();
        createdStore.setCode("test");
        createdStore.setCurrency("CAD");
        createdStore.setDefaultLanguage("en");
        createdStore.setEmail("test@test.com");
        createdStore.setName("test");
        createdStore.setPhone("444-555-6666");
        createdStore.setSupportedLanguages(Arrays.asList("en"));
        createdStore.setAddress(address);


        final HttpEntity<PersistableMerchantStore> httpEntity = new HttpEntity<PersistableMerchantStore>(createdStore, getHeader());

        ResponseEntity<ReadableMerchantStore> response = testRestTemplate.exchange(String.format("/api/v1/private/store/"), HttpMethod.POST, httpEntity, ReadableMerchantStore.class);

        if (response.getStatusCode() != HttpStatus.OK) {
            assertTrue(true);
        } else {
            final ReadableMerchantStore store = response.getBody();
            assertNotNull(store);
        }
    }

    @Test
    public void testCreateStoreAndDelete() throws Exception {


        PersistableAddress address = new PersistableAddress();
        address.setAddress("121212 simple address");
        address.setPostalCode("54321");
        address.setCountry("US");
        address.setCity("FT LD");
        address.setStateProvince("FL");

        PersistableMerchantStore createdStore = new PersistableMerchantStore();
        createdStore.setCode("test_store_n_delete");
        createdStore.setCurrency("CAD");
        createdStore.setDefaultLanguage("en");
        createdStore.setEmail("test@test.com");
        createdStore.setName("test_store_n_delete");
        createdStore.setPhone("444-555-6666");
        createdStore.setSupportedLanguages(Arrays.asList("en"));
        createdStore.setAddress(address);

        final HttpEntity<PersistableMerchantStore> httpEntity = new HttpEntity<PersistableMerchantStore>(createdStore, getHeader());

        ResponseEntity<ReadableMerchantStore> response = testRestTemplate.exchange(String.format("/api/v1/private/store/"), HttpMethod.POST, httpEntity, ReadableMerchantStore.class);

        if (response.getStatusCode() != HttpStatus.OK) {
            throw new Exception(response.toString());
        } else {
            final ReadableMerchantStore store = response.getBody();
            assertNotNull(store);
        }

        //delete store
        ResponseEntity<Void> deleteResponse = testRestTemplate.exchange(String.format("/api/v1/private/store/" + "test_store_n_delete"), HttpMethod.DELETE, httpEntity, Void.class);

        assertThat(deleteResponse.getStatusCode(), Matchers.is(HttpStatus.OK));

    }

    @Test
    public void deleteEmptyStore(){

        PersistableMerchantStore createdStore = new PersistableMerchantStore();

        final HttpEntity<PersistableMerchantStore> httpEntity = new HttpEntity<PersistableMerchantStore>(createdStore, getHeader());

        //delete store
        ResponseEntity<Void> deleteResponse = testRestTemplate.exchange(String.format("/api/v1/private/store/" + "delete_empty_store"), HttpMethod.DELETE, httpEntity, Void.class);

        MatcherAssert.assertThat(deleteResponse.getStatusCode(), Matchers.is(HttpStatus.NOT_FOUND));
    }

    @Test
    public void deleteNullStore(){

        PersistableMerchantStore createdStore = null;

        final HttpEntity<PersistableMerchantStore> httpEntity = new HttpEntity<PersistableMerchantStore>(createdStore, getHeader());

        //delete store
        ResponseEntity<Void> deleteResponse = testRestTemplate.exchange(String.format("/api/v1/private/store/" + "delete_null_store"), HttpMethod.DELETE, httpEntity, Void.class);

        MatcherAssert.assertThat(deleteResponse.getStatusCode(), Matchers.is(HttpStatus.NOT_FOUND));
    }

    public void createCustomerNewsTest() throws Exception {

        PersistableCustomerOptin customerOption = new PersistableCustomerOptin();
        customerOption.setEmail("test@test.com");
        customerOption.setFirstName("Jack");
        customerOption.setLastName("John");

        ObjectWriter writer = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = writer.writeValueAsString(customerOption);

        HttpEntity<String> e = new HttpEntity<>(json);
        ResponseEntity<?> resp = testRestTemplate.postForEntity("/api/v1/newsletter", e, PersistableCustomerOptin.class);

        if (resp.getStatusCode() != HttpStatus.OK) {
            throw new Exception(resp.toString());
        } else {
            assertTrue(true);
        }
    }

    public void createEmptyCustomerNewsTest() throws Exception {

        PersistableCustomerOptin customerOption = new PersistableCustomerOptin();

        ObjectWriter writer = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = writer.writeValueAsString(customerOption);

        HttpEntity<String> e = new HttpEntity<>(json);
        ResponseEntity<?> resp = testRestTemplate.postForEntity("/api/v1/newsletter", e, PersistableCustomerOptin.class);

        if (resp.getStatusCode() != HttpStatus.OK) {
            assertNotNull(resp);
            assertTrue(true);
        } else {
            assertTrue(false);
        }
    }

    public void createNullCustomerNewsTest() throws Exception {

        ObjectWriter writer = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = writer.writeValueAsString(null);

        HttpEntity<String> e = new HttpEntity<>(json);
        ResponseEntity<?> resp = testRestTemplate.postForEntity("/api/v1/newsletter", e, PersistableCustomerOptin.class);

        if (resp.getStatusCode() != HttpStatus.OK) {
            assertNotNull(resp);
            assertTrue(true);
        } else {
            assertTrue(false);
        }
    }


    @Test
    public void getUserTest() throws Exception {

        PersistableUser newUser = new PersistableUser();
        newUser.setDefaultLanguage("en");
        newUser.setEmailAddress("test@test.com");
        newUser.setFirstName("Test");
        newUser.setLastName("User");
        newUser.setUserName("test@test.com");
        newUser.setPassword("Password1");

        PersistableGroup g = new PersistableGroup();
        g.setName("ADMIN");

        newUser.getGroups().add(g);

        HttpEntity<PersistableUser> persistableUser = new HttpEntity<PersistableUser>(newUser, getHeader());

        ReadableUser user = null;
        ResponseEntity<ReadableUser> response = testRestTemplate.exchange(String.format("/api/v1/private/user/"), HttpMethod.POST,
                persistableUser, ReadableUser.class);
        if (response.getStatusCode() != HttpStatus.OK) {
            assertTrue(false);
        } else {
            user = response.getBody();
            assertNotNull(user);
        }

        System.out.println(persistableUser.getBody().getId());

        HttpEntity<String> httpEntity = new HttpEntity<>(getHeader());

        ResponseEntity<ReadableUser> response1 = testRestTemplate.exchange(String.format("/api/v1/private/users/" + 1L), HttpMethod.GET,
                httpEntity, ReadableUser.class);
        if (response.getStatusCode() != HttpStatus.OK) {
            throw new Exception(response.toString());
        } else {
            assertNotNull(user);
        }
    }

    @Test
    public void getNotExistingUserTest() throws Exception {
        HttpEntity<String> httpEntity = new HttpEntity<>(getHeader());

        ResponseEntity<ReadableUser> response = testRestTemplate.exchange(String.format("/api/v1/private/users/" + 1423L), HttpMethod.GET,
                httpEntity, ReadableUser.class);
        if (response.getStatusCode() != HttpStatus.OK) {
            assertTrue(true);
        } else {
            assertTrue(false);
        }
    }

    @Test
    public void changePasswordWithoutUserTest() throws Exception {
        String oldPassword = "password1";
        String newPassword = "password2";

        UserPassword userPassword = new UserPassword();
        userPassword.setPassword(oldPassword);
        userPassword.setChangePassword(newPassword);

        HttpEntity<UserPassword> changePasswordEntity = new HttpEntity<UserPassword>(userPassword, getHeader());


        ResponseEntity<Void> changePassword = testRestTemplate.exchange(String.format("/api/v1/private/user/" + 2L + "/password"), HttpMethod.PATCH, changePasswordEntity, Void.class);
        if (changePassword.getStatusCode() != HttpStatus.OK) {
            assertTrue(true);
        } else {
            assertTrue(false);
        }
    }

    @Test
    public void createUserChangePassword() throws Exception {

        PersistableUser newUser = new PersistableUser();
        newUser.setDefaultLanguage("en");
        newUser.setEmailAddress("test@test.com");
        newUser.setFirstName("Test");
        newUser.setLastName("User");
        newUser.setUserName("test@test.com");
        newUser.setPassword("password1");

        PersistableGroup g = new PersistableGroup();
        g.setName("ADMIN");

        newUser.getGroups().add(g);

        HttpEntity<PersistableUser> persistableUser = new HttpEntity<PersistableUser>(newUser, getHeader());

        ReadableUser user = null;
        ResponseEntity<ReadableUser> response = testRestTemplate.exchange(String.format("/api/v1/private/user/"), HttpMethod.POST,
                persistableUser, ReadableUser.class);
        if (response.getStatusCode() != HttpStatus.OK) {
            throw new Exception(response.toString());
        } else {
            user = response.getBody();
            assertNotNull(user);
        }

        String oldPassword = "password1";
        String newPassword = "password2";

        UserPassword userPassword = new UserPassword();
        userPassword.setPassword(oldPassword);
        userPassword.setChangePassword(newPassword);

        HttpEntity<UserPassword> changePasswordEntity = new HttpEntity<UserPassword>(userPassword, getHeader());


        ResponseEntity<Void> changePassword = testRestTemplate.exchange(String.format("/api/v1/private/user/" + user.getId() + "/password"), HttpMethod.PATCH, changePasswordEntity, Void.class);
        if (changePassword.getStatusCode() != HttpStatus.OK) {
            assertTrue(false);
        } else {
            assertTrue(true);
        }
    }

}
