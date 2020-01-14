package dto;

import entities.Ingredient;
import entities.Recipe;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Simon Norup
 */
public class RecipeDTO {

    @Schema(required = true)
    private Long id;
    @Schema(required = true, example = "Curry T-Bone")
    private String title;
    @Schema(required = true, example = "20", description = "estimated time to complete course measured in minutes")
    private Integer preparationTime;
    @Schema(required = true, example = "Directions for how to cook a Curry T-Bone")
    private String directions;
    private List<IngredientDTO> ingredientList = new ArrayList();

    public RecipeDTO(Recipe r) {
        this.id = r.getId();
        this.title = r.getTitle();
        this.preparationTime = r.getPreparationTime();
        this.directions = r.getDirections();
        for (Ingredient i : r.getIngredientList()) {
            this.ingredientList.add(new IngredientDTO(i));
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPreparationTime() {
        return preparationTime;
    }

    public void setPreparationTime(Integer preparationTime) {
        this.preparationTime = preparationTime;
    }

    public String getDirections() {
        return directions;
    }

    public void setDirections(String directions) {
        this.directions = directions;
    }

    public List<IngredientDTO> getIngredientList() {
        return ingredientList;
    }

    public void setIngredientList(List<IngredientDTO> ingredientList) {
        this.ingredientList = ingredientList;
    }
    
    public void addIngredient(IngredientDTO ingredient) {
        this.ingredientList.add(ingredient);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RecipeDTO)) {
            return false;
        }
        RecipeDTO other = (RecipeDTO) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

}
