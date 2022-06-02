package data;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserData {

    private String email;
    private String password;

    public static class UserDataBuilder {

         public UserDataBuilder generateFirstModel() {
             this.email = "nickwtwfu+888@gmail.com";
             this.password = "Abcd1234!";
             return this;
         }

        public UserDataBuilder generateSecondModel() {
            this.email = "nickwtwfu+777@gmail.com";
            this.password = "Abcd1234!";
            return this;
        }

    }
}
