package PruebasEmanuelle;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.salesmanager.core.business.constants.Constants;
import com.salesmanager.core.model.customer.CustomerGender;
import com.salesmanager.shop.model.catalog.SearchProductList;
import com.salesmanager.shop.model.catalog.SearchProductRequest;
import com.salesmanager.shop.model.catalog.category.*;
import com.salesmanager.shop.model.catalog.product.*;
import com.salesmanager.shop.model.customer.PersistableCustomer;
import com.salesmanager.shop.model.customer.address.Address;
import com.salesmanager.shop.model.customer.optin.PersistableCustomerOptin;
import com.salesmanager.shop.model.references.PersistableAddress;
import com.salesmanager.shop.model.security.PersistableGroup;
import com.salesmanager.shop.model.shoppingcart.PersistableShoppingCartItem;
import com.salesmanager.shop.model.shoppingcart.ReadableShoppingCart;
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
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.springframework.http.HttpStatus.*;


public class Pruebas  extends ServicesTestSupport {

    @Test
    public void postAddCartTest(){

        ReadableProduct producto = super.readyToWorkProduct("addToCart");

        PersistableShoppingCartItem carrito = new PersistableShoppingCartItem();
        carrito.setProduct(producto.getId());
        carrito.setQuantity(1);

        HttpEntity<PersistableShoppingCartItem> entidad_de_carro = new HttpEntity<>(carrito, getHeader());
        ResponseEntity<ReadableShoppingCart> respuesta = testRestTemplate.postForEntity(String.format("/api/v1/cart/"), entidad_de_carro, ReadableShoppingCart.class);

        // Se verifica que no sea nulo
        assertNotNull(respuesta);
        // Se verifica que se debe crear
        assertThat(respuesta.getStatusCode(), is(CREATED));

    }

    @Test
    public void postNullCartTest(){

        PersistableShoppingCartItem carrito = null;

        HttpEntity<PersistableShoppingCartItem> entidad_de_carro = new HttpEntity<>(carrito, getHeader());
        ResponseEntity<ReadableShoppingCart> respuesta = testRestTemplate.postForEntity(String.format("/api/v1/cart/"), entidad_de_carro, ReadableShoppingCart.class);

        // Se verifica que no sea nulo
        assertNotNull(respuesta);
        // Se verifica que retorne error
        assertThat(respuesta.getStatusCode(), is(INTERNAL_SERVER_ERROR));
    }

    @Test
    public void postEmptyCartTest(){

        PersistableShoppingCartItem carrito = new PersistableShoppingCartItem();

        HttpEntity<PersistableShoppingCartItem> entidad_de_carro = new HttpEntity<>(carrito, getHeader());
        ResponseEntity<ReadableShoppingCart> respuesta = testRestTemplate.postForEntity(String.format("/api/v1/cart/"), entidad_de_carro, ReadableShoppingCart.class);

        // Se verifica que lance el error
        assertThat(respuesta.getStatusCode(), is(SERVICE_UNAVAILABLE));
    }

    @Test
    public void getCategoryTest() throws Exception {

        HttpEntity<String> entidad_hhtp_del_header = new HttpEntity<>(getHeader());

        ResponseEntity<ReadableCategoryList> respuesta = testRestTemplate.exchange(String.format("/api/v1/category/"), HttpMethod.GET,
                entidad_hhtp_del_header, ReadableCategoryList.class);
        if (respuesta.getStatusCode() != HttpStatus.OK) {
            throw new Exception(respuesta.toString());
        } else {
            // Debe retornar la lista de las categorías
            List<ReadableCategory> categorias = respuesta.getBody().getCategories();
            // Se verifica que no sea nulo
            assertNotNull(categorias);
        }
    }

