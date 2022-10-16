package Homework5.tests;
import Homework5.api.ProductService;
import Homework5.dto.Product;
import Homework5.utils.RetrofitUtils;
import com.github.javafaker.Faker;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ModifyProductTest {
    static ProductService productService;
    Product product;
    Faker faker = new Faker();


    @BeforeAll
    static void beforeAll() {
        productService = RetrofitUtils.getRetrofit()
                .create(ProductService.class);
    }
@BeforeEach
        void setUpForModify() {
           product = new Product().withId(2)
                   .withTitle(faker.food().ingredient())
                    .withCategoryTitle("Food").withPrice((int) (Math.random() * 10000));
   }

    @Test
    void modifyProductInFoodCategoryTest() throws IOException {
        Response<Product> response = productService.modifyProduct(product).execute();
        assertThat(response.isSuccessful(), CoreMatchers.is(true));
        assertThat(response.body().getId(), equalTo(2));
    }
}
