package sys.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sys.dto.RegisterDTO;
import sys.service.AuthService;

@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {


    private AuthService authService;

    //Build register rest API
    @PostMapping
    public ResponseEntity<String> register(@RequestBody RegisterDTO registerDto) {
        String result = authService.Register(registerDto);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }
}