    @Test
    public void postCategoryTest() throws JsonProcessingException {

        PersistableCategory categoria = new PersistableCategory();
        categoria.setCode("java");
        categoria.setSortOrder(1);
        categoria.setVisible(true);
        categoria.setDepth(4);

        Category padre = new Category();

        categoria.setParent(padre);

        CategoryDescription descripcion = new CategoryDescription();
        descripcion.setLanguage("en");
        descripcion.setName("Java");
        descripcion.setFriendlyUrl("java");
        descripcion.setTitle("Java");

        List<CategoryDescription> lista_de_descripciones = new ArrayList<>();
        lista_de_descripciones.add(descripcion);

        categoria.setDescriptions(lista_de_descripciones);

        ObjectWriter writer = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = writer.writeValueAsString(categoria);

        HttpEntity<String> entity = new HttpEntity<>(json, getHeader());

        ResponseEntity respuesta = testRestTemplate.postForEntity("/api/v1/private/category", entity, PersistableCategory.class);
        PersistableCategory categoria_obtenida = (PersistableCategory) respuesta.getBody();
        assertThat(respuesta.getStatusCode(), is(CREATED));
        assertNotNull(categoria_obtenida.getId());
    }

    @Test
    public void postCategoryNullTest() throws Exception {

        ObjectWriter w = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String string_json = w.writeValueAsString(null);

        HttpEntity<String> entidad = new HttpEntity<>(string_json, getHeader());

        ResponseEntity respuesta = testRestTemplate.postForEntity("/api/v1/private/category", entidad, PersistableCategory.class);
        assertThat(respuesta.getStatusCode(), is(INTERNAL_SERVER_ERROR));

    }

    @Test
    public void deleteCategoryTest() throws Exception {

        final HttpEntity<String> entidad_http = new HttpEntity<>(getHeader());

        testRestTemplate.exchange("/services/DEFAULT/category/50", HttpMethod.DELETE, entidad_http, Category.class);
    }

    @Test
    public void registerCustomerTest() {

        PersistableCustomer cliente = new PersistableCustomer();

        cliente.setEmailAddress("test@test.com");
        cliente.setPassword("customer_test");
        cliente.setGender(CustomerGender.M.name());
        cliente.setLanguage("en");

        Address informacion = new Address();

        informacion.setFirstName("nombre");
        informacion.setLastName("apellido");
        informacion.setCountry("CR");
        cliente.setBilling(informacion);
        cliente.setStoreCode(Constants.DEFAULT_STORE);
        HttpEntity<PersistableCustomer> entidad = new HttpEntity<>(cliente, getHeader());

        ResponseEntity<PersistableCustomer> respuesta = testRestTemplate.postForEntity("/api/v1/customer/register", entidad, PersistableCustomer.class);
        assertThat(respuesta.getStatusCode(), is(OK));

    }

    @Test
    public void registerNullCustomerTest() {

        PersistableCustomer cliente = null;
        HttpEntity<PersistableCustomer> entidad = new HttpEntity<>(cliente, getHeader());

        ResponseEntity<PersistableCustomer> respuesta = testRestTemplate.postForEntity("/api/v1/customer/register", entidad, PersistableCustomer.class);
        assertThat(respuesta.getStatusCode(), is(INTERNAL_SERVER_ERROR));

    }

    @Test
    public void registerWithoutAddressCustomerTest() {

        PersistableCustomer cliente = new PersistableCustomer();

        cliente.setEmailAddress("test1@test.com");
        cliente.setPassword("pass");
        cliente.setGender(CustomerGender.M.name());
        cliente.setLanguage("CR");

        cliente.setStoreCode(Constants.DEFAULT_STORE);
        HttpEntity<PersistableCustomer> entidad = new HttpEntity<>(cliente, getHeader());

        ResponseEntity<PersistableCustomer> respuesta = testRestTemplate.postForEntity("/api/v1/customer/register", entidad, PersistableCustomer.class);
        assertThat(respuesta.getStatusCode(), is(INTERNAL_SERVER_ERROR));

    }

