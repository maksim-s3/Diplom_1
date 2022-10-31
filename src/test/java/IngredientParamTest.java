import com.github.javafaker.Faker;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Ingredient;
import praktikum.IngredientType;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class IngredientParamTest {
    private static Faker faker = new Faker();
    private IngredientType type;
    private String name;
    private float price;

    public IngredientParamTest(IngredientType type, String name, float price) {
        this.type = type;
        this.name = name;
        this.price = price;
    }

    @Parameterized.Parameters
    public static Object[][] getDataConstructor() {
        return new Object[][]{
                {IngredientType.SAUCE, faker.funnyName().name(), faker.number().numberBetween(100,999)},
                {IngredientType.FILLING, faker.funnyName().name(), faker.number().numberBetween(100,999)},
        };
    }

    @Test
    public void testGetPrice(){
        Ingredient ingredient = new Ingredient(type, name, price);
        float expectedPrice = price;
        float actualPrice = ingredient.getPrice();
        assertEquals("Price is incorrect", expectedPrice, actualPrice, 0.0);
    }

    @Test
    public void testGetName(){
        Ingredient ingredient = new Ingredient(type, name, price);
        String expectedName = name;
        String actualName = ingredient.getName();
        assertEquals("Price is incorrect", expectedName, actualName);
    }

    @Test
    public void testGetType(){
        Ingredient ingredient = new Ingredient(type, name, price);
        IngredientType expectedType = type;
        IngredientType actualType = ingredient.getType();
        assertEquals("Type is incorrect", expectedType, actualType);
    }
}
