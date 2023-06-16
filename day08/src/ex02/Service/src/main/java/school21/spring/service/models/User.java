package school21.spring.service.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    Long id;
    String email;
    String password;
    public User(){}

//    public User(String email, String password) {
//        this.email = email;
//        this.password = password;
//    }
}
