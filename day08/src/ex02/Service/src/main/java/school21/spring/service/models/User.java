package school21.spring.service.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    long id;
    String email;
    String password;
    public User(){}

}
