package fr.valtech.angularspring.security;

/**
 * Created by cliff.maury on 03/11/2014.
 */
public final class Authorities {

    private final static String PREFIX = "ROLE_";
    public static final String ADMIN = PREFIX + "ADMIN";
    public static final String USER = PREFIX + "USER";

    private Authorities() {

    }

}
