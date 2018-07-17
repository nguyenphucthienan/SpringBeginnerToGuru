package com.nguyenphucthienan.recipeapp.converters;

import com.nguyenphucthienan.recipeapp.command.NotesCommand;
import com.nguyenphucthienan.recipeapp.domain.Notes;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NotesToNotesCommandTest {
    public static final Long ID = 1L;
    public static final String RECIPE_NOTES = "Notes";

    NotesToNotesCommand notesToNotesCommand;

    @Before
    public void setUp() {
        notesToNotesCommand = new NotesToNotesCommand();
    }

    @Test
    public void testNullObject() {
        assertNull(notesToNotesCommand.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(notesToNotesCommand.convert(new Notes()));
    }

    @Test
    public void convert() {
        // Given
        Notes notes = new Notes();
        notes.setId(ID);
        notes.setRecipeNotes(RECIPE_NOTES);

        // When
        NotesCommand notesCommand = notesToNotesCommand.convert(notes);

        // Then
        assertNotNull(notesCommand);
        assertEquals(ID, notesCommand.getId());
        assertEquals(RECIPE_NOTES, notesCommand.getRecipeNotes());
    }
}
