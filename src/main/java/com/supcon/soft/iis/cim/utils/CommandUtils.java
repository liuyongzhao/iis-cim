package com.supcon.soft.iis.cim.utils;


public class CommandUtils {
    private static final String GLOBAL_SYS_PROFILE_KEY = "profile";

    public static String getAndSetSpringProfile(String[] args){
        String profile = null;
        if(args != null && args.length > 0){
            for(String arg : args){
                String p = parseSpringProfile(arg);
                if (p != null){
                    profile = p;
                    System.setProperty(GLOBAL_SYS_PROFILE_KEY, profile);
                }
            }
        }
        if(profile == null){
            System.setProperty(GLOBAL_SYS_PROFILE_KEY, Env.dev.name());
        }
        return profile;
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
