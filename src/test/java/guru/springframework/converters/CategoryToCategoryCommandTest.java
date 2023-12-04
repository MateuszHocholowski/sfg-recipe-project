package guru.springframework.converters;

import guru.springframework.commands.CategoryCommand;
import guru.springframework.domain.Category;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryToCategoryCommandTest {

    private final CategoryToCategoryCommand converter = new CategoryToCategoryCommand();
    @Test
    public void testNull() {
        assertNull(converter.convert(null));
    }
    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new Category()));
    }
    @Test
    public void convert() {
        //given
        Category category = new Category();
        category.setDescription("test");
        category.setId(1L);
        //when
        CategoryCommand categoryCommand = converter.convert(category);
        //then
        assertNotNull(categoryCommand);
        assertEquals(category.getDescription(),categoryCommand.getDescription());
        assertEquals(category.getId(),categoryCommand.getId());
    }
}