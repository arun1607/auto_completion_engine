package com.app.learning.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {


        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        String inputCountStr = br.readLine();
        int inputCount = Integer.parseInt(inputCountStr);
        AutoSuggestionProvider autoSuggestionProvider = new AutoSuggestionProvider();
        for (int i = 0; i < inputCount; i++) {
            String input = br.readLine();
            String[] inputParts = input.split(" ");
            autoSuggestionProvider.addWord(inputParts[0], Integer.parseInt(inputParts[1]));
        }
        String queryWord = br.readLine();
        List<String> suggestions = autoSuggestionProvider.getSuggestions(queryWord);
        File outputFile = new File("output.txt");
        if (outputFile.exists()) {
            outputFile.delete();
        }
        outputFile.createNewFile();
        Files.write(outputFile.toPath(), suggestions, LinkOption.NOFOLLOW_LINKS);
    }
}
