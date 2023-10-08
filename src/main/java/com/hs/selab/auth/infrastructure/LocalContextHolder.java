package com.hs.selab.auth.infrastructure;

import com.hs.selab.auth.domain.Authentication;
import com.hs.selab.auth.domain.UserDetail;
import com.hs.selab.member.domain.vo.RoleType;
import org.springframework.stereotype.Component;

@Component
public class LocalContextHolder {
    private static final ThreadLocal<Authentication> context = new ThreadLocal<>();

    public static Authentication getContext (){
        if(context.get() == null) {
            setContext(new Authentication
                    (new UserDetail(), RoleType.GUEST));
        }
        return context.get();
    }
    public static void setContext(Authentication authentication){
        context.set(authentication);
    }

    public static void remove(){
        context.remove();
    }
}