    @Test
    public void registerWithNullAddressCustomerTest() {

        PersistableCustomer cliente = new PersistableCustomer();

        cliente.setEmailAddress("test2@test.com");
        cliente.setPassword("pass");
        cliente.setGender(CustomerGender.M.name());
        cliente.setLanguage("CR");

        cliente.setStoreCode(Constants.DEFAULT_STORE);
        HttpEntity<PersistableCustomer> entidad = new HttpEntity<>(cliente, getHeader());

        ResponseEntity<PersistableCustomer> respuesta = testRestTemplate.postForEntity("/api/v1/customer/register", entidad, PersistableCustomer.class);
        assertThat(respuesta.getStatusCode(), is(INTERNAL_SERVER_ERROR));

    }

    @Test
    public void loginCustomerTest() {

        PersistableCustomer cliente = new PersistableCustomer();

        cliente.setEmailAddress("test_login3@test.com");
        cliente.setPassword("password");
        cliente.setGender(CustomerGender.M.name());
        cliente.setLanguage("CR");

        Address informacion = new Address();

        informacion.setFirstName("customer_name");
        informacion.setLastName("customer_name");
        informacion.setCountry("CR");
        cliente.setBilling(informacion);
        cliente.setStoreCode(Constants.DEFAULT_STORE);

        HttpEntity<PersistableCustomer> entidad = new HttpEntity<>(cliente, getHeader());

        ResponseEntity<PersistableCustomer> respuesta = testRestTemplate.postForEntity("/api/v1/customer/register", entidad, PersistableCustomer.class);
        assertThat(respuesta.getStatusCode(), is(OK));

        ResponseEntity<AuthenticationResponse> respuesta_login = testRestTemplate.postForEntity("/api/v1/customer/login", new HttpEntity<>(new AuthenticationRequest("test_login3@test.com", "password")),
                AuthenticationResponse.class);

        assertThat(respuesta_login.getStatusCode(), is(OK));
        assertNotNull(respuesta_login.getBody().getToken());

    }

    @Test
    public void loginNotRegisterCustomerTest(){

        try {

            ResponseEntity<AuthenticationResponse> respuesta_login = testRestTemplate.postForEntity("/api/v1/customer/login", new HttpEntity<>(new AuthenticationRequest("customer4@test.com", "pass")),
                    AuthenticationResponse.class);
            assertThat(respuesta_login.getStatusCode(), is(NOT_FOUND));
            assertNotNull(respuesta_login.getBody().getToken());
        }catch (NullPointerException e){
            assertTrue(true);
        }
    }

    @Test
    public void createProductTest(){

        PersistableProduct producto = new PersistableProduct();
        ArrayList<Category> lista_de_categorias = new ArrayList<>();
        producto.setCategories(lista_de_categorias);
        ProductSpecification especificaciones = new ProductSpecification();
        especificaciones.setManufacturer(com.salesmanager.core.model.catalog.product.manufacturer.Manufacturer.DEFAULT_MANUFACTURER);
        producto.setProductSpecifications(especificaciones);
        producto.setPrice(BigDecimal.TEN);
        producto.setSku("321");
        HttpEntity<PersistableProduct> entity = new HttpEntity<>(producto, getHeader());

        ResponseEntity<PersistableProduct> response = testRestTemplate.postForEntity("/api/v1/private/product?store=" + Constants.DEFAULT_STORE, entity, PersistableProduct.class);
        assertThat(response.getStatusCode(), is(CREATED));
    }

    @Test
    public void createNullProductTest(){

        PersistableProduct producto = null;
        HttpEntity<PersistableProduct> entidad = new HttpEntity<>(producto, getHeader());

        ResponseEntity<PersistableProduct> respuesta = testRestTemplate.postForEntity("/api/v1/private/product?store=" + Constants.DEFAULT_STORE, entidad, PersistableProduct.class);
        assertThat(respuesta.getStatusCode(), is(INTERNAL_SERVER_ERROR));

    }

