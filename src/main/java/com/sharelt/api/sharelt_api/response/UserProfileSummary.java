package com.sharelt.api.sharelt_api.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserProfileSummary {// using for response

    private Long id;
    private String username;
    private String name;
    private String imagePath;

}
