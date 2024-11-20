package com.globalnest.be.translation.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.globalnest.be.translation.dto.request.ChatRequest;
import com.globalnest.be.translation.dto.response.ChatResponse;
import com.globalnest.be.translation.util.TranslationClient;
import com.globalnest.be.user.domain.type.Language;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class TranslationService {

    private final TranslationClient translationClient;
    private final ObjectMapper objectMapper;

    @Value("${gpt.key}")
    private String KEY;

    // 메서드에서 어떤 형태의 데이터를 처리하고 GPT API 호출
    public JSONObject getChatResponse(Object inputData, Language language) {
        // API 키 설정
        String apiKey = "Bearer " + KEY;

        // 입력 데이터를 JSON 문자열로 변환
        String translatedJson = JsonParsing(inputData);

        // ChatRequest 객체 생성
        ChatRequest chatRequest = ChatRequest.fromCustomRequest(translatedJson, language);

        // GPT API 호출
        ChatResponse chatResponse = translationClient.createChatCompletion(apiKey, chatRequest);

        // GPT의 응답에서 메시지를 추출하고 JSON 형태로 반환
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // JSON 문자열을 객체로 변환
            return new JSONObject(objectMapper.readValue(chatResponse.choices()[0].message().content(), Map.class));
        } catch (Exception e) {
            e.printStackTrace();
            return new JSONObject();
        }
    }

    // 입력 데이터를 JSON 문자열로 변환하는 메서드
    private String JsonParsing(Object inputData) {
        try {
            return objectMapper.writeValueAsString(inputData); // inputData를 JSON으로 변환
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "{}";
        }
    }

}
