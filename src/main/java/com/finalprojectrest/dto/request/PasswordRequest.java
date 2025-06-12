package com.finalprojectrest.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PasswordRequest {

    @NotBlank(message = "Old password must not be empty")
    String oldPassword;

    @Size(min = 8, max = 64, message = "New password must be between 8 and 64 characters")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-\\[\\]{};':\"\\\\|,.<>/?]).{8,}$",
            message = "Password must contain at least 1 uppercase, 1 lowercase, 1 digit, 1 special character and be at least 8 characters long"
    )
    String newPassword;


    @NotBlank(message = "Confirm password must not be empty")
    String confirmPassword;
}

