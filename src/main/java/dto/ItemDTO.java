package dto;

import entities.Item;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 *
 * @author Simon Norup
 */
public class ItemDTO {

    @Schema(required = true)
    private Long id;
    @Schema(required = true, example = "T-Bone Steak")
    private String name;
    @Schema(required = true, example = "500", description = "price pr kg measured in DKK")
    private double pricePrKg;
    @Schema(required = true, example = "2000", description = "grams of item currently available in storage")
    private int storageAmount;

    public ItemDTO(Item i) {
        this.id = i.getId();
        this.name = i.getName();
        this.pricePrKg = i.getPricePrKg();
        this.storageAmount = i.getStorageAmount();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPricePrKg() {
        return pricePrKg;
    }

    public void setPricePrKg(double pricePrKg) {
        this.pricePrKg = pricePrKg;
    }

    public int getStorageAmount() {
        return storageAmount;
    }

    public void setStorageAmount(int storageAmount) {
        this.storageAmount = storageAmount;
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
        if (!(object instanceof ItemDTO)) {
            return false;
        }
        ItemDTO other = (ItemDTO) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

}
