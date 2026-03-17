public class Main {

    public static void main(String[] args) {

        try {
            Translator translator = new Translator();

            translator.loadDictionary("diccionario.txt");

            System.out.println("Diccionario ordenado in-order:");
            translator.showDictionary();

            System.out.println("\nTraducción:");
            String result = translator.translateText("texto.txt");
            System.out.println(result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}