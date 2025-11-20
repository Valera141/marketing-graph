// En: src/main/java/com/example/Marketing/service/UserServiceImpl.java

package com.example.Marketing.service;

import com.example.Marketing.dto.UserLoginRequest;
import com.example.Marketing.dto.UserLoginResponse;
import com.example.Marketing.model.User;
import com.example.Marketing.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    @Override
    public UserLoginResponse login(UserLoginRequest request) {
        
        // --- INICIO DE CÓDIGO DE DEBUG ---
        System.out.println("=========================================");
        System.out.println("INTENTO DE LOGIN PARA: " + request.getEmail());
        System.out.println("PASSWORD RECIBIDO: " + request.getPassword());
        
        try {
            // 1. Valida el usuario y contraseña contra la BD
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
        
        } catch (Exception e) {
            // Si la autenticación falla, imprimimos el error
            System.out.println("¡ERROR DE AUTENTICACIÓN!: " + e.getMessage());
            System.out.println("=========================================");
            throw e; // Relanzamos la excepción para que devuelva el 401
        }
        
        System.out.println("¡AUTENTICACIÓN EXITOSA!");
        System.out.println("=========================================");
        // --- FIN DE CÓDIGO DE DEBUG ---

        // 2. Si la autenticación es exitosa, busca al usuario
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        
        // 3. Genera un token JWT para ese usuario
        String token = jwtService.generateToken(user);

        // 4. Devuelve el token y el nombre del usuario
        return UserLoginResponse.builder()
                .token(token)
                .fullName(user.getFullName())
                .build();
    }
}