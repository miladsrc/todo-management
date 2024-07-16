package sys.service;

import sys.dto.RegisterDTO;

public interface AuthService {
    String Register(RegisterDTO registerDTO);
}
