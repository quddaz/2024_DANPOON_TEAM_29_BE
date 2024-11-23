package com.globalnest.be.global.util.translation.dto.request;

import com.globalnest.be.global.util.translation.dto.Message;
import com.globalnest.be.user.domain.type.Language;
import lombok.Builder;

@Builder
public record ChatRequest(
        String model,
        Message[] messages
) {

    private static final String COMMON_SYSTEM_MESSAGE = """
            {
              "system": "당신은 스마트하고 유용한 번역 도우미입니다. 사용자의 요청을 처리하기 위해 다음 단계를 주의 깊게 따라야 합니다:
                1. 첫 번째 'user'는 번역할 내용을 제공하며, 제목, 내용, 댓글 등 다양한 필드를 포함할 수 있습니다. 데이터 구조는 다를 수 있습니다.
                2. 두 번째 'user'는 내용이 번역되어야 할 언어를 지정합니다.
                3. 번역된 내용은 첫 번째 사용자가 제공한 구조와 정확히 동일한 구조로 반환해야 합니다.
                   - 지정된 언어에 따라 내용을 번역합니다.
                   - 구조를 수정하거나 필드를 제거하지 않습니다.
                4. "name", "%nickname%"은 번역 대상이 아닙니다. 이름은 각자의 나라를 대표하기 때문입니다.
                5. 응답은 제공된 데이터 구조와 정확히 일치해야 하며, 언어는 번역을 위한 기준이 되어야 합니다.
                6. 날짜의 경우 "yyyy-mm-dd"로 들어오면 "yyyy-mm-dd"로 포멧하세요. 또한 yyyy-MM-dd HH:mm:ss"로 들어오면 yyyy-MM-dd HH:mm:ss로 포멧하세요 배열로 리턴하지 말고 문자열 형태로 리턴해주세요.
                7. 정렬조건을 제외한 petitionType도 꼭 사용자 언어에 맞게 번역해주세요.
              "steps": [
                {
                  "step": "번역",
                  "instruction": "제공된 내용을 기반으로 유연하게 번역을 제공하고, 첫 번째 사용자가 제공한 구조에 맞게 결과를 반환하십시오."
                }
              ]
            }
            """;

    public static ChatRequest fromCustomRequest(String requestContent, Language language) {
        return ChatRequest.builder()
                .model("gpt-4-turbo")
                .messages(new Message[]{
                        new Message("system", COMMON_SYSTEM_MESSAGE),
                        new Message("user", requestContent),
                        new Message("user", language.toString())
                })
                .build();
    }
}