    @Test
    public void createNonValueProductTest(){

        PersistableProduct producto = new PersistableProduct();
        HttpEntity<PersistableProduct> entidad = new HttpEntity<>(producto, getHeader());

        ResponseEntity<PersistableProduct> respuesta = testRestTemplate.postForEntity("/api/v1/private/product?store=" + Constants.DEFAULT_STORE, entidad, PersistableProduct.class);
        assertThat(respuesta.getStatusCode(), is(BAD_REQUEST));

    }

    @Test
    public void searchItemTest(){

        PersistableProduct producto = super.product("PRODUCTOPRUEBA");

        HttpEntity<PersistableProduct> entidad = new HttpEntity<>(producto, getHeader());

        ResponseEntity<PersistableProduct> respuesta = testRestTemplate.postForEntity("/api/v1/private/product?store=" + Constants.DEFAULT_STORE, entidad, PersistableProduct.class);
        assertThat(respuesta.getStatusCode(), is(CREATED));

        SearchProductRequest query_de_busqueda = new SearchProductRequest();
        query_de_busqueda.setQuery("PRODUCTOPRUEBA");
        HttpEntity<SearchProductRequest> respuesta_de_query = new HttpEntity<>(query_de_busqueda, getHeader());


        ResponseEntity<SearchProductList> respuesta_del_api = testRestTemplate.postForEntity("/api/v1/search?store=" + Constants.DEFAULT_STORE, respuesta_de_query, SearchProductList.class);
        assertThat(respuesta_del_api.getStatusCode(), is(OK));

    }

    @Test
    public void searchNonExistingItemTest(){

        SearchProductRequest query_de_busqueda = new SearchProductRequest();
        query_de_busqueda.setQuery("TEST");
        HttpEntity<SearchProductRequest> respuesta_de_query = new HttpEntity<>(query_de_busqueda, getHeader());

        ResponseEntity<SearchProductList> respuesta_del_api = testRestTemplate.postForEntity("/api/v1/search?store=" + Constants.DEFAULT_STORE, respuesta_de_query, SearchProductList.class);
        assertThat(respuesta_del_api.getStatusCode(), is(NOT_FOUND));

    }

    @Test
    public void createStoreTest() throws Exception {

        PersistableAddress direccion = new PersistableAddress();
        direccion.setAddress("121212 simple address");
        direccion.setPostalCode("12345");
        direccion.setCountry("US");
        direccion.setCity("FT LD");
        direccion.setStateProvince("FL");

        PersistableMerchantStore tienda_creada = new PersistableMerchantStore();
        tienda_creada.setCode("create_test");
        tienda_creada.setCurrency("CAD");
        tienda_creada.setDefaultLanguage("en");
        tienda_creada.setEmail("test@test.com");
        tienda_creada.setName("create_test");
        tienda_creada.setPhone("444-555-6666");
        tienda_creada.setSupportedLanguages(Arrays.asList("en"));
        tienda_creada.setAddress(direccion);

        final HttpEntity<PersistableMerchantStore> entidad = new HttpEntity<PersistableMerchantStore>(tienda_creada, getHeader());

        ResponseEntity<ReadableMerchantStore> respuesta = testRestTemplate.exchange(String.format("/api/v1/private/store/"), HttpMethod.POST, entidad, ReadableMerchantStore.class);

        if (respuesta.getStatusCode() != OK) {
            throw new Exception(respuesta.toString());
        } else {
            final ReadableMerchantStore store = respuesta.getBody();
            assertNotNull(store);
        }
    }

