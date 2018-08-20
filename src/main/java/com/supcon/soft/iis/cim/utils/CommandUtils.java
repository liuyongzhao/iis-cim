package com.supcon.soft.iis.cim.utils;

/**
 * Command Line Arguments Handler
 * @author qiyuqi
 */
public class CommandUtils {
    private static final String GLOBAL_SYS_PROFILE_KEY = "profile";

    /**
     * Setting Application Profile
     * @param args command line arguments
     * @author qiyuqi
     */
    public static void getAndSetSpringProfile(String[] args){
        if(args != null && args.length > 0){
            for(String arg : args){
                String p = parseSpringProfile(arg);
                if (p != null){
                    System.setProperty(GLOBAL_SYS_PROFILE_KEY, p);
                }
            }
        } else{
            System.setProperty(GLOBAL_SYS_PROFILE_KEY, Env.dev.name());
        }
    }

    private static String parseSpringProfile(String arg){
        String result = parseSpringProfile(arg, "-Dspring.profiles.active=");
        if(result == null){
            result = parseSpringProfile(arg, "--spring.profiles.active=");
        }
        return result;
    }

    private static String parseSpringProfile(String arg, String captain){
        if(!arg.startsWith(captain)){
            return null;
        }
        return arg.substring(captain.length());
    }
}
