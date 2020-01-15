package dto;

import entities.*;
import java.util.List;

/**
 *
 * @author Simon Norup
 */
public class WeekMenuPlanDTO {

    private Long id;
    private int weekNo;
    private int yearNo;
    private List<RecipeDTO> recipeList;

    public WeekMenuPlanDTO(WeekMenuPlan plan) {
        this.id = plan.getId();
        this.weekNo = plan.getWeekNo();
        this.yearNo = plan.getYearNo();
        for (Recipe r : plan.getRecipeList()) {
            this.recipeList.add(new RecipeDTO(r));
        }
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

    public List<RecipeDTO> getRecipeList() {
        return recipeList;
    }

    public void setRecipeList(List<RecipeDTO> recipeList) {
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
        if (!(object instanceof WeekMenuPlanDTO)) {
            return false;
        }
        WeekMenuPlanDTO other = (WeekMenuPlanDTO) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

}
