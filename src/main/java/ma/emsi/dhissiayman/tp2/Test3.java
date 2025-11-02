package ma.emsi.dhissiayman.tp2;



import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.googleai.GoogleAiEmbeddingModel;
import dev.langchain4j.store.embedding.CosineSimilarity;

import java.time.Duration;
import java.util.Arrays;

public class Test3 {

    public static void main(String[] args) {

        String apiKey = System.getenv("GEMINI_KEY");
        if (apiKey == null || apiKey.isBlank()) {
            System.err.println("Erreur : définissez la variable d'environnement GEMINI_KEY avec votre clé Gemini.");
            return;
        }

        String embeddingModelName = "gemini-embedding-001";

        // ✅ Création du modèle d’embeddings
        EmbeddingModel embeddingModel = GoogleAiEmbeddingModel.builder()
                .apiKey(apiKey)
                .modelName(embeddingModelName)
                .timeout(Duration.ofSeconds(3))
                .logRequests(true)
                .build();

        // 🧩 Phrases à comparer
        String phrase1 = "Je bois du café le matin.";
        String phrase2 = "Le matin, j'aime prendre une tasse de café.";

        String phrase3 = "J'adore faire du sport chaque week-end.";
        String phrase4 = "Les chats dorment presque toute la journée.";



        Embedding emb1 = embeddingModel.embed(phrase1).content();
        Embedding emb2 = embeddingModel.embed(phrase2).content();
        Embedding emb3 = embeddingModel.embed(phrase3).content();
        Embedding emb4 = embeddingModel.embed(phrase4).content();


        double sim1 = CosineSimilarity.between(emb1, emb2);
        double sim2 = CosineSimilarity.between(emb3, emb4);


        System.out.println("=== Paires similaires ===");
        System.out.printf("\"%s\" ↔ \"%s\" → Similarité = %.4f%n%n", phrase1, phrase2, sim1);

        System.out.println("=== Paires différentes ===");
        System.out.printf("\"%s\" ↔ \"%s\" → Similarité = %.4f%n%n", phrase3, phrase4, sim2);


        System.out.println("Taille du vecteur : " + emb1.vector().length);
        System.out.println("Aperçu : " + Arrays.toString(Arrays.copyOf(emb1.vector(), 8)));
    }


}