    @Test
    public void duplicateCreateStoreTest() throws Exception {

        PersistableAddress direccion1 = new PersistableAddress();
        direccion1.setAddress("direccion");
        direccion1.setPostalCode("12345");
        direccion1.setCountry("US");
        direccion1.setCity("FT LD");
        direccion1.setStateProvince("FL");

        PersistableMerchantStore tienda_creada1 = new PersistableMerchantStore();
        tienda_creada1.setCode("test");
        tienda_creada1.setCurrency("CAD");
        tienda_creada1.setDefaultLanguage("en");
        tienda_creada1.setEmail("test@test.com");
        tienda_creada1.setName("test");
        tienda_creada1.setPhone("444-555-6666");
        tienda_creada1.setSupportedLanguages(Arrays.asList("en"));
        tienda_creada1.setAddress(direccion1);

        final HttpEntity<PersistableMerchantStore> entidad = new HttpEntity<PersistableMerchantStore>(tienda_creada1, getHeader());

        ResponseEntity<ReadableMerchantStore> response1 = testRestTemplate.exchange(String.format("/api/v1/private/store/"), HttpMethod.POST, entidad, ReadableMerchantStore.class);

        PersistableAddress direccion2 = new PersistableAddress();
        direccion2.setAddress("direccion");
        direccion2.setPostalCode("12345");
        direccion2.setCountry("US");
        direccion2.setCity("FT LD");
        direccion2.setStateProvince("FL");

        PersistableMerchantStore tienda_creada2 = new PersistableMerchantStore();
        tienda_creada2.setCode("test");
        tienda_creada2.setCurrency("CAD");
        tienda_creada2.setDefaultLanguage("en");
        tienda_creada2.setEmail("test@test.com");
        tienda_creada2.setName("test");
        tienda_creada2.setPhone("444-555-6666");
        tienda_creada2.setSupportedLanguages(Arrays.asList("en"));
        tienda_creada2.setAddress(direccion2);

        HttpEntity<PersistableMerchantStore> entidad2 = new HttpEntity<PersistableMerchantStore>(tienda_creada2, getHeader());

        ResponseEntity<ReadableMerchantStore> respuesta2 = testRestTemplate.exchange(String.format("/api/v1/private/store/"), HttpMethod.POST, entidad2, ReadableMerchantStore.class);


        if (response1.getStatusCode() != OK || respuesta2.getStatusCode() != OK) {
            assertTrue(true);
        } else {
            assertTrue(false);
        }
    }

    @Test
    public void CreateStoreWithoutAdressTest() throws Exception {

        PersistableMerchantStore tienda_creada = new PersistableMerchantStore();
        tienda_creada.setCode("test");
        tienda_creada.setCurrency("CAD");
        tienda_creada.setDefaultLanguage("en");
        tienda_creada.setEmail("test@test.com");
        tienda_creada.setName("test");
        tienda_creada.setPhone("444-555-6666");
        tienda_creada.setSupportedLanguages(Arrays.asList("en"));

        final HttpEntity<PersistableMerchantStore> entidad = new HttpEntity<PersistableMerchantStore>(tienda_creada, getHeader());

        ResponseEntity<ReadableMerchantStore> respuesta = testRestTemplate.exchange(String.format("/api/v1/private/store/"), HttpMethod.POST, entidad, ReadableMerchantStore.class);

        if (respuesta.getStatusCode() != OK) {
            assertTrue(true);
        } else {
            final ReadableMerchantStore store = respuesta.getBody();
            assertNotNull(store);
        }
    }

    @Test
    public void createStoreWithNullAdressTest() throws Exception {

        PersistableAddress direccion = new PersistableAddress();

        PersistableMerchantStore createdStore = new PersistableMerchantStore();
        createdStore.setCode("test");
        createdStore.setCurrency("CAD");
        createdStore.setDefaultLanguage("en");
        createdStore.setEmail("test@test.com");
        createdStore.setName("test");
        createdStore.setPhone("444-555-6666");
        createdStore.setSupportedLanguages(Arrays.asList("en"));
        createdStore.setAddress(direccion);


        final HttpEntity<PersistableMerchantStore> entidad = new HttpEntity<PersistableMerchantStore>(createdStore, getHeader());

        ResponseEntity<ReadableMerchantStore> respuesta = testRestTemplate.exchange(String.format("/api/v1/private/store/"), HttpMethod.POST, entidad, ReadableMerchantStore.class);

        if (respuesta.getStatusCode() != HttpStatus.OK) {
            assertTrue(true);
        } else {
            final ReadableMerchantStore store = respuesta.getBody();
            assertNotNull(store);
        }
    }

