package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Simon Norup
 */
@Entity
@Table(name = "Recipe")
@NamedQueries({
    @NamedQuery(name = "Recipe.findAll", query = "SELECT r FROM Recipe r"),
    @NamedQuery(name = "Recipe.deleteAll", query = "DELETE from Recipe")})
public class Recipe implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "preparationTime")
    private Integer preparationTime;
    @Lob
    @Size(max = 65535)
    @Column(name = "directions")
    private String directions;
    @ManyToMany(mappedBy = "recipeList", fetch = FetchType.LAZY)
    private List<WeekMenuPlan> weekMenuPlanList;
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipeId", fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id", referencedColumnName = "id")
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Ingredient> ingredientList;

    public Recipe() {
    }

    public Recipe(Long id) {
        this.id = id;
    }

    public Recipe(String title, Integer preparationTime, String directions) {
        this.title = title;
        this.preparationTime = preparationTime;
        this.directions = directions;
        this.ingredientList = new ArrayList<>();
        this.weekMenuPlanList = new ArrayList<>();
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

    public List<WeekMenuPlan> getWeekMenuPlanList() {
        return weekMenuPlanList;
    }

    public void setWeekMenuPlanList(List<WeekMenuPlan> weekMenuPlanList) {
        this.weekMenuPlanList = weekMenuPlanList;
    }

    public void addWeekMenuPlan(WeekMenuPlan weekMenuPlan) {
        this.weekMenuPlanList.add(weekMenuPlan);
    }

    public List<Ingredient> getIngredientList() {
        return ingredientList;
    }

    public void setIngredientList(List<Ingredient> ingredientList) {
        this.ingredientList = ingredientList;
    }

    public void addIngredient(Ingredient ingredient) {
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
        if (!(object instanceof Recipe)) {
            return false;
        }
        Recipe other = (Recipe) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Recipe[ id=" + id + " ]";
    }

}
