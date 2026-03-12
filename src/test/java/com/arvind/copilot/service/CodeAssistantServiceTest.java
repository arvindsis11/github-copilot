package com.arvind.copilot.service;
import com.arvind.copilot.llm.OpenAiClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CodeAssistantServiceTest {

    @Mock
    private OpenAiClient openAiClient;

    @InjectMocks
    private CodeAssistantService codeAssistantService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldExplainCode() {
        when(openAiClient.askLLM(contains("Explain"))).thenReturn("This method adds two numbers");

        String result = codeAssistantService.process("explain", "public int add(int a, int b) { return a + b; }");

        assertNotNull(result);
        assertEquals("This method adds two numbers", result);
        verify(openAiClient, times(1)).askLLM(anyString());
    }

    @Test
    void shouldGenerateCode() {
        when(openAiClient.askLLM(contains("Generate"))).thenReturn("public String reverse(String s) { return new StringBuilder(s).reverse().toString(); }");

        String result = codeAssistantService.process("generate", "a method to reverse a string");

        assertNotNull(result);
        verify(openAiClient, times(1)).askLLM(anyString());
    }

    @Test
    void shouldDetectBugs() {
        when(openAiClient.askLLM(contains("bugs"))).thenReturn("Bug: division by zero when b is 0");

        String result = codeAssistantService.process("detect-bugs", "public int divide(int a, int b) { return a / b; }");

        assertNotNull(result);
        verify(openAiClient, times(1)).askLLM(anyString());
    }

    @Test
    void shouldThrowExceptionForUnknownType() {
        assertThrows(IllegalArgumentException.class, () ->
            codeAssistantService.process("unknown-type", "some code")
        );
    }
}