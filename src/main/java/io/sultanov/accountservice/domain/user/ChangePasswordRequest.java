package io.sultanov.accountservice.domain.user;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class ChangePasswordRequest {
    @Length(min = 12)
    @NotEmpty
    private String newPassword;

    private String oldPassword;
}
