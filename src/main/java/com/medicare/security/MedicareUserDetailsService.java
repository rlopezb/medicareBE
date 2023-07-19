package com.medicare.security;

import com.medicare.entity.User;
import com.medicare.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class MedicareUserDetailsService implements UserDetailsService {
  private final UserRepository userRepository;

  @Override
  public MedicareUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.getUserByUsername(username);
    if (user == null) {
      throw new UsernameNotFoundException("Could not find user '" + username + "'");
    }
    return new MedicareUserDetails(user);
  }
}
