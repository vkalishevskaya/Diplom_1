package praktikum;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.Mockito.when;
import static praktikum.IngredientType.FILLING;

@RunWith(MockitoJUnitRunner.class)

public class BurgerTest {

    @Mock
    private Bun bun;
    Burger burger = new Burger();

    @Mock
    Ingredient ingredient;

    @Mock
    private Burger ingredients;


    @Test
    public void getPrice() {

        when(bun.getPrice())
                .thenReturn(988.0f);
        when(ingredient.getPrice())
                .thenReturn(1337.0f);

        Burger burger = new Burger();
        burger.setBuns(bun);
        burger.addIngredient(ingredient);

        MatcherAssert.assertThat(3313.0f, equalTo(burger.getPrice()));
    }

    @Test
    public void getReceipt() {

        Mockito.when(bun.getName()).thenReturn("black bun");
        when(bun.getPrice()).thenReturn(988.0f);
        Mockito.when(ingredient.getType()).thenReturn(FILLING);
        when(ingredient.getName()).thenReturn("cutlet");
        when(ingredient.getPrice()).thenReturn(1337.0f);

        Burger burger = new Burger();
        burger.setBuns(bun);
        burger.addIngredient(ingredient);

        MatcherAssert.assertThat(("(==== black bun ====)\n= filling cutlet =\n(==== black bun ====)\n\nPrice: 3313.000000\n"), equalTo(burger.getReceipt()));
    }

    @Test
    public void addIngredientTest() {
        burger.addIngredient(ingredient);
        int actualValue = burger.ingredients.size();
        int expectedValue = 1;
        assertEquals( expectedValue, actualValue);
    }

    @Test
    public void removeIngredientTest() {
        burger.addIngredient(ingredient);
        burger.removeIngredient(0);
        int actualValue = burger.ingredients.size();
        int expectedValue = 0;
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void moveIngredientTest() {
        burger.addIngredient(new Ingredient(IngredientType.FILLING, "dinosaur", 200));
        burger.addIngredient(new Ingredient(IngredientType.SAUCE, "chili sauce", 300));
        burger.addIngredient(new Ingredient(FILLING, "sausage", 300));
        burger.moveIngredient(0, 2);
        String actualValue = burger.ingredients.get(0).name;
        String expectedValue = "sausage";
        assertEquals( expectedValue, actualValue);
    }

    @Test
    public void setBunsTest() {
        Burger burger = new Burger();
        burger.setBuns(bun);
        assertEquals(bun, burger.bun);
    }
}

