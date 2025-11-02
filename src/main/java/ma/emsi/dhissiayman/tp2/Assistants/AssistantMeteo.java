package ma.emsi.dhissiayman.tp2.Assistants;



public interface AssistantMeteo {
    // Une simple méthode "chat" : le LLM décidera s’il doit appeler l’outil.
    String chat(String message);
}
