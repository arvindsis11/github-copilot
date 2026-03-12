package com.arvind.copilot.controller;

import com.arvind.copilot.model.AgentResponse;
import com.arvind.copilot.model.CodeRequest;
import com.arvind.copilot.service.CodeAssistantService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/agent")
public class CodeAssistantController {

    private final CodeAssistantService codeAssistantService;

    public CodeAssistantController(CodeAssistantService codeAssistantService) {
        this.codeAssistantService = codeAssistantService;
    }

    @PostMapping("/code")
    public AgentResponse code(@RequestBody CodeRequest request) {
        String reply = codeAssistantService.process(request.getType(), request.getCode());
        return new AgentResponse(reply);
    }
}