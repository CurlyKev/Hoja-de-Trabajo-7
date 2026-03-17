import java.io.*;

public class Translator {

    private BinarySearchTree bst;

    public Translator() {
        bst = new BinarySearchTree();
    }

    public void loadDictionary(String filename) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;

        while ((line = br.readLine()) != null) {
            line = line.trim();

            if (line.startsWith("(") && line.contains(",")) {
                line = line.replace("(", "").replace(")", "");
                String[] parts = line.split(",");

                String english = parts[0].trim().toLowerCase();
                String spanish = parts[1].trim();

                bst.insert(english, spanish);
            }
        }
        br.close();
    }

    public String translateText(String filename) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        StringBuilder sb = new StringBuilder();
        String line;

        while ((line = br.readLine()) != null) {

            String[] words = line.split(" ");

            for (String w : words) {
                String clean = w.toLowerCase().replaceAll("[^a-zA-Z]", "");
                String translated = bst.search(clean);

                if (translated == null) sb.append("*").append(w).append("* ");
                else sb.append(translated).append(" ");
            }
        }

        br.close();
        return sb.toString().trim();
    }

    public void showDictionary() {
        bst.inorder();
    }
}
