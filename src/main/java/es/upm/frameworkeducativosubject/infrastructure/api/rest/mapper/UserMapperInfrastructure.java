package es.upm.frameworkeducativosubject.infrastructure.api.rest.mapper;

import es.upm.frameworkeducativosubject.domain.model.User;
import es.upm.frameworkeducativosubject.infrastructure.api.rest.model.UserDTO;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapperInfrastructure {
    public UserDTO userToUserDTO(User user) {
        return UserDTO.builder()
                .id_user(user.getId_user())
                .ident(user.getIdent())
                .name(user.getName())
                .surnames(user.getSurnames())
                .email(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRoles())
                .isChanged(user.getIsChanged())
                .build();
    }

    public User userDTOToUser(UserDTO userDTO) {
        return User.builder()
                .id_user(userDTO.getId_user())
                .ident(userDTO.getIdent())
                .name(userDTO.getName())
                .surnames(userDTO.getSurnames())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .roles(userDTO.getRoles())
                .isChanged(userDTO.getIsChanged())
                .build();
    }

    public List<User> userDTOListTOUserList(List<UserDTO> usersDTO) {
        return usersDTO.stream()
                .map(this::userDTOToUser)
                .collect(Collectors.toList());
    }
}
