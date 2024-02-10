package Services.custom.impl;

import DTO.UserDto;
import Dao.DaoFactory;
import Dao.DaoType;
import Dao.custom.UserDao;
import Entity.User;
import Services.custom.UserBo;
import javafx.scene.control.Alert;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;


public class UserrBoImpl implements UserBo {
    static UserDto loggedInUser = null;
    static List<UserDto> userList=null;
    UserDao userDao= DaoFactory.getInstance().getDao(DaoType.USER_DAO);

    @Override
    public List<UserDto> assignToUserList() {
        try {
            List<User> userEntityList = userDao.getAllUsers();

            List<UserDto> userModelList = new LinkedList<>();

            for (User userEntity : userEntityList) {
                System.out.println(userEntity.toString() + "this is the user entity");
                userModelList.add(new UserDto(userEntity.getId(), userEntity.getUserName(), userEntity.getPassword(), userEntity.getDescription(), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(userEntity.getCreatedAt()), (userEntity.isStatus() ? "ACTIVE" : "INNACTIVE"), userEntity.getAuthorityLvl()));
            }
           return userModelList;

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("theres an error");
        }
        return null;
    }
    @Override
    public UserDto getLoggedInUser() {
        return loggedInUser;
    }

    @Override
    public boolean logout() {
        loggedInUser=null;
        return true;
    }

    @Override
    public boolean login(String username, String password) throws NoSuchAlgorithmException {
        String encryptedPassword = toHexString(getSHA(password));

        User temp = userDao.login(username, encryptedPassword);
        loggedInUser = new UserDto(temp.getId(), temp.getUserName(), temp.getPassword(),temp.getDescription(),new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(temp.getCreatedAt()),temp.isStatus()?"ACTIVE":"INNACTIVE",temp.getAuthorityLvl());

        if (temp == null) {
            new Alert(Alert.AlertType.ERROR, "Username or Password is Incorrect").show();

        } else {
            System.out.println("userId" + loggedInUser.getId() +
                    "username" + loggedInUser.getUserName());

            if (loggedInUser.getAuthorityLvl() == 1) {

            }
        }
        return temp != null;
    }
    public static byte[] getSHA(String input) throws NoSuchAlgorithmException {
        /* MessageDigest instance for hashing using SHA256 */
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        /* digest() method called to calculate message digest of an input and return array of byte */
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }

    public static String toHexString(byte[] hash) {
        /* Convert byte array of hash into digest */
        BigInteger number = new BigInteger(1, hash);

        /* Convert the digest into hex value */
        StringBuilder hexString = new StringBuilder(number.toString(16));

        /* Pad with leading zeros */
        while (hexString.length() < 32) {
            hexString.insert(0, '0');
        }

        return hexString.toString();
    }
    @Override
    public boolean registerUser(UserDto user) throws NoSuchAlgorithmException {
        return userDao.save(new User(user.getId(), user.getUserName(), toHexString(getSHA(user.getPassword())), true, user.getDescription(), user.getAuthorityLvl()));
    }
    @Override
    public List<UserDto> getUserList() {
        return userList!=null?userList:(userList=assignToUserList());
    }

}
