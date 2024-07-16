package sys.service;

import sys.dto.LoginDTO;
import sys.dto.RegisterDTO;

public interface AuthService {
    String Register(RegisterDTO registerDTO);
    String Login(LoginDTO loginDTO);
}
