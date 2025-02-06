package com.sharelt.api.sharelt_api.dto;

import com.sharelt.api.sharelt_api.entity.user.User;
import com.sharelt.api.sharelt_api.response.EditProfileResponse;
import com.sharelt.api.sharelt_api.response.UserProfileSummary;

public class ModelMapper {

    public static UserProfileSummary mapUsersToUserSummaries(User user) {
        UserProfileSummary userProfileSummary = new UserProfileSummary();
        userProfileSummary.setId(user.getId());
        userProfileSummary.setName(user.getName());
        userProfileSummary.setUsername(user.getUsername());
        userProfileSummary.setImagePath(user.getImagePath());
        return userProfileSummary;
    }

    public static EditProfileResponse mapUserToEditProfileResponse(User user) {
        EditProfileResponse editProfileResponse = new EditProfileResponse();

        editProfileResponse.setId(user.getId());
        editProfileResponse.setUsername(user.getUsername());
        editProfileResponse.setEmail(user.getEmail());
        editProfileResponse.setName(user.getName());
        editProfileResponse.setMobile(user.getMobile());
        editProfileResponse.setImagePath(user.getImagePath());
        editProfileResponse.setWebsite(user.getWebsite());
        editProfileResponse.setBio(user.getBio());
        editProfileResponse.setPrivate(user.isPrivate());
        return editProfileResponse;
    }

}
