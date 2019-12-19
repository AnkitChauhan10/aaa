package com.trs.cc.sponsor.utils;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.trs.cc.notification.exception.NotFoundException;
import com.trs.cc.notification.model.NotificationAPI;
import com.trs.cc.notification.model.NotificationAdminConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

import static com.trs.cc.notification.constant.MessageConstants.TOKEN_NOT_FOUND;

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

    public static boolean validateEmailAddress(String emailAddress) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(emailAddress);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
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
    public static List<NotificationAPI> getAllMethodNames(Class className) {
        Method[] allMethods = className.getDeclaredMethods();
        List<NotificationAPI> apis = new ArrayList<NotificationAPI>();
        for (Method method : allMethods) {

            if (Modifier.isPublic(method.getModifiers())) {
                Access a = method.getAnnotation(Access.class);
                RequestMapping rm = method.getAnnotation(RequestMapping.class);
                ArrayList<Roles> authList = new ArrayList<Roles>(Arrays.asList(a.levels()));

                NotificationAPI api = new NotificationAPI();
                api.setName(rm.name());
                api.setRoles(authList);
                apis.add(api);

            }
        }
        return apis;
    }

    /**
     * This method is for file upload and Return Path in BitBucket.
     *
     * @param configurations
     * @param imageName
     * @param imageByteArray
     * @return
     */
    public static String storeImageInAwsS3(NotificationAdminConfiguration configurations, final String imageName, final byte[] imageByteArray) {
        AWSCredentials credentials = new BasicAWSCredentials(configurations.getFileUploadAccessKey(),
                configurations.getFileUploadSecretKey());
        AmazonS3 conn = new AmazonS3Client(credentials);
        InputStream stream = new ByteArrayInputStream(imageByteArray);
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(imageByteArray.length);
        metadata.setCacheControl("public, max-age=31536000");
        conn.putObject(new PutObjectRequest(configurations.getBucketName(), imageName, stream, metadata)
                .withCannedAcl(CannedAccessControlList.PublicRead));
        return ((AmazonS3Client) conn).getResourceUrl(configurations.getBucketName(), imageName);
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
