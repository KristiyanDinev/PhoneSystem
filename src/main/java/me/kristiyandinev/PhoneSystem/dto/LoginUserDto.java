package me.kristiyandinev.PhoneSystem.dto;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
@Builder
public class LoginUserDto {

    public String email;

    public String password;

    @Override
    public String toString() {
        return "LoginUserDto(email="+email+", password="+password+")";
    }
}
