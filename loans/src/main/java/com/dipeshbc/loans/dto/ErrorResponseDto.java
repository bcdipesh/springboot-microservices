package com.dipeshbc.loans.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Schema(
        name = "ErrorResponse",
        description = "Schema to hold error response information"
)
@Data
@AllArgsConstructor
public class ErrorResponseDto {

    @Schema(
            description = "API path that was invoked by the client",
            example = "/api/create"
    )
    private String apiPath;

    @Schema(
            description = "HTTP status code representing the error. See RFC 7231.",
            example = "400"
    )
    private HttpStatus errorCode;

    @Schema(
            description = "A human-readable description of the error",
            example = "A loan is already present for this mobile number"
    )
    private String errorMessage;

    @Schema(
            description = "The timestamp indicating when the error occurred",
            example = "2025-07-14T12:30:45"
    )
    private LocalDateTime errorTime;
}