    @Test
    public void createStoreAndDeleteTest() throws Exception {

        PersistableAddress direccion = new PersistableAddress();
        direccion.setAddress("121212 simple address");
        direccion.setPostalCode("54321");
        direccion.setCountry("US");
        direccion.setCity("FT LD");
        direccion.setStateProvince("FL");

        PersistableMerchantStore createdStore = new PersistableMerchantStore();
        createdStore.setCode("test_store_n_delete_test");
        createdStore.setCurrency("CAD");
        createdStore.setDefaultLanguage("en");
        createdStore.setEmail("test@test.com");
        createdStore.setName("test_store_n_delete");
        createdStore.setPhone("444-555-6666");
        createdStore.setSupportedLanguages(Arrays.asList("en"));
        createdStore.setAddress(direccion);

        final HttpEntity<PersistableMerchantStore> entidad = new HttpEntity<PersistableMerchantStore>(createdStore, getHeader());

        ResponseEntity<ReadableMerchantStore> respuesta = testRestTemplate.exchange(String.format("/api/v1/private/store/"), HttpMethod.POST, entidad, ReadableMerchantStore.class);

        if (respuesta.getStatusCode() != HttpStatus.OK) {
            throw new Exception(respuesta.toString());
        } else {
            final ReadableMerchantStore tienda = respuesta.getBody();
            assertNotNull(tienda);
        }

        //delete store
        ResponseEntity<Void> respuesta_eliminada = testRestTemplate.exchange(String.format("/api/v1/private/store/" + "test_store_n_delete_test"), HttpMethod.DELETE, respuesta, Void.class);

        assertThat(respuesta_eliminada.getStatusCode(), Matchers.is(HttpStatus.OK));

    }

    @Test
    public void deleteEmptyStoreTest(){

        PersistableMerchantStore tienda_creada = new PersistableMerchantStore();

        final HttpEntity<PersistableMerchantStore> entidad = new HttpEntity<PersistableMerchantStore>(tienda_creada, getHeader());

        ResponseEntity<Void> respuesta_eliminada = testRestTemplate.exchange(String.format("/api/v1/private/store/" + "delete_empty_store"), HttpMethod.DELETE, entidad, Void.class);

        MatcherAssert.assertThat(respuesta_eliminada.getStatusCode(), Matchers.is(HttpStatus.NOT_FOUND));
    }

    @Test
    public void deleteNullStoreTest(){

        PersistableMerchantStore tienda_creada = null;

        final HttpEntity<PersistableMerchantStore> entidad = new HttpEntity<PersistableMerchantStore>(tienda_creada, getHeader());

        ResponseEntity<Void> respuesta_eliminada = testRestTemplate.exchange(String.format("/api/v1/private/store/" + "delete_null_store"), HttpMethod.DELETE, entidad, Void.class);

        MatcherAssert.assertThat(respuesta_eliminada.getStatusCode(), Matchers.is(HttpStatus.NOT_FOUND));
    }

    public void createCustomerNewsTest() throws Exception {

        PersistableCustomerOptin opcion_del_cliente = new PersistableCustomerOptin();
        opcion_del_cliente.setEmail("test@test.com");
        opcion_del_cliente.setFirstName("Jack");
        opcion_del_cliente.setLastName("John");

        ObjectWriter w = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = w.writeValueAsString(opcion_del_cliente);

        HttpEntity<String> entidad = new HttpEntity<>(json);
        ResponseEntity<?> respuesta = testRestTemplate.postForEntity("/api/v1/newsletter", entidad, PersistableCustomerOptin.class);

        if (respuesta.getStatusCode() != HttpStatus.OK) {
            throw new Exception(respuesta.toString());
        } else {
            assertTrue(true);
        }
    }

