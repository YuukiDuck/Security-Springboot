package duck.spring.tutorial.service;

import duck.spring.tutorial.dto.AuthRequestDto;
import duck.spring.tutorial.dto.AuthResponseDto;
import duck.spring.tutorial.model.ERole;
import duck.spring.tutorial.model.User;
import duck.spring.tutorial.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponseDto register(AuthRequestDto requestDto) {
        var user = User.builder()
                .email(requestDto.getEmail())
                .password(passwordEncoder.encode(requestDto.getPassword()))
                .role(ERole.USER)
                .build();

        userRepository.save(user);

        return AuthResponseDto.builder().token(jwtService.generateToken(user.getEmail())).build();
    }

    public AuthResponseDto login(AuthRequestDto requestDto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requestDto.getEmail(), requestDto.getPassword()));
        var user = userRepository.findByEmail(requestDto.getEmail());
        return AuthResponseDto.builder().token(jwtService.generateToken(user.get().getEmail())).build();
    }

    public boolean authenticateUser(String email, String password) {
        Optional<User> optionalUser = userRepository.findByEmail(email);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return passwordEncoder.matches(password, user.getPassword());
        }
        return false;
    }
}

