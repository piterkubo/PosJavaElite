package dev.ia;


// Esta anotação instrui o Quarkus a gerar uma implementaçaão desta interface que se conecta

import io.quarkiverse.langchain4j.RegisterAiService;

@RegisterAiService
public interface TavelAgentAssistant {

    /**
     * O método 'chat' recebe a mensagem do usuário e retorna a resposta do LLM.
     * @param userMessage A mensagem do usuário.
     * @return A resposta gerada pelo modelo de linguagem.
     */
    String chat(String userMessage);


}
