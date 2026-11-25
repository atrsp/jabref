package org.jabref.logic.xmp;

import java.util.List;

import org.jabref.model.entry.BibEntry;
import org.jabref.model.entry.field.StandardField;
import org.jabref.model.entry.types.StandardEntryType;

import org.apache.xmpbox.XMPMetadata;
import org.apache.xmpbox.schema.DublinCoreSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DublinCoreExtractorTest {

    private XmpPreferences xmpPreferences;

    @BeforeEach
    void setUp() {
        xmpPreferences = mock(XmpPreferences.class);
        when(xmpPreferences.getKeywordSeparator()).thenReturn(',');
        when(xmpPreferences.shouldUseXmpPrivacyFilter()).thenReturn(false);
    }

    @Test
    void semicolonSeparatedKeywordsAreWrittenAsSeparateSubjects() {
        BibEntry entry = new BibEntry(StandardEntryType.InProceedings)
                .withField(StandardField.KEYWORDS, "ADR; MADR; architecture decision records; architectural decision records; Nygard");
        DublinCoreSchema dcSchema = XMPMetadata.createXMPMetadata().createAndAddDublinCoreSchema();

        new DublinCoreExtractor(dcSchema, xmpPreferences, entry).fillDublinCoreSchema();

        assertEquals(List.of("ADR", "MADR", "architecture decision records", "architectural decision records", "Nygard"), dcSchema.getSubjects());
    }
}