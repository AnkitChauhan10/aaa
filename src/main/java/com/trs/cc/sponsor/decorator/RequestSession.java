package com.trs.cc.sponsor.decorator;


import com.trs.cc.sponsor.model.JWTUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Random;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class RequestSession {

    JWTUser jwtUser;

    int r;

    public RequestSession() {
        r = new Random().nextInt();
    }
}
