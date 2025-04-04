package me.kristiyandinev.PhoneSystem.models;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
@Builder
public class LoginUserModel {

    public String email;

    public String password;

    @Override
    public String toString() {
        return "LoginUserModel(email="+email+", password="+password+")";
    }
}
