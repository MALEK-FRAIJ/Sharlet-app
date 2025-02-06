package com.sharelt.api.sharelt_api.service;

import java.io.IOException;

import org.apache.coyote.BadRequestException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.sharelt.api.sharelt_api.entity.user.User;
import com.sharelt.api.sharelt_api.exception.AuthenticationException;
import com.sharelt.api.sharelt_api.exception.FileStorageException;
import com.sharelt.api.sharelt_api.exception.UserException;
import com.sharelt.api.sharelt_api.request.ChangePasswordRequest;
import com.sharelt.api.sharelt_api.request.SignUpRequest;
import com.sharelt.api.sharelt_api.request.UpdateUserRequest;
import com.sharelt.api.sharelt_api.response.EditProfileResponse;
import com.sharelt.api.sharelt_api.response.PagedResponse;
import com.sharelt.api.sharelt_api.response.PrivateResponse;
import com.sharelt.api.sharelt_api.response.UserIdentityAvailability;
import com.sharelt.api.sharelt_api.response.UserProfileResponse;
import com.sharelt.api.sharelt_api.response.UserProfileSummary;
import com.sharelt.api.sharelt_api.security.UserPrincipal;

public interface UserService {

    PagedResponse<UserProfileSummary> getUsersByUsernameOrName(int page, int size, String usernameOrName)
            throws UserException, BadRequestException;

    UserProfileResponse getUserProfileByUsername(String username);

    EditProfileResponse getProfileDetailsToEdit(UserPrincipal currentUser);

    void updateUser(UserPrincipal currentUser, UpdateUserRequest updateUserRequest);

    User getByUsername(String username) throws UserException;

    ResponseEntity<?> updateProfilePicture(UserPrincipal currentUser, MultipartFile image)
            throws FileStorageException, IOException;

    PrivateResponse toggleUserPrivacy(UserPrincipal currentUser);

    ResponseEntity<?> changePassword(ChangePasswordRequest changePasswordRequest) throws AuthenticationException;

    boolean isOldPasswordValid(User user, String oldPassword);

    UserIdentityAvailability checkUsernameAvailability(String username);

    UserIdentityAvailability checkEmailAvailability(String email);

    void validatePageRequest(int pageNumber, int pageSize) throws BadRequestException;

    User createUser(SignUpRequest signUpRequest);
}
