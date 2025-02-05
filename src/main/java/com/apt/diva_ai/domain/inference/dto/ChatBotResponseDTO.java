package com.apt.diva_ai.domain.inference.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChatBotResponseDTO {

    @JsonAlias({"botMessage", "bot_message"})
    private String botMessage;
}
