package duck.spring.tutorial.controller;

import duck.spring.tutorial.dto.AuthRequestDto;
import duck.spring.tutorial.dto.AuthResponseDto;
import duck.spring.tutorial.exception.BadRequestException;
import duck.spring.tutorial.service.authservice.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDto> register(@RequestBody AuthRequestDto requestDto) throws BadRequestException {
        return ResponseEntity.ok(authService.register(requestDto));
    }

    @PostMapping("/login")
    public AuthResponseDto login(@RequestBody AuthRequestDto requestDto){
        return authService.login(requestDto);
    }
}