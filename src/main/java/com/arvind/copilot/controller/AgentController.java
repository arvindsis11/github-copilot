package com.arvind.copilot.controller;

import com.arvind.copilot.model.AgentRequest;
import com.arvind.copilot.model.AgentResponse;
import com.arvind.copilot.service.AgentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/agent")
public class AgentController {

    private final AgentService agentService;

    public AgentController(AgentService agentService) {
        this.agentService = agentService;
    }

    @PostMapping("/ask")
    public AgentResponse ask(@RequestBody AgentRequest request) {
        String reply = agentService.process(request.getMessage());
        return new AgentResponse(reply);
    }
}
