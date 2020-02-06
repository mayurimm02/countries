package com.amazing.countries.security;

public class JwtProperties {
	public static final String SECRET = "jwttokengeneration";
	public static final int EXPIRATION_TIME = 864000000;// equals to 10 days
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";

}
