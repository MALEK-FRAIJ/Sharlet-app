package com.sharelt.api.sharelt_api.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class EditProfileResponse {
    private Long id;
    private String username;
    private String email;
    private String name;
    private String bio;
    private String mobile;
    private String website;
    private String imagePath;
    private boolean isPrivate;
}