    @Test
    public void createDeplicateCustomerNewsTest() throws Exception {

        PersistableCustomerOptin respuesta_cliente1 = new PersistableCustomerOptin();
        respuesta_cliente1.setEmail("test@test.com");
        respuesta_cliente1.setFirstName("Emanuelle");
        respuesta_cliente1.setLastName("John");

        ObjectWriter writer1 = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json1 = writer1.writeValueAsString(respuesta_cliente1);

        HttpEntity<String> entidad1 = new HttpEntity<>(json1);
        ResponseEntity<?> resp1 = testRestTemplate.postForEntity("/api/v1/newsletter", entidad1, PersistableCustomerOptin.class);

        PersistableCustomerOptin respuesta_cliente2 = new PersistableCustomerOptin();
        respuesta_cliente2.setEmail("test@test.com");
        respuesta_cliente2.setFirstName("Emanuelle");
        respuesta_cliente2.setLastName("John");

        ObjectWriter writer2 = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json2 = writer2.writeValueAsString(respuesta_cliente2);

        HttpEntity<String> entidad2 = new HttpEntity<>(json2);
        ResponseEntity<?> resp2 = testRestTemplate.postForEntity("/api/v1/newsletter", entidad2, PersistableCustomerOptin.class);


        if (resp1.getStatusCode() != HttpStatus.OK || resp2.getStatusCode() != HttpStatus.OK) {
            assertTrue(true);
        } else {
            assertTrue(false);
        }
    }

    public void createEmptyCustomerNewsTest() throws Exception {

        PersistableCustomerOptin respuesta_cliente = new PersistableCustomerOptin();

        ObjectWriter writer = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = writer.writeValueAsString(respuesta_cliente);

        HttpEntity<String> entidad = new HttpEntity<>(json);
        ResponseEntity<?> respuesta = testRestTemplate.postForEntity("/api/v1/newsletter", entidad, PersistableCustomerOptin.class);

        if (respuesta.getStatusCode() != HttpStatus.OK) {
            assertNotNull(respuesta);
            assertTrue(true);
        } else {
            assertTrue(false);
        }
    }

    public void createNullCustomerNewsTest() throws Exception {

        ObjectWriter w = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = w.writeValueAsString(null);

        HttpEntity<String> entidad = new HttpEntity<>(json);
        ResponseEntity<?> respuesta = testRestTemplate.postForEntity("/api/v1/newsletter", entidad, PersistableCustomerOptin.class);

        if (respuesta.getStatusCode() != HttpStatus.OK) {
            assertNotNull(respuesta);
            assertTrue(true);
        } else {
            assertTrue(false);
        }
    }


    @Test
    public void getUserTest() throws Exception {

        PersistableUser usuario_nuevo = new PersistableUser();
        usuario_nuevo.setDefaultLanguage("en");
        usuario_nuevo.setEmailAddress("test_get@test.com");
        usuario_nuevo.setFirstName("Testget");
        usuario_nuevo.setLastName("Userget");
        usuario_nuevo.setUserName("test_get@test.com");
        usuario_nuevo.setPassword("Password1");

        PersistableGroup grupo_nuevo = new PersistableGroup();
        grupo_nuevo.setName("ADMIN");

        usuario_nuevo.getGroups().add(grupo_nuevo);

        HttpEntity<PersistableUser> entidad_usuario = new HttpEntity<PersistableUser>(usuario_nuevo, getHeader());

        ReadableUser user = null;
        ResponseEntity<ReadableUser> respuesta_usuario = testRestTemplate.exchange(String.format("/api/v1/private/user/"), HttpMethod.POST,
                entidad_usuario, ReadableUser.class);
        if (respuesta_usuario.getStatusCode() != OK) {
            assertTrue(false);
        } else {
            user = respuesta_usuario.getBody();
            assertNotNull(user);
        }

        HttpEntity<String> entidad_usuarios = new HttpEntity<>(getHeader());

        ResponseEntity<ReadableUser> respuesta = testRestTemplate.exchange(String.format("/api/v1/private/users/" + 1L), HttpMethod.GET,
                entidad_usuarios, ReadableUser.class);
        if (respuesta.getStatusCode() != OK) {
            throw new Exception(respuesta_usuario.toString());
        } else {
            assertEquals(respuesta.getStatusCode(),OK);
        }
    }

