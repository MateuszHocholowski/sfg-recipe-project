package guru.springframework.converters;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.domain.Ingredient;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class IngredientCommandToIngredient implements Converter<IngredientCommand, Ingredient> {

    private final UnitOfMeasureCommandToUnitOfMeasure uomConverter;
    public IngredientCommandToIngredient(UnitOfMeasureCommandToUnitOfMeasure uomCommandToUom) {
        this.uomConverter = uomCommandToUom;
    }

    @Synchronized
    @Override
    public Ingredient convert(IngredientCommand source) {
        if (source == null) {
            return null;
        } else {
            Ingredient ingredient = new Ingredient();
            ingredient.setUnitOfMeasure(uomConverter.convert(source.getUnitOfMeasureCommand()));
            ingredient.setDescription(source.getDescription());
            ingredient.setAmount(source.getAmount());
            ingredient.setId(source.getId());
            return ingredient;
        }
    }
}
