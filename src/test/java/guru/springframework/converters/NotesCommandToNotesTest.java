package guru.springframework.converters;

import guru.springframework.commands.NotesCommand;
import guru.springframework.domain.Notes;
import guru.springframework.domain.Recipe;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NotesCommandToNotesTest {

    private final NotesCommandToNotes converter = new NotesCommandToNotes();
    private Recipe recipe;
    @Before
    public void setUp() throws Exception {
        recipe = new Recipe();
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new NotesCommand()));
    }
    @Test
    public void convert() {
        //given
        NotesCommand notesCommand = new NotesCommand();
        notesCommand.setId(1L);
        notesCommand.setRecipeNotes("test");
        //when
        Notes notes = converter.convert(notesCommand);
        //then
        assertNotNull(notes);
        assertEquals(notes.getId(),notesCommand.getId());
        assertEquals(notes.getRecipeNotes(),notesCommand.getRecipeNotes());
    }
}