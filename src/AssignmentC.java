import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class AssignmentC {

    // URL of the word list
    private static final String WORD_LIST_URL = "https://raw.githubusercontent.com/dwyl/english-words/master/words_alpha.txt";

    public static void main(String[] args) {
        try {
            // Initialize a map to hold the statistics for each letter
            Map<Character, LetterStats> statsMap = new HashMap<>();
            for (char c = 'a'; c <= 'z'; c++) {
                statsMap.put(c, new LetterStats());
            }

            // Read the word list from the URL
            URL url = new URL(WORD_LIST_URL);
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()))) {
                String word;
                while ((word = reader.readLine()) != null) {
                    if (word.length() > 0) {
                        char firstLetter = word.charAt(0);
                        char lastLetter = word.charAt(word.length() - 1);
                        if (Character.isAlphabetic(firstLetter) && Character.isAlphabetic(lastLetter)) {
                            LetterStats stats = statsMap.get(Character.toLowerCase(firstLetter));
                            if (stats != null) {
                                stats.processWord(word);
                            }
                        }
                    }
                }
            }

            // Display the statistics for each letter
            for (char c = 'a'; c <= 'z'; c++) {
                LetterStats stats = statsMap.get(c);
                System.out.println("Letter: " + c);
                System.out.println("Number of words that begin with the letter: " + stats.getWordCount());
                System.out.println("Number of words that end with the letter: " + stats.getEndCount());
                System.out.println("The shortest word that begins with the letter: " + stats.getShortestWord());
                System.out.println("The longest word that begins with the letter: " + stats.getLongestWord());
                System.out.println("The average number of characters in words that begin with the letter: " + stats.getAverageLength());
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class LetterStats {
    private int wordCount;
    private int endCount;
    private int totalLength;
    private String shortestWord;
    private String longestWord;

    void processWord(String word) {
        wordCount++;
        totalLength += word.length();
        if (shortestWord == null || word.length() < shortestWord.length()) {
            shortestWord = word;
        }
        if (longestWord == null || word.length() > longestWord.length()) {
            longestWord = word;
        }
        char lastLetter = word.charAt(word.length() - 1);
        if (lastLetter == shortestWord.charAt(0)) {
            endCount++;
        }
    }

    int getWordCount() {
        return wordCount;
    }

    int getEndCount() {
        return endCount;
    }

    String getShortestWord() {
        return shortestWord == null ? "N/A" : shortestWord;
    }

    String getLongestWord() {
        return longestWord == null ? "N/A" : longestWord;
    }

    double getAverageLength() {
        return wordCount == 0 ? 0 : (double) totalLength / wordCount;
    }
}
