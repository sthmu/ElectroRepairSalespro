package Services.custom;

import DTO.UserDto;
import Services.SuperBo;

import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface UserBo extends SuperBo {
    List<UserDto> assignToUserList();

    UserDto getLoggedInUser();

    boolean logout();

    boolean login(String username, String password) throws NoSuchAlgorithmException;

    boolean registerUser(UserDto user) throws NoSuchAlgorithmException;

    List<UserDto> getUserList();


}
