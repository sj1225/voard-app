package com.asteroid.voardapp.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserSysInfo implements UserDetails {
    private String user_id;     // ID
    private String user_nm;     // 이름
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)  // 쓰기전용
    private String password;    // 비밀번호

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.user_id;
    }

    /**
     * 계정 만료여부를 반환하는 함수
     * @return 계정 만료여부
     */
    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 계정 잠김여부를 반환하는 함수 (비밀번호 많이 틀리는 경우)
     * @return 계정 잠김여부
     */
    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    /**
     * 자격 증명 만료여부를 반환하는 함수 (비밀번호 오래 안바꾼 경우)
     * @return 자격 증명 만료여부
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    /**
     * 계정 사용 가능여부를 반환하는 함수
     * @return 계정 사용 가능여부
     */
    @Override
    public boolean isEnabled() {
        return isAccountNonExpired() && isAccountNonLocked() && isCredentialsNonExpired();
    }
}

