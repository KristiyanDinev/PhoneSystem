package me.kristiyandinev.PhoneSystem.models;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
@Builder
public class RegisterUserModel {

    public String name;

    public String email;

    public String password;

    @Override
    public String toString() {
        return "RegisterUserModel(name="+name+", email="+email+", password="+password+")";
    }
}
