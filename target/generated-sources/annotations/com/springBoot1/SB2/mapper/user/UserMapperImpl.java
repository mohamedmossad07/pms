package com.springBoot1.SB2.mapper.user;

import com.springBoot1.SB2.dto.pharmacy.ShowIdPharmacyDTO;
import com.springBoot1.SB2.dto.role.ShowRoleIdAndNameDTO;
import com.springBoot1.SB2.dto.user.CreateUserDTO;
import com.springBoot1.SB2.dto.user.ShowAllUserDTO;
import com.springBoot1.SB2.dto.user.ShowUserDTO;
import com.springBoot1.SB2.dto.user.UpdateUserDTO;
import com.springBoot1.SB2.entity.Pharmacy;
import com.springBoot1.SB2.entity.PharmacyUser;
import com.springBoot1.SB2.entity.Role;
import com.springBoot1.SB2.entity.User;
import com.springBoot1.SB2.exception.api.ApiException;
import com.springBoot1.SB2.mapper.base.IdsMapper;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-23T01:37:42+0200",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.18 (Ubuntu)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Autowired
    private IdsMapper idsMapper;

    @Override
    public List<ShowAllUserDTO> mapToShowAll(List<User> entities) {
        if ( entities == null ) {
            return null;
        }

        List<ShowAllUserDTO> list = new ArrayList<ShowAllUserDTO>( entities.size() );
        for ( User user : entities ) {
            list.add( map2SingleShowAll( user ) );
        }

        return list;
    }

    @Override
    public User unMapCreated(CreateUserDTO createUserDTO, String path) {
        if ( createUserDTO == null ) {
            return null;
        }

        User user = new User();

        user.setFname( createUserDTO.getFname() );
        user.setLname( createUserDTO.getLname() );
        user.setUsername( createUserDTO.getUsername() );
        user.setEmail( createUserDTO.getEmail() );
        user.setPassword( createUserDTO.getPassword() );
        user.setPhone( createUserDTO.getPhone() );
        try {
            user.setImg( idsMapper.mapMultiPartToString( createUserDTO.getImg(), path ) );
        }
        catch ( ApiException e ) {
            throw new RuntimeException( e );
        }
        try {
            user.setRole( idsMapper.mapLongIdToRole( createUserDTO.getRole() ) );
        }
        catch ( ApiException e ) {
            throw new RuntimeException( e );
        }

        user.setPharmacy( IdsMapper.mapPharmacyIdToPharmacyUser(createUserDTO.getPharmacy(),user) );

        return user;
    }

    @Override
    public User unMapUpdated(User user, UpdateUserDTO updateUserDTO, String path) {
        if ( updateUserDTO == null ) {
            return user;
        }

        if ( updateUserDTO.getFname() != null ) {
            user.setFname( updateUserDTO.getFname() );
        }
        if ( updateUserDTO.getLname() != null ) {
            user.setLname( updateUserDTO.getLname() );
        }
        if ( updateUserDTO.getUsername() != null ) {
            user.setUsername( updateUserDTO.getUsername() );
        }
        if ( updateUserDTO.getEmail() != null ) {
            user.setEmail( updateUserDTO.getEmail() );
        }
        if ( updateUserDTO.getPassword() != null ) {
            user.setPassword( updateUserDTO.getPassword() );
        }
        if ( updateUserDTO.getPhone() != null ) {
            user.setPhone( updateUserDTO.getPhone() );
        }
        try {
            if ( updateUserDTO.getImg() != null ) {
                user.setImg( idsMapper.mapMultiPartToString( updateUserDTO.getImg(), path ) );
            }
        }
        catch ( ApiException e ) {
            throw new RuntimeException( e );
        }
        try {
            if ( updateUserDTO.getRole() != null ) {
                user.setRole( idsMapper.mapLongIdToRole( updateUserDTO.getRole() ) );
            }
        }
        catch ( ApiException e ) {
            throw new RuntimeException( e );
        }

        user.setPharmacy( IdsMapper.mapPharmacyIdToPharmacyUser(updateUserDTO.getPharmacy(),user) );

        return user;
    }

    @Override
    public User unMapCreated(CreateUserDTO createUserDTO) {
        if ( createUserDTO == null ) {
            return null;
        }

        User user = new User();

        user.setFname( createUserDTO.getFname() );
        user.setLname( createUserDTO.getLname() );
        user.setUsername( createUserDTO.getUsername() );
        user.setEmail( createUserDTO.getEmail() );
        user.setPassword( createUserDTO.getPassword() );
        user.setPhone( createUserDTO.getPhone() );
        try {
            user.setRole( idsMapper.mapLongIdToRole( createUserDTO.getRole() ) );
        }
        catch ( ApiException e ) {
            throw new RuntimeException( e );
        }

        return user;
    }

    @Override
    public User unMapUpdated(User user, UpdateUserDTO updateUserDTO) {
        if ( updateUserDTO == null ) {
            return user;
        }

        if ( updateUserDTO.getFname() != null ) {
            user.setFname( updateUserDTO.getFname() );
        }
        if ( updateUserDTO.getLname() != null ) {
            user.setLname( updateUserDTO.getLname() );
        }
        if ( updateUserDTO.getUsername() != null ) {
            user.setUsername( updateUserDTO.getUsername() );
        }
        if ( updateUserDTO.getEmail() != null ) {
            user.setEmail( updateUserDTO.getEmail() );
        }
        if ( updateUserDTO.getPassword() != null ) {
            user.setPassword( updateUserDTO.getPassword() );
        }
        if ( updateUserDTO.getPhone() != null ) {
            user.setPhone( updateUserDTO.getPhone() );
        }
        try {
            if ( updateUserDTO.getRole() != null ) {
                user.setRole( idsMapper.mapLongIdToRole( updateUserDTO.getRole() ) );
            }
        }
        catch ( ApiException e ) {
            throw new RuntimeException( e );
        }

        return user;
    }

    @Override
    public ShowAllUserDTO map2SingleShowAll(User user) {
        if ( user == null ) {
            return null;
        }

        ShowAllUserDTO showAllUserDTO = new ShowAllUserDTO();

        showAllUserDTO.setRole( userRoleName( user ) );
        showAllUserDTO.setCreatedAt( user.getCreatedAt() );
        showAllUserDTO.setId( user.getId() );
        showAllUserDTO.setUsername( user.getUsername() );
        showAllUserDTO.setEmail( user.getEmail() );
        showAllUserDTO.setPhone( user.getPhone() );

        showAllUserDTO.setName( user.getFname()+" "+user.getLname() );

        return showAllUserDTO;
    }

    @Override
    public ShowUserDTO mapToShow(User user) {
        if ( user == null ) {
            return null;
        }

        ShowUserDTO showUserDTO = new ShowUserDTO();

        showUserDTO.setPharmacy( pharmacyToShowIdPharmacyDTO( userPharmacyPharmacy( user ) ) );
        showUserDTO.setCreatedBy( user.getCreatedBy() );
        showUserDTO.setUpdatedBy( user.getUpdatedBy() );
        showUserDTO.setCreatedAt( user.getCreatedAt() );
        showUserDTO.setUpdatedAt( user.getUpdatedAt() );
        showUserDTO.setId( user.getId() );
        showUserDTO.setTrashed( user.isTrashed() );
        showUserDTO.setFname( user.getFname() );
        showUserDTO.setLname( user.getLname() );
        showUserDTO.setUsername( user.getUsername() );
        showUserDTO.setEmail( user.getEmail() );
        showUserDTO.setPhone( user.getPhone() );
        showUserDTO.setImg( user.getImg() );
        showUserDTO.setRole( roleToShowRoleIdAndNameDTO( user.getRole() ) );
        showUserDTO.setManaging( pharmacySetToShowIdPharmacyDTOSet( user.getManaging() ) );

        return showUserDTO;
    }

    private String userRoleName(User user) {
        if ( user == null ) {
            return null;
        }
        Role role = user.getRole();
        if ( role == null ) {
            return null;
        }
        String name = role.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private Pharmacy userPharmacyPharmacy(User user) {
        if ( user == null ) {
            return null;
        }
        PharmacyUser pharmacy = user.getPharmacy();
        if ( pharmacy == null ) {
            return null;
        }
        Pharmacy pharmacy1 = pharmacy.getPharmacy();
        if ( pharmacy1 == null ) {
            return null;
        }
        return pharmacy1;
    }

    protected ShowIdPharmacyDTO pharmacyToShowIdPharmacyDTO(Pharmacy pharmacy) {
        if ( pharmacy == null ) {
            return null;
        }

        ShowIdPharmacyDTO showIdPharmacyDTO = new ShowIdPharmacyDTO();

        showIdPharmacyDTO.setId( pharmacy.getId() );
        showIdPharmacyDTO.setName( pharmacy.getName() );

        return showIdPharmacyDTO;
    }

    protected ShowRoleIdAndNameDTO roleToShowRoleIdAndNameDTO(Role role) {
        if ( role == null ) {
            return null;
        }

        ShowRoleIdAndNameDTO showRoleIdAndNameDTO = new ShowRoleIdAndNameDTO();

        showRoleIdAndNameDTO.setId( role.getId() );
        showRoleIdAndNameDTO.setName( role.getName() );

        return showRoleIdAndNameDTO;
    }

    protected Set<ShowIdPharmacyDTO> pharmacySetToShowIdPharmacyDTOSet(Set<Pharmacy> set) {
        if ( set == null ) {
            return null;
        }

        Set<ShowIdPharmacyDTO> set1 = new LinkedHashSet<ShowIdPharmacyDTO>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Pharmacy pharmacy : set ) {
            set1.add( pharmacyToShowIdPharmacyDTO( pharmacy ) );
        }

        return set1;
    }
}
