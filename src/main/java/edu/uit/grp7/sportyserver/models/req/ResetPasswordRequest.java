package edu.uit.grp7.sportyserver.models.req;

import com.google.auto.value.AutoValue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class ResetPasswordRequest extends LoginRequest {
    private String newPassword;
}