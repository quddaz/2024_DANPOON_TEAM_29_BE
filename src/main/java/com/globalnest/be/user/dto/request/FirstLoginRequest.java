package com.globalnest.be.user.dto.request;

import com.globalnest.be.user.domain.type.AgeRange;
import com.globalnest.be.user.domain.type.Language;
import com.globalnest.be.user.domain.type.Part;

public record FirstLoginRequest(
    String name,
    String nickname,
    AgeRange ageRange, // 예: "20대", "30대"
    Part part, // 예: "개발자", "디자이너"
    Language language
) {}