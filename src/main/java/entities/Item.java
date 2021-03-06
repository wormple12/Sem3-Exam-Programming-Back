package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Simon Norup
 */
@Entity
@Table(name = "Item")
@NamedQueries({
    @NamedQuery(name = "Item.findAll", query = "SELECT i FROM Item i"),
    @NamedQuery(name = "Item.deleteAll", query = "DELETE from Item")})
public class Item implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Column(name = "price_pr_kg")
    private double pricePrKg;
    @Basic(optional = false)
    @NotNull
    @Column(name = "storage_amount")
    private int storageAmount;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "itemId", fetch = FetchType.LAZY)
//    private List<Ingredient> ingredientList;

    public Item() {
    }

    public Item(Long id) {
        this.id = id;
    }

    public Item(String name, double pricePrKg, int storageAmount) {
        this.name = name;
        this.pricePrKg = pricePrKg;
        this.storageAmount = storageAmount;
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

//    public List<Ingredient> getIngredientList() {
//        return ingredientList;
//    }
//
//    public void setIngredientList(List<Ingredient> ingredientList) {
//        this.ingredientList = ingredientList;
//    }
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Item)) {
            return false;
        }
        Item other = (Item) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Item[ id=" + id + " ]";
    }

}
