package io.sultanov.accountservice.domain.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.sultanov.accountservice.api.security.utils.Role;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
public class User {

    private Long id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String lastname;

    @Pattern(regexp = "[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+")
    private String email;

    @NotEmpty
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Length(min = 12)
    private String password;

    private String userRole;
}
