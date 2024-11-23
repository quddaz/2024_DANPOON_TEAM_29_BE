package com.globalnest.be.global.util.translation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.globalnest.be.global.util.translation.dto.response.ChatResponse;
import com.globalnest.be.global.util.translation.client.TranslationClient;
import com.globalnest.be.global.util.translation.dto.request.ChatRequest;
import com.globalnest.be.user.domain.type.Language;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Map;

@RequiredArgsConstructor
@Component
public class TranslationConverter {

    private final TranslationClient translationClient;
    private final ObjectMapper objectMapper;

    @Value("${gpt.api-key}")
    private String KEY;

    public JSONObject getChatResponse(Object inputData, Language language) {
        String apiKey = "Bearer " + KEY;
        String translatedJson = JsonParsing(inputData);

        ChatRequest chatRequest = ChatRequest.fromCustomRequest(translatedJson, language);
        ChatResponse chatResponse = translationClient.createChatCompletion(apiKey, chatRequest);
        try {
            return new JSONObject(objectMapper.readValue(chatResponse.choices()[0].message().content(), Map.class));
        } catch (Exception e) {
            return new JSONObject();
        }
    }

    private String JsonParsing(Object inputData) {
        try {
            return objectMapper.writeValueAsString(inputData);
        } catch (JsonProcessingException e) {
            return "{}";
        }
    }
}
