package es.upm.frameworkeducativosubject.infrastructure.api.rest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String id_user;
    private String ident;
    private String name;
    private String surnames;
    private String password;
    private String email;
    private List<String> roles;
    private Boolean isChanged;
//    @JsonCreator
//    public UserDTO(@JsonProperty("id_user")String id_user,
//                   @JsonProperty("ident")String ident,
//                   @JsonProperty("name")String name,
//                   @JsonProperty("surnames")String surnames,
//                   @JsonProperty("password")String password,
//                   @JsonProperty("email")String email,
//                   @JsonProperty("roles")List<String> roles,
//                   @JsonProperty("isChanged")Boolean isChanged) {
//        this.id_user = id_user;
//        this.ident = ident;
//        this.name = name;
//        this.surnames = surnames;
//        this.password = password;
//        this.email = email;
//        this.roles = roles;
//        this.isChanged = isChanged;
//    }
}

