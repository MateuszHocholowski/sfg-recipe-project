package guru.springframework.recipeproject.domain;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private BigDecimal amount;
    @ManyToOne(fetch = FetchType.EAGER)
    private UnitOfMeasure unitofMeasure;
    @ManyToOne
    private Recipe recipe;

    public Ingredient() {
    }

    public Ingredient(String description, BigDecimal amount, UnitOfMeasure unitofMeasure, Recipe recipe) {
        this.description = description;
        this.amount = amount;
        this.unitofMeasure = unitofMeasure;
        this.recipe = recipe;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public UnitOfMeasure getUnitofMeasure() {
        return unitofMeasure;
    }

    public void setUnitOfMeasure(UnitOfMeasure unitofMeasure) {
        this.unitofMeasure = unitofMeasure;
    }
}
