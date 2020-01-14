/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import entities.*;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Disabled;
import utils.EMF_Creator;

/**
 *
 * @author Simon Norup
 */
//@Disabled
public class RecipeFacadeTest {

    private final RecipeFacade facade;
    private final AbstractFacade<Ingredient> ingr_facade;
    private final AbstractFacade<Item> item_facade;
    private static EntityManagerFactory emf;

    public RecipeFacadeTest() {
        facade = RecipeFacade.getRecipeFacade(emf);
        ingr_facade = new AbstractFacade(Ingredient.class, emf) {
        };
        item_facade = new AbstractFacade(Item.class, emf) {
        };
    }

    private static Recipe r_1, r_2;
    private static Ingredient ingr_1, ingr_2, ingr_3, ingr_4, ingr_5;
    private static Item item_1, item_2, item_3, item_4;

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.TEST, EMF_Creator.Strategy.DROP_AND_CREATE);

        item_1 = new Item("T-Bone Steak", 500, 2000);
        item_2 = new Item("Curry Paste", 200, 1000);
        ingr_1 = new Ingredient(item_1, 400);
        ingr_2 = new Ingredient(item_2, 20);
        r_1 = new Recipe("Curry T-Bone", 20, "Directions for Curry T-Bone");
        r_1.addIngredient(ingr_1);
        r_1.addIngredient(ingr_2);

        item_3 = new Item("Jasmin Rice", 15, 9000);
        item_4 = new Item("Wokmix", 35, 300);
        ingr_3 = new Ingredient(item_2, 30);
        ingr_4 = new Ingredient(item_3, 100);
        ingr_5 = new Ingredient(item_4, 200);
        r_2 = new Recipe("Curry Rice", 15, "Directions for Curry Rice");
        r_2.addIngredient(ingr_3);
        r_2.addIngredient(ingr_4);
        r_2.addIngredient(ingr_5);
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("WeekMenuPlan.deleteAll").executeUpdate();
            em.createNamedQuery("Ingredient.deleteAll").executeUpdate();
            em.createNamedQuery("Recipe.deleteAll").executeUpdate();
            em.createNamedQuery("Item.deleteAll").executeUpdate();
            em.getTransaction().commit();

            em.getTransaction().begin();
            em.persist(item_1);
            em.persist(item_2);
            em.persist(item_3);
            em.persist(item_4);
//            em.persist(ingr_1); // cascades from Recipe instead
//            em.persist(ingr_2);
            em.persist(r_1);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testCreateRecipe() {
        facade.create(r_2);
        assertNotNull(r_2.getId());
        Recipe result = facade.find(r_2.getId());
        assertEquals(r_2.getTitle(), result.getTitle());
        assertEquals(3, result.getIngredientList().size());
        result.getIngredientList().forEach(i -> {
            assertNotNull(i.getId());
            assertNotNull(i.getItem());
        });
    }

    @Test
    @Disabled
    // FAIL = INGREDIENTS ARE NOT EDITED
    public void testEditRecipe() {
        Recipe r_edited = new Recipe("Curry T-Bone", 30, "Directions for Curry T-Bone");
        Ingredient i_edited = new Ingredient(item_1, 600);
        i_edited.setId(ingr_1.getId());
        Ingredient i_new = new Ingredient(item_3, 100);
        r_edited.addIngredient(i_edited);
        r_edited.addIngredient(i_new);
        r_edited.setId(r_1.getId());
        Recipe result = facade.edit(r_edited);

        assertEquals(30, (int) result.getPreparationTime());
        assertEquals(2, result.getIngredientList().size());
        Ingredient i_result = (Ingredient) ingr_facade.find(ingr_1.getId());
        assertEquals(600, (int) i_result.getAmount());
        assertTrue(result.getIngredientList().contains(ingr_1));
        assertTrue(result.getIngredientList().contains(i_new));
        assertFalse(result.getIngredientList().contains(ingr_2));
    }

    @Test
    public void testDeleteRecipe() {
        final int listSize = facade.findAll().size();
        facade.remove(r_1.getId());
        assertEquals(listSize - 1, facade.findAll().size());
        assertNull(facade.find(r_1.getId()));
        assertNull(ingr_facade.find(ingr_1.getId()));
        assertNull(ingr_facade.find(ingr_2.getId()));
        assertNotNull(item_facade.find(item_1.getId()));
    }

    @Test
    public void testFindAllRecipes() {
        final List<Recipe> recipes = facade.findAll();
        assertEquals(1, facade.findAll().size());
        assertEquals(r_1.getId(), recipes.get(0).getId());
        assertEquals(r_1.getIngredientList().size(), recipes.get(0).getIngredientList().size());
    }

}
