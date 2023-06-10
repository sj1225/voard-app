package com.asteroid.voardapp.security;

import com.asteroid.voardapp.mapper.SecurityMapper;
import com.asteroid.voardapp.model.UserSysInfo;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;

/* 인증 토큰을 생성함 */
@Slf4j
@RequiredArgsConstructor
@Component
public class JwtTokenProvider {
    @Value("${jwt.secret}")
    private String secretKey;                                               // 비밀키.

    private final long ACCESS_TOKEN_VALID_TIME = 60 * 60 * 1000L;          // 토큰 유효시간. 10분.

    private final long REFRESH_TOKEN_VALID_TIME = 60 * 60 * 1000L * 24;    // 리프레시 토큰 유효시간. 24시간.

    @Autowired
    SecurityMapper securityMapper;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }   // 비밀키 인코딩

    // JWT 토큰 생성
    public String createJwtAccessToken(UserSysInfo user) {
        Date now = new Date();

        // claim: JWT payload 에 저장되는 정보단위
        Claims claims = Jwts.claims().setSubject(user.getUser_id().toString()); // user_id

        // 토큰 생성
        return Jwts.builder()
                .setClaims(claims)      // 정보 저장
                .setIssuedAt(now)       // 토큰 발행 시간
                .setExpiration(new Date(now.getTime() + ACCESS_TOKEN_VALID_TIME)) // 토큰 만료 기간
                .signWith(SignatureAlgorithm.HS256, secretKey)  // 암호화 알고리즘, 비밀키 설정
                .compact();
    }

    // JWT 리프레시 토큰 생성
    public String createJwtRefreshToken(UserSysInfo user, String uuidValue, String ip) {
        Date now = new Date();

        Claims claims = Jwts.claims().setSubject(user.getUser_id().toString());  // user_id
        //claims.put("value", uuidValue); // Universally unique identifier는 전세계 유일 ID번호

        // 토큰 생성하기
        return Jwts.builder()
                .setClaims(claims)      // 정보 저장
                .setIssuedAt(now)       // 토큰 발행 시간
                .setExpiration(new Date(now.getTime() + REFRESH_TOKEN_VALID_TIME)) // 토큰 만료 기간
                .signWith(SignatureAlgorithm.HS256, secretKey)  // 암호화 알고리즘, 비밀키 설정
                .compact();
    }

    /**
     * 토큰에서 아이디 정보 찾음
     * @param token 토큰
     * @return 아이디
     */
    public String getUserId(String token) {
        Claims claims =  Jwts.parser()
                            .setSigningKey(secretKey)
                            .parseClaimsJws(token)
                            .getBody();

        String user_id = claims.getSubject().toString();

        return user_id;
    }

    /**
     * 유효한 토큰인지 검증
     * @param token 토큰
     * @return 유효 여부
     */
    public Boolean validateToken(String token) {
        if (!StringUtils.isEmpty(token)) {
            try {
                Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
                return true;
            } catch (MalformedJwtException e) {
                log.error("잘못된 토큰입니다.");
            } catch (ExpiredJwtException e) {
                log.error("만료된 토큰입니다.");
            } catch (UnsupportedJwtException e) {
                log.error("지원되지 않는 토큰입니다.");
            } catch (IllegalArgumentException ex) {
                log.error("토큰이 비어있습니다.");
            } catch (NullPointerException ex){
                log.error("리프레시 토큰이 비어있습니다.");
            } catch (Exception e) {
                log.error("정상적이지 않은 토큰입니다. ======>" + e.toString());
            }
        }
        return false;
    }

    // JWT 토큰에서 인증 정보 조회
    public Authentication getAuthentication(String token) {
        String user_id =  getUserId(token);

        UserSysInfo userInfo = 	new UserSysInfo() ;
        userInfo.setUser_id(user_id);

        UserDetails userDetails = loadUserByUsername(user_id);

        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    /**
     * 유저 아이디로 정보 조회해 반환
     * @param user_id 유저 아이디
     * @return 유저 정보
     * @throws UsernameNotFoundException
     */
    public UserDetails loadUserByUsername(String user_id) throws UsernameNotFoundException {
        UserSysInfo principal = securityMapper.selectByUserId(user_id);

        if (principal == null) {
            throw new UsernameNotFoundException("User Not Found");
        }

        return principal;
    }

    // Request의 Header에서 token 값을 가져옴. "X-AUTH-TOKEN" : "TOKEN값'
    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("X-AUTH-TOKEN");
    }
}