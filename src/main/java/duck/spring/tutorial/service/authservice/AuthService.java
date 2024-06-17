//package duck.spring.tutorial.service.authservice;
//
//import duck.spring.tutorial.dto.AuthRequestDto;
//import duck.spring.tutorial.dto.AuthResponseDto;
//import duck.spring.tutorial.exception.BadRequestException;
//import duck.spring.tutorial.jwt.JwtFilter;
//import duck.spring.tutorial.model.ERole;
//import duck.spring.tutorial.model.User;
//import duck.spring.tutorial.repository.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.transaction.annotation.Transactional;
//
//@Service
//@RequiredArgsConstructor
//public class AuthService {
//
//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//    private final JwtService jwtService;
//    private final AuthenticationManager authenticationManager;
//    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);
//
//    @Transactional
//    public AuthResponseDto register(AuthRequestDto requestDto) throws BadRequestException {
//        if (userRepository.existsByUsername(requestDto.getUsername())) {
//            throw new BadRequestException("Username is already taken!");
//        }
//
//        User user = User.builder()
//                .username(requestDto.getUsername())
//                .password(passwordEncoder.encode(requestDto.getPassword()))
//                .role(ERole.USER)
//                .build();
//
//        userRepository.save(user);
//
//        return AuthResponseDto.builder().token(jwtService.generateToken(user.getUsername())).build();
//    }
//    //    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////        auth.inMemoryAuthentication()
////                .withUser("admin@gmail.com")
////                .password(passwordEncoder().encode("admin"))
////                .roles("ADMIN");
////    }
//
//    @Transactional
//    public AuthResponseDto login(AuthRequestDto requestDto) {
//        try {
//            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requestDto.getUsername(), requestDto.getPassword()));
//            logger.debug("User logged in successfully: {}", requestDto.getUsername());
//        } catch (AuthenticationException e) {
//            throw new BadCredentialsException("Invalid username or password");
//        }
//
//        User user = userRepository.findByUsername(requestDto.getUsername())
//                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + requestDto.getUsername()));
//
//        String token = jwtService.generateToken(user.getUsername());
//
//        return AuthResponseDto.builder().token(token).build();
//    }
//}