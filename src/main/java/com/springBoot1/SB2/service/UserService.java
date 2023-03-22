package com.springBoot1.SB2.service;

import com.springBoot1.SB2.dto.pharmacy.ShowIdPharmacyDTO;
import com.springBoot1.SB2.dto.pharmacy.ShowPharmacyDTO;
import com.springBoot1.SB2.exception.api.ApiException;

import javax.mail.MessagingException;
import java.util.Set;

public interface UserService {
    String resetPasswordByEmail(String email, String link) throws MessagingException, ApiException;

    boolean isResetTokenValid(String token);

    boolean updatePasswordByToken(String token, String password);

    Set<ShowIdPharmacyDTO> currentUserPharmacies();
}
