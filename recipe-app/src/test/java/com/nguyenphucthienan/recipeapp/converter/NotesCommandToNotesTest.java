package com.nguyenphucthienan.recipeapp.converter;

import com.nguyenphucthienan.recipeapp.command.NotesCommand;
import com.nguyenphucthienan.recipeapp.domain.Notes;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NotesCommandToNotesTest {

    public static final Long ID = 1L;
    public static final String RECIPE_NOTES = "Notes";

    NotesCommandToNotes notesCommandToNotes;

    @Before
    public void setUp() {
        notesCommandToNotes = new NotesCommandToNotes();
    }

    @Test
    public void testNullObject() {
        assertNull(notesCommandToNotes.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(notesCommandToNotes.convert(new NotesCommand()));
    }

    @Test
    public void convert() {
        // Given
        NotesCommand notesCommand = new NotesCommand();
        notesCommand.setId(ID);
        notesCommand.setRecipeNotes(RECIPE_NOTES);

        // When
        Notes notes = notesCommandToNotes.convert(notesCommand);

        // Then
        assertNotNull(notes);
        assertEquals(ID, notes.getId());
        assertEquals(RECIPE_NOTES, notes.getRecipeNotes());
    }
}
