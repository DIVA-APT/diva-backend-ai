package com.apt.diva_ai.domain.inference.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChatBotRequestDTO {
    private String input;
}
