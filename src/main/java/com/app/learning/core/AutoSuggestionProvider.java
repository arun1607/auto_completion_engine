package com.app.learning.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AutoSuggestionProvider {
    private final Trie trie;

    public AutoSuggestionProvider() {
        this.trie = new Trie();
    }

    public void addWord(String str, final int weight) {
        trie.addWord(str, weight);
    }

    public List<String> getSuggestions(final String searchStr) {
        Collection<String> suggestions = trie.getSuggestions(searchStr);
        return new ArrayList<>(suggestions);
    }

    public boolean contains(final String searchStr) {
        return trie.contains(searchStr);
    }
}
