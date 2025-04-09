package me.kristiyandinev.PhoneSystem.dto;


import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
@Builder
public class RegisterUserDto {

    public String name;

    public String email;

    public String password;

    @Override
    public String toString() {
        return "RegisterUserDto(name="+name+", email="+email+", password="+password+")";
    }
}
