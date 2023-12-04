package guru.springframework.converters;

import guru.springframework.commands.NotesCommand;
import guru.springframework.domain.Notes;
import guru.springframework.domain.Recipe;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NotesToNotesCommandTest {

    private final NotesToNotesCommand converter = new NotesToNotesCommand();
    private Recipe recipe;

    @Before
    public void setUp() throws Exception {
        recipe = new Recipe();
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new Notes()));
    }

    @Test
    public void convert() {
        //given
        Notes notes = new Notes();
        notes.setRecipeNotes("test");
        notes.setId(1L);
        notes.setRecipe(recipe);
        //when
        NotesCommand notesCommand = converter.convert(notes);
        //then
        assertNotNull(notesCommand);
        assertEquals(notes.getId(),notesCommand.getId());
        assertEquals(notes.getRecipeNotes(),notesCommand.getRecipeNotes());
    }
}