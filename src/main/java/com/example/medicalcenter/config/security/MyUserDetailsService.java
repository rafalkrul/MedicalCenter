package com.example.medicalcenter.config.security;




import com.example.medicalcenter.repository.UserDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MyUserDetailsService implements UserDetailsService {
    private final UserDataRepository userDataRepository;

    @Override
    public MyUserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var user = userDataRepository.findByEmail(email);
        if(user.getPassword().isEmpty()){
            throw new RuntimeException("user not found");
        }

        return new MyUserDetails(user);

    }
}