package com.sharelt.api.sharelt_api.service;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.apache.coyote.BadRequestException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sharelt.api.sharelt_api.dto.ModelMapper;
import com.sharelt.api.sharelt_api.entity.user.User;
import com.sharelt.api.sharelt_api.exception.AuthenticationException;
import com.sharelt.api.sharelt_api.exception.FileStorageException;
import com.sharelt.api.sharelt_api.exception.UserException;
import com.sharelt.api.sharelt_api.repository.UserRepository;
import com.sharelt.api.sharelt_api.request.ChangePasswordRequest;
import com.sharelt.api.sharelt_api.request.SignUpRequest;
import com.sharelt.api.sharelt_api.request.UpdateUserRequest;
import com.sharelt.api.sharelt_api.response.ApiResponse;
import com.sharelt.api.sharelt_api.response.EditProfileResponse;
import com.sharelt.api.sharelt_api.response.PagedResponse;
import com.sharelt.api.sharelt_api.response.PrivateResponse;
import com.sharelt.api.sharelt_api.response.ProfilePictureUpdateResponse;
import com.sharelt.api.sharelt_api.response.UserIdentityAvailability;
import com.sharelt.api.sharelt_api.response.UserProfileResponse;
import com.sharelt.api.sharelt_api.response.UserProfileSummary;
import com.sharelt.api.sharelt_api.security.UserPrincipal;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserImplementation implements UserService {

    String DEFAULT_PAGE_NUMBER = "0";
    String DEFAULT_PAGE_SIZE = "30";

    int MAX_PAGE_SIZE = 50;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final FileStorageService fileStorageService;

    private final String defaultImagePath = "http://localhost:5000/api/posts/images/default-user.jpg";

    @Override
    public PagedResponse<UserProfileSummary> getUsersByUsernameOrName(int pageNumber, int pageSize,
            String usernameOrName)
            throws UserException, BadRequestException {
        validatePageRequest(pageNumber, pageSize);
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.Direction.DESC, "createdAt");
        Page<User> users = userRepository.findByNameOrUsername(usernameOrName, pageable);

        if (users.getNumberOfElements() == 0) {
            return new PagedResponse<>(Collections.emptyList(), users.getNumber(), users.getSize(),
                    users.getTotalElements(), users.getTotalPages(), users.isLast());
        }

        List<UserProfileSummary> userSummaries = users.map(ModelMapper::mapUsersToUserSummaries).getContent();
        return new PagedResponse<>(userSummaries, users.getNumber(),
                users.getSize(), users.getTotalElements(), users.getTotalPages(), users.isLast());
    }

    @Override
    public UserProfileResponse getUserProfileByUsername(String username) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUserProfileByUsername'");
    }

    @Override
    public EditProfileResponse getProfileDetailsToEdit(UserPrincipal currentUser) {
        User user = userRepository.findByUsername(currentUser.getUsername()).get();
        return ModelMapper.mapUserToEditProfileResponse(user);
    }

    @Override
    public void updateUser(UserPrincipal currentUser, UpdateUserRequest updateUserRequest) {
        User userToUpdate = userRepository.getReferenceById(currentUser.getId());

        userToUpdate.setName(updateUserRequest.getName());
        userToUpdate.setUsername(updateUserRequest.getUsername());
        userToUpdate.setEmail(updateUserRequest.getEmail());
        userToUpdate.setBio(updateUserRequest.getBio());
        userToUpdate.setMobile(updateUserRequest.getMobile());
        userToUpdate.setWebsite(updateUserRequest.getBio());
        userRepository.save(userToUpdate);
    }

    @Override
    public User getByUsername(String username) throws UserException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getByUsername'");
    }

    @Override
    public ResponseEntity<ProfilePictureUpdateResponse> updateProfilePicture(UserPrincipal currentUser,
            MultipartFile image)
            throws FileStorageException, IOException {
        String storedFileName = fileStorageService.storeFile(image);
        String imageUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("api/posts/images/")
                .path(storedFileName)
                .toUriString();

        User user = userRepository.getReferenceById(currentUser.getId());
        user.setImagePath(imageUrl != null ? imageUrl : defaultImagePath);

        userRepository.save(user);

        return ResponseEntity.ok(new ProfilePictureUpdateResponse(imageUrl));
    }

    @Override
    public PrivateResponse toggleUserPrivacy(UserPrincipal currentUser) {
        User user = userRepository.getReferenceById(currentUser.getId());
        boolean newPrivacyStatus = !user.isPrivate();
        user.setPrivate(newPrivacyStatus);
        userRepository.save(user);
        return new PrivateResponse(newPrivacyStatus);
    }

    @Override
    public ResponseEntity<ApiResponse> changePassword(ChangePasswordRequest changePasswordRequest)
            throws AuthenticationException {
        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> userOptional = userRepository.findByUsername(currentUsername);

        User user = userOptional.orElseThrow(() -> new AuthenticationException("User not found"));

        if (!isOldPasswordValid(user, changePasswordRequest.getOldPassword())) {
            throw new AuthenticationException("Invalid Old Password.");
        }

        String encodedNewPassword = passwordEncoder.encode(changePasswordRequest.getNewPassword());
        user.setPassword(encodedNewPassword);

        userRepository.save(user);

        return ResponseEntity.ok(new ApiResponse(true, "Password changed successfully."));
    }

    @Override
    public boolean isOldPasswordValid(User user, String oldPassword) {
        return passwordEncoder.matches(oldPassword, user.getPassword());
    }

    @Override
    public UserIdentityAvailability checkUsernameAvailability(String username) {
        Boolean isUsernameAvailable = !userRepository.existsByUsername(username);
        return new UserIdentityAvailability(isUsernameAvailable);
    }

    @Override
    public UserIdentityAvailability checkEmailAvailability(String email) {
        Boolean isEmailAvailable = !userRepository.existsByEmail(email);
        return new UserIdentityAvailability(isEmailAvailable);
    }

    @Override
    public void validatePageRequest(int pageNumber, int pageSize) throws BadRequestException {
        if (pageNumber < 0) {
            throw new BadRequestException("Page number cannot be less than zero.");
        }
        if (pageSize > MAX_PAGE_SIZE) {
            throw new BadRequestException("Page size must not be greater than " + MAX_PAGE_SIZE);
        }
    }

    @Override
    public User createUser(SignUpRequest signUpRequest) {

        User user = new User();
        user.setUsername(signUpRequest.getUsername());
        user.setName(signUpRequest.getName());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        return userRepository.save(user);
    }

}
