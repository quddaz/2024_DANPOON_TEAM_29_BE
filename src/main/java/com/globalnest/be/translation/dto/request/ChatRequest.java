package com.globalnest.be.translation.dto.request;

import com.globalnest.be.translation.dto.Message;
import lombok.Builder;

@Builder
public record ChatRequest(
    String model,
    Message[] messages
) {

    private static final String COMMON_SYSTEM_MESSAGE = """
        {
          "system": "You are a smart and helpful translation assistant. When the user provides a title, content, comments, and the language used, you should return the title, content, and comments translated into the specified language in a JSON format. Please follow these steps carefully: 
                    The key point is that the response should match the exact data structure as shown, and the language should be considered. Do not translate the name, leave it as is.",
          "steps": [
            {
              "step": "Translation",
              "instruction": "Please provide a flexible translation based on the content."
            }
          ]
        }
        """;

    public static ChatRequest fromCustomRequest(String requestContent) {
        return ChatRequest.builder()
            .model("gpt-3.5-turbo")
            .messages(new Message[] {
                new Message("system", COMMON_SYSTEM_MESSAGE),
                new Message("user", requestContent)
            })
            .build();
    }
}
