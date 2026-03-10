package com.arvind.copilot.service;

import com.arvind.copilot.llm.OpenAiClient;
import org.springframework.stereotype.Service;

@Service
public class AgentService {

    private final OpenAiClient openAiClient;

    public AgentService(OpenAiClient openAiClient) {
        this.openAiClient = openAiClient;
    }

    public String process(String message) {

        // Future: Add reasoning, tool calling, memory here
        return openAiClient.askLLM(message);
    }
}
