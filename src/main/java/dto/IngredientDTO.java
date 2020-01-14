package dto;

import entities.Ingredient;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 *
 * @author Simon Norup
 */
public class IngredientDTO {

    @Schema(required = true)
    private Long id;
    @Schema(required = true, example = "400", description="grams of this ingredient to use in the given recipe, for 100 people")
    private int amount;
    private ItemDTO item;

    public IngredientDTO(Ingredient i) {
        this.item = new ItemDTO(i.getItem());
        this.amount = i.getAmount();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public ItemDTO getItem() {
        return item;
    }

    public void setItem(ItemDTO item) {
        this.item = item;
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
        if (!(object instanceof IngredientDTO)) {
            return false;
        }
        IngredientDTO other = (IngredientDTO) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

}
