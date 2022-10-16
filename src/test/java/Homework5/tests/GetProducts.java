package Homework5.tests;

import Homework5.api.ProductService;
import Homework5.dto.Product;
import Homework5.utils.RetrofitUtils;
import okhttp3.ResponseBody;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class GetProducts {
    static ProductService productService;

    @BeforeAll
    static void beforeAll() {
        productService = RetrofitUtils.getRetrofit()
                .create(ProductService.class);
    }
    @Test
    void getProductsPositiveTest() throws IOException {
        Response<ResponseBody> response = productService.getProducts().execute();
        assertThat(response.code(), CoreMatchers.is(200));
        assertThat(response.headers().get("Content-Type"), equalTo("application/json"));
    }

    @Test
    void getProductByIdPositiveTest() throws IOException {
        Response<Product> response = productService.getProductById(3).execute();

        assertThat(response.code(), CoreMatchers.is(200));
        assertThat(response.body().getId(), equalTo(3));
        //assertThat(response.);
    }

}
