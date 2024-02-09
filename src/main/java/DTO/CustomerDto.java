package DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerDto {

    private int id;
    private String email;
    private String name;
    private String phone;

    public CustomerDto(String email, String name, String phone) {
        this.email = email;
        this.name = name;
        this.phone = phone;
    }
}
