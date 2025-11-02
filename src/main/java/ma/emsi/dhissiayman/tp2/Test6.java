package ma.emsi.dhissiayman.tp2;

import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.googleai.GoogleAiGeminiChatModel;
import dev.langchain4j.service.AiServices;
import ma.emsi.dhissiayman.tp2.Assistants.AssistantMeteo;
import ma.emsi.dhissiayman.tp2.Tool.MeteoTool;

import java.util.Scanner;

public class Test6 {

    public static void main(String[] args) {
        // Récupère la clé API Gemini (ex: export GEMINI_KEY="xxxxx")
        String llmKey = System.getenv("GEMINI_KEY");
        if (llmKey == null || llmKey.isEmpty()) {
            System.err.println("Erreur: la variable d'environnement GEMINI_KEY est vide.");
            System.err.println("Exemple bash: export GEMINI_KEY=\"votre_cle\"");
            return;
        }

        // Modèle LLM (identique à ton Test4)
        ChatModel model = GoogleAiGeminiChatModel.builder()
                .apiKey(llmKey)
                .modelName("gemini-2.5-flash")
                .temperature(0.3)
                .logRequests(true)
                .build();

        // Instancie l’outil meteo
        MeteoTool meteoTool = new MeteoTool();

        // Crée l’assistant et lui donne accès à l’outil
        AssistantMeteo assistant =
                AiServices.builder(AssistantMeteo.class)
                        .chatModel(model)
                        .chatMemory(MessageWindowChatMemory.withMaxMessages(10))
                        .tools(meteoTool) // <<< essentiel : on fournit l'outil
                        .build();

        // --- Démonstrations ---

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("==================================================");
                System.out.println("Posez votre question : ");
                String question = scanner.nextLine();
                if (question.isBlank()) {
                    continue;
                }
                System.out.println("==================================================");
                if ("fin".equalsIgnoreCase(question)) {
                    break;
                }
                String reponse = assistant.chat(question);
                System.out.println("Assistant : " + reponse);
                System.out.println("==================================================");
            }
        }
    }
}