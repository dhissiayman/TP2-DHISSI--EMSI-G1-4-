package ma.emsi.dhissiayman.tp2;


import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.googleai.GoogleAiGeminiChatModel;
import dev.langchain4j.model.input.Prompt;
import dev.langchain4j.model.input.PromptTemplate;


public class Test2 {

    public static void main(String[] args) {

        // Création du modèle Gemini
        ChatModel model = GoogleAiGeminiChatModel.builder()
                .apiKey(System.getenv("GEMINI_KEY"))
                .modelName("gemini-2.5-flash")
                .temperature(0.7)
                .logRequests(true)
                .build();

        PromptTemplate template = PromptTemplate.from(
                "Traduis les textes suivants en anglais :\n1. {{it}}"
        );
        // Exemple de texte à traduire
        String texte = "Bonjour tout le monde, je suis content d'apprendre l'IA.";

        Prompt prompt = template.apply(
                 "Bonjour tout le monde, je suis content d'apprendre l'IA."
        );

        // Envoi du prompt au modèle
        String reponse = model.chat(prompt.text());

        System.out.println("Texte original : " + texte);
        System.out.println("Traduction     : " + reponse);
    }
}
