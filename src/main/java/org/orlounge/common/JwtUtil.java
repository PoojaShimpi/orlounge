package org.orlounge.common;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.TextCodec;
import org.orlounge.common.util.ProcessData;
import org.orlounge.common.util.StringUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public abstract class JwtUtil {
    private static final String KEY = "Yn2kjibddFAWtnPJ2AFlL8WXmohJMCvigQggaEypa5E=";

    private JwtUtil(){

    }

    public static String createJwtToken(UserToken userToken){

        //Let's set the JWT Claims
        Date from = new Date();
        return Jwts.builder()
                .setId(StringUtil.getUUID())
                .setIssuedAt(new Date())
                .setSubject(userToken.getUserCode())
                .setIssuer("orlounge")
                .claim("userCode", userToken.getUserCode() )
                .claim("email", userToken.getUserCode() )
                .claim("role", userToken.getRole() )
                .claim("groupId", userToken.getCurrentGroupId() )
                .claim("groupName", userToken.getCurrentGroupName() )
                .claim("groupSize", userToken.getCurrentGroupSize() )
                .claim("userId", userToken.getUserId() )
                .claim("timezone", userToken.getTimeZone() )
                .claim("firstName", userToken.getFirstName() )
                .claim("lastName", userToken.getLastName() )
                .claim("ipAddress", userToken.getIpAddress())
                .claim("isPassExpired", userToken.getIsPassExpired())
                .claim("groupAccess", userToken.getGroupAccessList())
                .signWith(
                        SignatureAlgorithm.HS256,
                        TextCodec.BASE64.decode(KEY)
                ).compact();

    }

    public static UserToken decode(HttpServletRequest request){
        for(Cookie cookie : request.getCookies()){
            if(cookie.getName().equals("jwt")){
                return decode(cookie.getValue());
            }
        }
        return null;
    }
    public static UserToken decode(String jwt){

        Jws<Claims> jws = Jwts.parser()
                .setSigningKey( TextCodec.BASE64.decode(KEY))
                .parseClaimsJws(jwt);
        Claims claims = jws.getBody();


        //Let's set the JWT Claims
        UserToken token = new UserToken();
        token.setUserCode(claims.getSubject());
        token.setRole(claims.get("role", String.class));
        token.setCurrentGroupId(claims.get("groupId", Integer.class));
        token.setCurrentGroupName(claims.get("groupName", String.class) );
        token.setCurrentGroupSize(claims.get("groupSize", String.class) );
        token.setUserId(claims.get("userId", Integer.class));
        token.setTimeZone(claims.get("timezone", String.class));
        token.setFirstName(claims.get("firstName", String.class));
        token.setLastName(claims.get("lastName", String.class));
        token.setIpAddress(claims.get("ipAddress", String.class));
        token.setIsPassExpired(claims.get("isPassExpired", Boolean.class));

        List access = claims.get("groupAccess", List.class);
        if(ProcessData.isValid(access)){
            token.setGroupAccessList(access);
        }else {
            token.setGroupAccessList(Collections.emptyList());
        }


        return token;
    }
}
