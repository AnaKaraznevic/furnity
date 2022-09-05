package com.furnity.furnity.service;

import com.furnity.furnity.model.User;
import com.furnity.furnity.web.dto.UserRegistrationDto;

public interface UserService {
    User saveNewUser( UserRegistrationDto userRegistrationDto );
}
