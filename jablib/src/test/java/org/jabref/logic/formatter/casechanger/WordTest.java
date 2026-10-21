package org.jabref.logic.formatter.casechanger;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class WordTest {
    
    @Test
    void asIsSmallerWord() {
        assertTrue(Word.isSmallerWord("as"));
        assertTrue(Word.isSmallerWord("AS"));
        assertTrue(Word.isSmallerWord("As"));
        assertTrue(Word.isSmallerWord("aS"));
    }


}
