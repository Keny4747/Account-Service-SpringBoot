package account.service;

import account.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserDetailsImpl implements UserDetails {

    private final String userName;
    private final String password;
    private final List<GrantedAuthority> rolesAuthorities;

    public UserDetailsImpl(User user) {
        this.userName = user.getEmail();
        this.password = user.getPassword();
        this.rolesAuthorities = List.of(new SimpleGrantedAuthority("USER"));
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return rolesAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
