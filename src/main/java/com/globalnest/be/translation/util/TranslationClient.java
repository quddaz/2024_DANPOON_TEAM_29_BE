package com.globalnest.be.translation.util;

import com.globalnest.be.translation.dto.request.ChatRequest;
import com.globalnest.be.translation.dto.response.ChatResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "chatgpt", url = "https://api.openai.com/v1")
public interface TranslationClient {

    @PostMapping("/chat/completions")
    ChatResponse createChatCompletion(
            @RequestHeader("Authorization") String authorization,
            @RequestBody ChatRequest chatRequest);
}