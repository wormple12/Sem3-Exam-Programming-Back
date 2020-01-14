package facades;

import entities.Ingredient;
import entities.Item;
import entities.Recipe;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 * @author lam@cphbusiness.dk
 */
public class RecipeFacade extends AbstractFacade<Recipe> {

    private static EntityManagerFactory emf;
    private static RecipeFacade instance;

    public RecipeFacade() {
        super(Recipe.class, emf);
//        ingredientFacade = new AbstractFacade(Ingredient.class, emf) {};
//        itemFacade = new AbstractFacade(Item.class, emf) {};
    }

    /**
     *
     * @param _emf
     * @return the instance of this facade.
     */
    public static RecipeFacade getRecipeFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new RecipeFacade();
        }
        return instance;
    }

}
