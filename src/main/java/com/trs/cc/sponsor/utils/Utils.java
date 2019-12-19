package com.trs.cc.sponsor.utils;

import com.trs.cc.sponsor.exception.NotFoundException;
import com.trs.cc.sponsor.model.SponsorAPI;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

import static com.trs.cc.sponsor.constant.MessageConstants.TOKEN_NOT_FOUND;

public class Utils {

    public static int getAge(int birthYear){
        Calendar calendar=Calendar.getInstance();
        int year=calendar.get(Calendar.YEAR);
        return year-birthYear;
    }

    public static int getBirthYear(int age){
        Calendar calendar=Calendar.getInstance();
        int year=calendar.get(Calendar.YEAR);
        return year-age;
    }

    public static boolean validPhone(String phoneNumber) {
        String pattern = "\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}";

        if (Pattern.compile(pattern).matcher(phoneNumber).matches()) {
            return true;
        } else {
            return false;
        }
    }


    public static String generateUniqueId(int length) {
        String token_chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890abcdefghijklmnopqrstuvxyz";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < length) {
            int index = (int) (rnd.nextFloat() * token_chars.length());
            salt.append(token_chars.charAt(index));
        }
        return salt.toString();

    }


    /**
     * This method creates a list of method with its authorization
     * "getUserDetail" [USER, ADMIN, TECH_ADMIN]
     *
     * @param className
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static List<SponsorAPI> getAllMethodNames(Class className) {
        Method[] allMethods = className.getDeclaredMethods();
        List<SponsorAPI> apis = new ArrayList<SponsorAPI>();
        for (Method method : allMethods) {

            if (Modifier.isPublic(method.getModifiers())) {
                Access a = method.getAnnotation(Access.class);
                RequestMapping rm = method.getAnnotation(RequestMapping.class);
                ArrayList<Roles> authList = new ArrayList<Roles>(Arrays.asList(a.levels()));

                SponsorAPI api = new SponsorAPI();
                api.setName(rm.name());
                api.setRoles(authList);
                apis.add(api);

            }
        }
        return apis;
    }





    /**
     * Generate Today Date in String
     *
     * @return
     */
    public static String generateProfilePicName() {
        String todayDateInString = new SimpleDateFormat("ddMMyyyy").format(new Date());
        return todayDateInString;
    }

    public static String getTokenFromHeaders(HttpServletRequest request) throws NotFoundException {
        String jwtToken = request.getHeader(CustomHTTPHeaders.TOKEN.toString());
        if(jwtToken ==null){
            throw new NotFoundException(TOKEN_NOT_FOUND);
        }
        return jwtToken;
    }
}
