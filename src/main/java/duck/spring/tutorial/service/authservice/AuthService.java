package duck.spring.tutorial.service.authservice;

import duck.spring.tutorial.dto.AuthRequestDto;
import duck.spring.tutorial.dto.AuthResponseDto;
import duck.spring.tutorial.exception.BadRequestException;
import duck.spring.tutorial.model.Role;
import duck.spring.tutorial.model.User;
import duck.spring.tutorial.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponseDto register(AuthRequestDto requestDto) throws BadRequestException {
        if (userRepository.existsByUsername(requestDto.getUsername())) {
            throw new BadRequestException("Username is already taken!");
        }

        var user = User.builder()
                .username(requestDto.getUsername())
                .password(passwordEncoder.encode(requestDto.getPassword()))
                .role(Role.USER)
                .build();

        userRepository.save(user);

        return AuthResponseDto.builder().token(jwtService.generateToken(user.getUsername())).build();
    }

    public AuthResponseDto login(AuthRequestDto requestDto) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requestDto.getUsername(), requestDto.getPassword()));
        var user = userRepository.findByUsername(requestDto.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return AuthResponseDto.builder().token(jwtService.generateToken(user.getUsername())).build();
    }
}
