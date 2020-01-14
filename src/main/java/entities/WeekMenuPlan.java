package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Simon Norup
 */
@Entity
@Table(name = "WeekMenuPlan")
@NamedQueries({
    @NamedQuery(name = "WeekMenuPlan.findAll", query = "SELECT w FROM WeekMenuPlan w"),
    @NamedQuery(name = "WeekMenuPlan.deleteAll", query = "DELETE from WeekMenuPlan")})
public class WeekMenuPlan implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "weekNo")
    private int weekNo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "yearNo")
    private int yearNo;
    @JoinTable(name = "Plan_Has_Recipe", joinColumns = {
        @JoinColumn(name = "plan_id", referencedColumnName = "id")}, inverseJoinColumns = {
        @JoinColumn(name = "recipe_id", referencedColumnName = "id")})
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Recipe> recipeList;

    public WeekMenuPlan() {
    }

    public WeekMenuPlan(Long id) {
        this.id = id;
    }

    public WeekMenuPlan(int weekNo, int year) {
        this.weekNo = weekNo;
        this.yearNo = year;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getWeekNo() {
        return weekNo;
    }

    public void setWeekNo(int weekNo) {
        this.weekNo = weekNo;
    }

    public int getYearNo() {
        return yearNo;
    }

    public void setYearNo(int yearNo) {
        this.yearNo = yearNo;
    }

    public List<Recipe> getRecipeList() {
        return recipeList;
    }

    public void setRecipeList(List<Recipe> recipeList) {
        this.recipeList = recipeList;
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
        if (!(object instanceof WeekMenuPlan)) {
            return false;
        }
        WeekMenuPlan other = (WeekMenuPlan) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.WeekMenuPlan[ id=" + id + " ]";
    }

}
