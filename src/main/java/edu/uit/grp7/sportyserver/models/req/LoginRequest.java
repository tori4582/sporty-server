package edu.uit.grp7.sportyserver.models.req;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
@RequiredArgsConstructor
public class LoginRequest {
    private final String username;
    private final String password;
}
