package sys.service.Impl;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sys.dto.RegisterDTO;
import sys.entity.Role;
import sys.entity.Todo;
import sys.entity.User;
import sys.exception.TodoAPIException;
import sys.repository.RoleRepository;
import sys.repository.UserRepository;
import sys.service.AuthService;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {


    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public String Register(RegisterDTO registerDTO) {

        //check if username exists in database
        if(userRepository.existsByUsername(registerDTO.getUsername())) {
            throw new TodoAPIException(HttpStatus.BAD_REQUEST, "Username already exists!");
        }

        //check if email exists in database
        if(userRepository.existsByEmail(registerDTO.getEmail())){
            throw new TodoAPIException(HttpStatus.BAD_REQUEST, "Email already exists!");
        }

        User user = User.builder()
                .username(registerDTO.getUsername())
                .password(passwordEncoder.encode(registerDTO.getPassword()))
                .email(registerDTO.getEmail())
                .name(registerDTO.getName())
                .build();


        Set<Role> role = new HashSet<>();
        Role userRole = roleRepository.findByName("ROLE_USER").get();
        role.add(userRole);

        user.setRoles(role);

        userRepository.save(user);

        return "User Registered Successfully !";
    }
}
