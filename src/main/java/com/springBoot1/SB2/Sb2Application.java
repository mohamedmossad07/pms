package com.springBoot1.SB2;

import com.springBoot1.SB2.entity.Authority;
import com.springBoot1.SB2.entity.Role;
import com.springBoot1.SB2.entity.User;
import com.springBoot1.SB2.repository.RoleRepository;
import com.springBoot1.SB2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@SpringBootApplication
public class Sb2Application{
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(Sb2Application.class, args);
    }
//    @EventListener
//    public void run(ContextRefreshedEvent event){
//        System.out.println("========ContextRefreshedEvent===========");
//       ArrayUtil.of("Test","test2");
//    }


    //start_________________comment_______________________________
//    @EventListener
//    public void handleContextRefresh(ContextRefreshedEvent event) {
//        String[] auth = {
//                "CATEGORYCREATE",
//                "CATEGORYUPDATE",
//                "CATEGORYSHOW",
//                "CATEGORYSHOWALL",
//                "CATEGORYTRASH",
//                "CATEGORYRESTORE",
//                "CATEGORYDELETE",
//                "USERCREATE",
//                "USERUPDATE",
//                "USERSHOW",
//                "USERSHOWALL",
//                "USERTRASH",
//                "USERRESTORE",
//                "USERDELETE",
//                "ROLECREATE",
//                "ROLEUPDATE",
//                "ROLESHOW",
//                "ROLESHOWALL",
//                "ROLETRASH",
//                "ROLERESTORE",
//                "ROLEDELETE",
//                "ADDRESSCREATE",
//                "ADDRESSUPDATE",
//                "ADDRESSSHOW",
//                "ADDRESSSHOWALL",
//                "ADDRESSTRASH",
//                "ADDRESSRESTORE",
//                "ADDRESSDELETE",
//                "CUSTOMERCREATE",
//                "CUSTOMERUPDATE",
//                "CUSTOMERSHOW",
//                "CUSTOMERSHOWALL",
//                "CUSTOMERTRASH",
//                "CUSTOMERRESTORE",
//                "CUSTOMERDELETE",
//                "MEDICINECREATE",
//                "MEDICINEUPDATE",
//                "MEDICINESHOW",
//                "MEDICINESHOWALL",
//                "MEDICINETRASH",
//                "MEDICINERESTORE",
//                "MEDICINEDELETE",
//                "PHARMACYCREATE",
//                "PHARMACYUPDATE",
//                "PHARMACYSHOW",
//                "PHARMACYSHOWALL",
//                "PHARMACYTRASH",
//                "PHARMACYRESTORE",
//                "PHARMACYDELETE",
//                "SUPPLIERCREATE",
//                "SUPPLIERUPDATE",
//                "SUPPLIERSHOW",
//                "SUPPLIERSHOWALL",
//                "SUPPLIERTRASH",
//                "SUPPLIERRESTORE",
//                "SUPPLIERDELETE",
//                "TRANSACTIONCREATE",
//                "TRANSACTIONUPDATE",
//                "TRANSACTIONSHOW",
//                "TRANSACTIONSHOWALL",
//                "TRANSACTIONTRASH",
//                "TRANSACTIONRESTORE",
//                "TRANSACTIONDELETE",
//                "AUTHORITYSHOW",
//                "AUTHORITYSHOWALL",
//                "MEDICINESHOWTYPES",
//                "MEDICINEUPDATECOUNT"
//        };
//        Role role1 = new Role();
//        role1.setName("super");
//        role1.setPriority((short) 0);
//        Role role = roleRepository.save(role1);
//        User user = new User();
//        user.setFname("mohamed");
//        user.setLname("mossad");
//        user.setUsername("momo");
//        user.setEmail("mo@mo.com");
//        user.setPassword(passwordEncoder.encode("1234"));
//        user.setRole(role);
//        User user1 = userRepository.save(user);
//        Set<Authority> authorities;
//        authorities = Arrays.stream(auth).map(s -> {
//            Authority authority = new Authority();
//            authority.setName(s);
//            return authority;
//        }).collect(Collectors.toSet());
//        role.setAuthorities(authorities);
//        roleRepository.save(role);
//    }
    //end________________comment___________________________
//@EventListener
//public void handleContextRefresh(ContextRefreshedEvent event) {
//    ApplicationContext applicationContext = event.getApplicationContext();
//    RequestMappingHandlerMapping requestMappingHandlerMapping = applicationContext
//            .getBean("requestMappingHandlerMapping", RequestMappingHandlerMapping.class);
//    Map<RequestMappingInfo, HandlerMethod> map = requestMappingHandlerMapping
//            .getHandlerMethods();
//    map.forEach((key, value) -> System.out.println(key));
//}
}