    @Test
    public void getNotExistingUserTest() throws Exception {
        HttpEntity<String> entidad = new HttpEntity<>(getHeader());

        ResponseEntity<ReadableUser> respuesta = testRestTemplate.exchange(String.format("/api/v1/private/users/" + 1423L), HttpMethod.GET,
                entidad, ReadableUser.class);
        if (respuesta.getStatusCode() != HttpStatus.OK) {
            assertTrue(true);
        } else {
            assertTrue(false);
        }
    }

    @Test
    public void changePasswordWithoutUserTest() throws Exception {
        String vieja = "password1";
        String nueva = "password2";

        UserPassword contrasena = new UserPassword();
        contrasena.setPassword(vieja);
        contrasena.setChangePassword(nueva);

        HttpEntity<UserPassword> changePasswordEntity = new HttpEntity<UserPassword>(contrasena, getHeader());


        ResponseEntity<Void> respuesta_cambio = testRestTemplate.exchange(String.format("/api/v1/private/user/" + 2L + "/password"), HttpMethod.PATCH, changePasswordEntity, Void.class);
        if (respuesta_cambio.getStatusCode() != HttpStatus.OK) {
            assertTrue(true);
        } else {
            assertTrue(false);
        }
    }

    @Test
    public void createUserChangePasswordTest() throws Exception {

        PersistableUser nuevo_usuario = new PersistableUser();
        nuevo_usuario.setDefaultLanguage("en");
        nuevo_usuario.setEmailAddress("test_create_change@test.com");
        nuevo_usuario.setFirstName("test_create_changeTest");
        nuevo_usuario.setLastName("test_create_changeUser");
        nuevo_usuario.setUserName("test_create_changetest@test.com");
        nuevo_usuario.setPassword("password1");

        PersistableGroup grupo = new PersistableGroup();
        grupo.setName("ADMIN");

        nuevo_usuario.getGroups().add(grupo);

        HttpEntity<PersistableUser> entidad_usuario = new HttpEntity<PersistableUser>(nuevo_usuario, getHeader());

        ReadableUser user = null;
        ResponseEntity<ReadableUser> respuesta = testRestTemplate.exchange(String.format("/api/v1/private/user/"), HttpMethod.POST,
                entidad_usuario, ReadableUser.class);
        if (respuesta.getStatusCode() != OK) {
            throw new Exception(respuesta.toString());
        } else {
            user = respuesta.getBody();
            assertNotNull(user);
        }

        String vieja = "password1";
        String nueva = "password2";

        UserPassword contrasena_usuario = new UserPassword();
        contrasena_usuario.setPassword(vieja);
        contrasena_usuario.setChangePassword(nueva);

        HttpEntity<UserPassword> changePasswordEntity = new HttpEntity<UserPassword>(contrasena_usuario, getHeader());


        ResponseEntity<Void> respuesta_cambio = testRestTemplate.exchange(String.format("/api/v1/private/user/" + user.getId() + "/password"), HttpMethod.PATCH, changePasswordEntity, Void.class);
        if (respuesta_cambio.getStatusCode() != OK) {
            assertTrue(false);
        } else {
            assertTrue(true);
        }
    }

}
