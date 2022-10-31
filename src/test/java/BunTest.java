import com.github.javafaker.Faker;
import org.junit.Before;
import org.junit.Test;
import praktikum.Bun;

import static org.junit.Assert.assertEquals;

public class BunTest {
    private Faker faker = new Faker();
    private String name;
    private float price;
    private Bun bun;

    @Before
    public void setUp(){
        name = faker.country().name();
        price = faker.number().numberBetween(100, 999);
        bun = new Bun(name, price);
    }

    @Test
    public void checkTheCorrectName(){
        String actualName = bun.name;
        assertEquals("Name is incorrect", name, actualName);
    }

    @Test
    public void checkTheCorrectPrice(){
        float actualPrice = bun.price;
        assertEquals("Price is incorrect", price, actualPrice, 0.0);
    }

    @Test
    public void checkTheCorrectReturnName(){
        String actualName = bun.getName();
        assertEquals("Name is incorrect", name, actualName);
    }

    @Test
    public void checkTheCorrectReturnPrice(){
        float actualPrice = bun.getPrice();
        assertEquals("Price is incorrect", price, actualPrice, 0.0);
    }
}
