package com.trs.cc.discountcode.model;


import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JWTUser {
    private enum ClaimFiledNames{
        ID,ROLE
    }

    String id;
    List<String> role;


    public Map<String, Object> toClaim(){
        Map<String, Object> claim   = new HashMap<>();
        claim.put(ClaimFiledNames.ID.toString(),id);
        claim.put(ClaimFiledNames.ROLE.toString(),role);
        return claim;
    }


    public static JWTUser fromClaim(Claims claim){
        JWTUser jwtUser = new JWTUser();
        jwtUser.setId((String) claim.get(ClaimFiledNames.ID.toString()));
        jwtUser.setRole((List<String>) claim.get(ClaimFiledNames.ROLE.toString()));
        return jwtUser;
    }
}
