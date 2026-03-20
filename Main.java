/**
 * Entry point for the English–Spanish dictionary program.
 * Loads a BST-based dictionary and translates a text file.
 */
public class Main {

    public static void main(String[] args) {
        try {
            Translator translator = new Translator();

            // Load dictionary into BST
            translator.loadDictionary("diccionario.txt");

            // Print all entries sorted alphabetically (in-order traversal)
            System.out.println("=== Diccionario ordenado (in-order) ===");
            translator.showDictionary();

            // Translate texto.txt
            System.out.println("\n=== Traducción de texto.txt ===");
            String result = translator.translateText("texto.txt");
            System.out.println(result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}