package ma.emsi.dhissiayman.tp2.Assistants;


public interface Assistant {
    // Prend un message de l'utilisateur et retourne une réponse du LLM.
    String chat(String userMessage);
}