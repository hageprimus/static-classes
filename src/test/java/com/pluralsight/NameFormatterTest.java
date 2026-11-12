package com.pluralsight;

import static org.junit.jupiter.api.Assertions.*;

class NameFormatterTest {

    @org.junit.jupiter.api.Test
    void NameFormatterTest_formatSmall() {
        String firstName = "Hage";
        String lastName = "Primus";
        String expected = "Primus, Hage";

        String test = NameFormatter.format(firstName, lastName);

        assertEquals(expected, test);
    }

    @org.junit.jupiter.api.Test
    void NameFormatterTest_formatExplicit() {
        String prefix = "Mr.";
        String firstName = "Hage";
        String middleName = "Vincent";
        String lastName = "Primus";
        String suffix = "PhD";
        String expected = "Primus, Mr. Hage Vincent, PhD";

        String test = NameFormatter.format(prefix, firstName, middleName, lastName, suffix);

        assertEquals(expected, test);
    }

    @org.junit.jupiter.api.Test
    void NameFormatterTest_formatParse() {
        String fullName1 = "Hage Primus";
        String expected1 = "Primus, Hage";

        String fullName2 = "Mr. Hage Vincent Richard Primus, PhD";
        String expected2 = "Primus, Mr. Hage Vincent Richard, PhD";

        String fullName3 = "Mr. Hage Vincent Primus";
        String expected3 = "Primus, Mr. Hage Vincent";

        String test1 = NameFormatter.format(fullName1);
        String test2 = NameFormatter.format(fullName2);
        String test3 = NameFormatter.format(fullName3);

        assertEquals(expected1, test1);
        assertEquals(expected2, test2);
        assertEquals(expected3, test3);
    }
}