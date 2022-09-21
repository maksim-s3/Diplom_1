import com.github.javafaker.Faker;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.*;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {
    private Burger burger;
    private Faker faker;

    @Mock
    Bun bun;
    @Mock
    Ingredient ingredient1;
    @Mock
    Ingredient ingredient2;

    @Before
    public void setUp(){
        burger = new Burger();
        faker = new Faker();
    }
    @Test
    public void checkSetBuns(){
        burger.setBuns(bun);
        Assert.assertEquals("The field bun does not match the argument", bun, burger.bun);
    }

    @Test
    public void checkAddIngredient(){
        burger.addIngredient(ingredient1);
        Assert.assertEquals("Ingredient not added", ingredient1, burger.ingredients.get(0));
    }

    @Test
    public void checkRemoveIngredient(){
        burger.addIngredient(ingredient1);

        burger.removeIngredient(0);
        Assert.assertFalse("Ingredient not removed", burger.ingredients.contains(ingredient1));
    }

    @Test
    public void checkMoveIngredient(){
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);

        burger.moveIngredient(1, 0);
        int expectedIndex = 0;
        int actualIndex = burger.ingredients.indexOf(ingredient2);
        Assert.assertEquals("Index not correct", expectedIndex, actualIndex);

    }

    @Test
    public void checkGetPrice(){
        burger.setBuns(bun);
        Mockito.when(bun.getPrice()).thenReturn(100F);
        burger.addIngredient(ingredient1);
        Mockito.when(ingredient1.getPrice()).thenReturn(200F);
        burger.addIngredient(ingredient2);
        Mockito.when(ingredient2.getPrice()).thenReturn(300F);

        float actualPrice = burger.getPrice();
        float expectedPrice = 700;
        Assert.assertEquals("Price is incorrect", expectedPrice, actualPrice, 0.0);
    }

    @Test
    public void checkGetReceipt(){
        burger.setBuns(bun);
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        //с помощью библиотеки Faker даю булочке и ингредиентам какие-то осмысленные имена
        String bunName = faker.address().city();
        String ingredient1Name = faker.funnyName().name();
        String ingredient2Name = faker.funnyName().name();

        Mockito.when(bun.getName()).thenReturn(bunName);
        Mockito.when(ingredient1.getName()).thenReturn(ingredient1Name);
        Mockito.when(ingredient2.getName()).thenReturn(ingredient2Name);

        Mockito.when(bun.getPrice()).thenReturn(100F);
        Mockito.when(ingredient1.getPrice()).thenReturn(200F);
        Mockito.when(ingredient2.getPrice()).thenReturn(300F);

        Mockito.when(ingredient1.getType()).thenReturn(IngredientType.SAUCE);
        Mockito.when(ingredient2.getType()).thenReturn(IngredientType.FILLING);

        String expectedReceipt = String.format("(==== %s ====)%n" +
                "= sauce %s =%n" +
                "= filling %s =%n" +
                "(==== %s ====)%n" +
                "%nPrice: %f%n", bunName,ingredient1Name, ingredient2Name, bunName, 700f);
        Assert.assertEquals("Receipt is incorrect", expectedReceipt, burger.getReceipt());
    }

    @After
    public void tearDown(){
        burger.ingredients.clear();
    }

}
