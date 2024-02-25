package praktikum;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.Mockito.when;
import static praktikum.IngredientType.FILLING;
import static praktikum.IngredientType.SAUCE;

@RunWith(MockitoJUnitRunner.class)

public class BurgerTest {

    @Mock
    private Bun bun;
    Burger burger = new Burger();

    @Mock
    Ingredient ingredient;



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
    public void getReceiptTest() {

        Mockito.when(bun.getName()).thenReturn("black bun");
        when(bun.getPrice()).thenReturn(988.0f);
        Mockito.when(ingredient.getType()).thenReturn(FILLING);
        when(ingredient.getName()).thenReturn("cutlet");
        when(ingredient.getPrice()).thenReturn(1337.0f);

        Burger burger = new Burger();
        burger.setBuns(bun);
        burger.addIngredient(ingredient);
        String expectedReciept = "(==== black bun ====)\n= filling cutlet =\n(==== black bun ====)\n\nPrice: 3313.000000\n";

        assertEquals(expectedReciept, burger.getReceipt());
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
        burger.addIngredient(new Ingredient(FILLING, "dinosaur", 200));
        burger.addIngredient(new Ingredient(SAUCE, "chili sauce", 300));
        Ingredient expectedValue = burger.ingredients.get(1);
        burger.moveIngredient(0, 1);
        Ingredient actualValue = burger.ingredients.get(0);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void setBunsTest() {
        Burger burger = new Burger();
        burger.setBuns(bun);
        assertEquals(bun, burger.bun);
    }
}

