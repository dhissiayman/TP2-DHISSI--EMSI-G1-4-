package ma.emsi.dhissiayman.tp2;


import dev.langchain4j.model.chat.ChatModel;
// Assurez-vous que l'import correspond bien à votre module LangChain4j :
import dev.langchain4j.model.googleai.GoogleAiGeminiChatModel;


public class Test1 {

    public static void main(String[] args) {
        // Renseignez la clé via variable d'environnement GEMINI_KEY
        ChatModel model = GoogleAiGeminiChatModel.builder()
                .apiKey(System.getenv("GEMINI_KEY"))
                .modelName("gemini-2.5-flash")
                .temperature(0.7)
                .logRequests(true)
                .build();

        String question = "Donne-moi des informations sur casablanca";


        String response = model.chat(question);

        System.out.println("Question  : " + question);
        System.out.println("Réponse LLM : " + response);
    }
}
