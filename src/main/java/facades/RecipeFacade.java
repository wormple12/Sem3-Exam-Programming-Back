package facades;

import entities.Recipe;
import javax.persistence.EntityManagerFactory;

/**
 * @author lam@cphbusiness.dk
 */
public class RecipeFacade extends AbstractFacade<Recipe> {

    private static EntityManagerFactory emf;
    private static RecipeFacade instance;

    public RecipeFacade() {
        super(Recipe.class, emf);
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
