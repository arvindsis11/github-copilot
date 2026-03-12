package com.arvind.copilot.service;

import org.springframework.stereotype.Service;

import com.arvind.copilot.llm.OpenAiClient;

@Service
public class CodeAssistantService {
    private final OpenAiClient openAiCLient;
    public CodeAssistantService(OpenAiClient openAiClient){
        this.openAiCLient=openAiClient;
    }
    public String process(String type,String code){
        String prompt=buildPrompt(type,code);
        return openAiCLient.askLLM(prompt);
    }
    private String buildPrompt(String type, String code){
        return switch(type){
            case "explain" -> "Explain this code clearly:\n\n" + code;
            case "generate" -> "Generate clean Java code for:\n\n" + code;
            case "detect-bugs" -> "Find bugs in this code and explain each one:\n\n" + code;
            default -> throw new IllegalArgumentException("Unknown type: " + type);
        };
    }

}
