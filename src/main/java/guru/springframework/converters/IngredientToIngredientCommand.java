package guru.springframework.converters;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.domain.Ingredient;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class IngredientToIngredientCommand implements Converter<Ingredient, IngredientCommand> {

    private final UnitOfMeasureToUnitOfMeasureCommand uomConverter;

    public IngredientToIngredientCommand(UnitOfMeasureToUnitOfMeasureCommand uomConverter) {
        this.uomConverter = uomConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public IngredientCommand convert(Ingredient source) {
        if (source == null) {
            return null;
        } else {
            IngredientCommand ingredientCommand = new IngredientCommand();
            ingredientCommand.setUnitOfMeasureCommand(uomConverter.convert(source.getUnitOfMeasure()));
            ingredientCommand.setDescription(source.getDescription());
            ingredientCommand.setAmount(source.getAmount());
            ingredientCommand.setId(source.getId());
            return ingredientCommand;
        }
    }
}
