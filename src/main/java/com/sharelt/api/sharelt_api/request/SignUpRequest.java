package com.sharelt.api.sharelt_api.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class SignUpRequest {

    @NotBlank
    @Size(min = 2, max = 15)
    private String username;

    @NotBlank
    @Size(min = 4, max = 25)
    private String name;

    @NotBlank
    @Size(max = 40)
    @Email
    private String email;

    @NotBlank
    @Size(min = 6, max = 30)
    private String password;

}
