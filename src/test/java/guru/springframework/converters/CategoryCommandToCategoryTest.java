package guru.springframework.converters;

import guru.springframework.commands.CategoryCommand;
import guru.springframework.domain.Category;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryCommandToCategoryTest {

    private CategoryCommandToCategory converter = new CategoryCommandToCategory();

    @Test
    public void testNull() {
        assertNull(converter.convert(null));
    }
    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new CategoryCommand()));
    }
    @Test
    public void convert() {
        //given
        CategoryCommand categoryCommand = new CategoryCommand();
        categoryCommand.setDescription("test");
        categoryCommand.setId(1L);
        //when
        Category convertedCategory = converter.convert(categoryCommand);
        //then
        assertNotNull(convertedCategory);
        assertEquals(categoryCommand.getDescription(),convertedCategory.getDescription());
        assertEquals(categoryCommand.getId(),convertedCategory.getId());
    }
}