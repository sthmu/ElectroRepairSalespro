package DTO;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserDto {
    private String Id;
    private String userName;
    private String Password;
    private String description;
    private String createdAt;
    private String status;
    private int authorityLvl;

    public UserDto(String id, String userName, String password, String description, int authorityLvl) {
        Id = id;
        this.userName = userName;
        Password = password;
        this.description = description;
        this.authorityLvl = authorityLvl;
    }
}
