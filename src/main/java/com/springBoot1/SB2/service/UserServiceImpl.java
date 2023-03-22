package com.springBoot1.SB2.service;

import com.springBoot1.SB2.config.Authority.Authority;
import com.springBoot1.SB2.config.UserDetailsImpl;
import com.springBoot1.SB2.dto.pharmacy.ShowIdPharmacyDTO;
import com.springBoot1.SB2.dto.pharmacy.ShowPharmacyDTO;
import com.springBoot1.SB2.dto.transaction.CreateTransactionDTO;
import com.springBoot1.SB2.dto.transaction.ShowAllTransactionDTO;
import com.springBoot1.SB2.dto.transaction.ShowTransactionDTO;
import com.springBoot1.SB2.dto.transaction.UpdateTransactionDTO;
import com.springBoot1.SB2.dto.user.CreateUserDTO;
import com.springBoot1.SB2.dto.user.ShowAllUserDTO;
import com.springBoot1.SB2.dto.user.ShowUserDTO;
import com.springBoot1.SB2.dto.user.UpdateUserDTO;
import com.springBoot1.SB2.entity.*;
import com.springBoot1.SB2.event.ResetPasswordEmailSenderEvent;
import com.springBoot1.SB2.exception.api.ApiException;
import com.springBoot1.SB2.exception.api.UnAuthorizedAccessException;
import com.springBoot1.SB2.mapper.base.BaseMapper;
import com.springBoot1.SB2.mapper.user.UserMapper;
import com.springBoot1.SB2.repository.*;
import com.springBoot1.SB2.service.base.BaseTrashableCRUDServiceImpl;
import com.springBoot1.SB2.util.ArrayUtil;
import com.springBoot1.SB2.util.ObjectUtil;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl extends BaseTrashableCRUDServiceImpl<User, Long, CreateUserDTO, ShowAllUserDTO, ShowUserDTO, UpdateUserDTO> implements UserService {
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ResetPasswordTokenRepository passwordTokenRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Value("${resetPasswordToken.exp}")
    private Long restPasswordTokenExp;
    @Value("${resetPasswordToken.email.from}")
    protected String from;
    @Value("${resetPasswordToken.email.html}")
    protected boolean html;
    @Autowired
    private PharmacyRepository pharmacyRepository;
    @Value("${uploadPaths.userPath}")
    private String userPath;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PharmacyUserRepository pharmacyUserRepository;
    @Autowired
    private ApplicationEventPublisher eventPublisher;
    @PreAuthorize("hasAuthority('" + Authority.USERCREATE + "')")
    @Override
    public void create(CreateUserDTO userDTO) throws  UnAuthorizedAccessException, ApiException {
        User user=userMapper.unMapCreated(userDTO,userPath);
        checkPriority(user.getRole().getPriority());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        baseRepository.save(user);
    }

    @PreAuthorize("hasAuthority('" + Authority.USERUPDATE + "')")
    @Override
    public void update(Long id, UpdateUserDTO userDTO) throws  UnAuthorizedAccessException, ApiException {
        User user = userRepository.findById(id).orElseThrow(() -> new ApiException(messageSource.getMessage("exceptions.id.notfound", ArrayUtil.of(id.toString()), LocaleContextHolder.getLocale())));//throw exception
        checkPriority(user.getRole().getPriority());
        if (userDTO.getPharmacy()!=null) {
            pharmacyUserRepository.deleteByUserId(id);
        }
        user=userMapper.unMapUpdated(user,userDTO,userPath);
        if (StringUtils.hasText(userDTO.getPassword())) {
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        }
        if (userDTO.getImg()!=null&&!userDTO.getImg().isEmpty()) {
            try {
                if (user.getImg() != null) {
                    Path path = Paths.get(user.getImg());
                    Files.deleteIfExists(path);
                }
            } catch (IOException e) {
                throw new ApiException(e.getMessage());
            }
        }
        userRepository.save(user);
    }

    @PreAuthorize("hasAuthority('" + Authority.USERSHOW + "')")
    @Override
    public ShowUserDTO show(Long id) throws  UnAuthorizedAccessException, ApiException {
        return super.show(id);
    }

    @PreAuthorize("hasAuthority('" + Authority.USERSHOWALL + "')")
    @Override
    public List<ShowAllUserDTO> findAll(int page, int size, String sort) {
        return super.findAll(page, size, sort);
    }

    @PreAuthorize("hasAuthority('" + Authority.USERTRASH + "')")
    @Override
    public void trash(Long id) throws  UnAuthorizedAccessException, ApiException {
        User user = userRepository.findById(id).orElseThrow(() -> new ApiException(messageSource.getMessage("exceptions.id.notfound", ArrayUtil.of(id.toString()), LocaleContextHolder.getLocale())));
        checkPriority(user.getRole().getPriority());
        user.setTrashed(true);
        userRepository.save(user);
    }

    @PreAuthorize("hasAuthority('" + Authority.USERRESTORE + "')")
    @Override
    public void restore(Long id) throws  UnAuthorizedAccessException, ApiException {
        User user = userRepository.findById(id).orElseThrow(() -> new ApiException(messageSource.getMessage("exceptions.id.notfound", ArrayUtil.of(id.toString()), LocaleContextHolder.getLocale())));
        checkPriority(user.getRole().getPriority());
        user.setTrashed(false);
        userRepository.save(user);
    }

    @PreAuthorize("hasAuthority('" + Authority.USERDELETE + "')")
    @Override
    public void delete(Long id) throws  UnAuthorizedAccessException, ApiException {
        User user = userRepository.findById(id).orElseThrow(() ->new ApiException(messageSource.getMessage("exceptions.id.notfound", ArrayUtil.of(id.toString()), LocaleContextHolder.getLocale())));
        checkPriority(user.getRole().getPriority());
        baseRepository.deleteById(id);
    }

    @PreAuthorize("hasAuthority('" + Authority.MEDICINEUPDATECOUNT + "')")
    @Override
    public Set<ShowIdPharmacyDTO> currentUserPharmacies() {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Set<ShowIdPharmacyDTO> showPharmacyDTOS = new HashSet<>();
        if (userDetails.getUser().getManaging()!=null&&!userDetails.getUser().getManaging().isEmpty()){
            showPharmacyDTOS = userDetails.getUser().getManaging().stream().map(pharmacy -> {
                return new ShowIdPharmacyDTO(pharmacy.getId(),pharmacy.getName()+" "+pharmacy.getAddress().getGovernorate()+"-"+pharmacy.getAddress().getCity()+"-"+pharmacy.getAddress().getTown());
            }).collect(Collectors.toSet());
        } else if (Objects.nonNull(userDetails.getUser().getPharmacy())&&userDetails.getUser().getPharmacy().getPharmacy()!=null) {
                showPharmacyDTOS.add(new ShowIdPharmacyDTO(userDetails.getUser().getPharmacy().getId(),userDetails.getUser().getPharmacy().getPharmacy().getName()));
        }else {
            showPharmacyDTOS = pharmacyRepository.findAll().stream().map(pharmacy -> {
                return  new ShowIdPharmacyDTO(pharmacy.getId(),pharmacy.getName());
            }).collect(Collectors.toSet());
        }
        return showPharmacyDTOS;
    }
    @Override
    public String resetPasswordByEmail(String email, String link) throws MessagingException,  ApiException {
        User user = userRepository.findByEmail(email).orElseThrow(() ->new ApiException(messageSource.getMessage("exceptions.filed.notfound", ArrayUtil.of(email), LocaleContextHolder.getLocale())));
        String token = RandomString.make(50);
        long dateMS = new Date().getTime();//need to change to local time
        long exp = dateMS + restPasswordTokenExp;//need to optimize
        String resetPasswordLink = link + "?token=" + token;
        eventPublisher.publishEvent(new ResetPasswordEmailSenderEvent(
                from,email,messageSource.getMessage("resetPassword.email.subject", ArrayUtil.of(), LocaleContextHolder.getLocale()),
                messageSource.getMessage("resetPassword.email.text", ArrayUtil.of(resetPasswordLink), LocaleContextHolder.getLocale())
                ,html));
        ResetPasswordToken resetPasswordToken =
                user.getResetPasswordToken() == null ?
                        new ResetPasswordToken()
                        : user.getResetPasswordToken();
        resetPasswordToken.setToken(token);
        resetPasswordToken.setExp(exp);
        resetPasswordToken.setUser(user);
        user.setResetPasswordToken(resetPasswordToken);
        userRepository.save(user);
        return token;
    }
    @Override
    public boolean isResetTokenValid(String token) {
        Optional<ResetPasswordToken> resetPasswordToken =
                passwordTokenRepository.findByToken(token);
        if (resetPasswordToken.isPresent()) {
            long date = new Date().getTime();
            return resetPasswordToken.get().getExp() >= date;
        }
        return false;
    }
    @Override
    public boolean updatePasswordByToken(String token, String password) {
        Optional<ResetPasswordToken> resetPasswordToken =
                passwordTokenRepository.findByToken(token);
        if (resetPasswordToken.isPresent() && isResetTokenValid(token)) {
            resetPasswordToken.get().getUser().setPassword(passwordEncoder.encode(password));
            userRepository.save(resetPasswordToken.get().getUser());
            return true;
        }
        return false;
    }
}
