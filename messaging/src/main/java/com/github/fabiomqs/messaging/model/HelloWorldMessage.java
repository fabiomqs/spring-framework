package com.github.fabiomqs.messaging.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HelloWorldMessage {

    static final long serialVersionUID = -8708305855930327096L;

    private UUID id;
    private String message;
}
