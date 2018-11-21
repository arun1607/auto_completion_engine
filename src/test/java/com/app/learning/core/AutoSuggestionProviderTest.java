package com.app.learning.core;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class AutoSuggestionProviderTest {

    private AutoSuggestionProvider autoSuggestionProvider = null;

    @Before
    public void setup() {
        autoSuggestionProvider = new AutoSuggestionProvider();
        autoSuggestionProvider.addWord("mister", 10);
        autoSuggestionProvider.addWord("missed", 7);
        autoSuggestionProvider.addWord("misty", 9);
        autoSuggestionProvider.addWord("mistaken", 11);
    }

    @Test
    public void getSuggestions_1() {
        final List<String> suggestions = autoSuggestionProvider.getSuggestions("m");

        String[] expected = new String[]{"mistaken", "mister", "misty", "missed"};

        int counter = 0;

        boolean matched = true;
        while (counter < suggestions.size()) {
            if (!expected[counter].equals(suggestions.get(counter))) {
                matched = false;
                break;
            }
            counter++;
        }

        Assert.assertTrue(matched);
    }

    @Test
    public void getSuggestions_2() {
        final List<String> suggestions = autoSuggestionProvider.getSuggestions("mist");

        String[] expected = new String[]{"mistaken", "mister", "misty"};

        int counter = 0;

        boolean matched = true;
        while (counter < suggestions.size()) {
            if (!expected[counter].equals(suggestions.get(counter))) {
                matched = false;
                break;
            }
            counter++;
        }

        Assert.assertTrue(matched);
    }

    @Test
    public void contains() {
        Assert.assertTrue(autoSuggestionProvider.contains("mister"));
    }
}