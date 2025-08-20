package com.dipeshbc.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Schema(
        name = "Response",
        description = "Schema to hold successful response information"
)
@Data
@AllArgsConstructor
public class ResponseDto {

    @Schema(
            description = "HTTP status code of the successful response",
            example = "200"
    )
    private String statusCode;

    @Schema(
            description = "A message indicating the result of the successful operation",
            example = "Request processed successfully"
    )
    private String statusMsg;
}
