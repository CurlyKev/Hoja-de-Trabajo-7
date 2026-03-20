import java.io.*;

/**
 * Loads a dictionary from a file into a BST and translates text files
 * from English to Spanish word by word.
 */
public class Translator {

    private BinarySearchTree bst;

    public Translator() {
        bst = new BinarySearchTree();
    }

    /**
     * Reads diccionario.txt and inserts each (english, spanish) pair into the BST.
     * Lines must follow the format: (word, translation)
     *
     * @param filename path to the dictionary file
     */
    public void loadDictionary(String filename) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;

        while ((line = br.readLine()) != null) {
            line = line.trim();

            if (line.startsWith("(") && line.contains(",") && line.endsWith(")")) {
                // Remove surrounding parentheses
                line = line.substring(1, line.length() - 1);

                // Split on first comma only (in case value contains a comma)
                int commaIndex = line.indexOf(",");
                if (commaIndex == -1) continue;

                String english = line.substring(0, commaIndex).trim().toLowerCase();
                String spanish  = line.substring(commaIndex + 1).trim();

                if (!english.isEmpty() && !spanish.isEmpty()) {
                    bst.insert(english, spanish);
                }
            }
        }
        br.close();
    }

    /**
     * Translates each word in texto.txt to Spanish.
     * Words not found in the dictionary are wrapped in asterisks.
     * Punctuation attached to words is preserved in the output.
     *
     * @param filename path to the text file to translate
     * @return translated text as a single String
     */
    public String translateText(String filename) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        StringBuilder sb = new StringBuilder();
        String line;
        boolean firstLine = true;

        while ((line = br.readLine()) != null) {

            if (!firstLine) sb.append("\n");
            firstLine = false;

            if (line.trim().isEmpty()) {
                sb.append("");
                continue;
            }

            String[] words = line.split(" ");
            StringBuilder lineSb = new StringBuilder();

            for (String word : words) {
                if (word.isEmpty()) continue;

                // Separate leading and trailing punctuation from the word
                String leading  = leadingPunctuation(word);
                String trailing = trailingPunctuation(word);
                String clean    = word.substring(leading.length(),
                                                  word.length() - trailing.length())
                                      .toLowerCase();

                if (clean.isEmpty()) {
                    lineSb.append(word).append(" ");
                    continue;
                }

                String translated = bst.search(clean);

                if (translated != null) {
                    lineSb.append(leading).append(translated).append(trailing).append(" ");
                } else {
                    // Word not found: wrap original (with punctuation) in asterisks
                    lineSb.append("*").append(leading).append(clean).append(trailing)
                          .append("* ");
                }
            }

            sb.append(lineSb.toString().trim());
        }

        br.close();
        return sb.toString();
    }

    /** Extracts leading punctuation/symbols from a word token. */
    private String leadingPunctuation(String word) {
        int i = 0;
        while (i < word.length() && !Character.isLetter(word.charAt(i))) i++;
        return word.substring(0, i);
    }

    /** Extracts trailing punctuation/symbols from a word token. */
    private String trailingPunctuation(String word) {
        int i = word.length() - 1;
        while (i >= 0 && !Character.isLetter(word.charAt(i))) i--;
        return word.substring(i + 1);
    }

    /** Prints the full dictionary in alphabetical order (in-order BST traversal). */
    public void showDictionary() {
        bst.inorder();
    }
}