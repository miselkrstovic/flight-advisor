package com.github.flightadvisor.services;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

@Service
public class SecurityHelper {

    public int passwordStrength(String password) { 
        int passwordScore = 0;
        
        if( password.length() < 8 )
            return 0;
        else if( password.length() >= 10 )
            passwordScore += 2;
        else 
        	passwordScore += 1;
        
        if( password.matches("(?=.*[0-9]).*") )
        	passwordScore += 2;
        
        if( password.matches("(?=.*[a-z]).*") )
        	passwordScore += 2;
        
        if( password.matches("(?=.*[A-Z]).*") )
        	passwordScore += 2;    
        
        if( password.matches("(?=.*[~!@#$%^&*()_-]).*") )
        	passwordScore += 2;
        
        return passwordScore;
    }
	   
	public Pair<String, String> encode(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
		SecureRandom random = new SecureRandom();
		byte[] salt = new byte[16];
		random.nextBytes(salt);
		
		KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
		SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		
		byte[] hash = factory.generateSecret(spec).getEncoded();
		
		return Pair.of(bytesToBase64(hash), bytesToBase64(salt));
	}
	
	public boolean check(String password, String hash, String salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
		byte[] originalSalt = base64ToBytes(salt);
		byte[] previousHash = base64ToBytes(hash);
		
		KeySpec spec = new PBEKeySpec(password.toCharArray(), originalSalt, 65536, 128);
		SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		
		byte[] newHash = factory.generateSecret(spec).getEncoded();
		
		return Arrays.equals(previousHash, newHash);
	}
	
	private String bytesToBase64(byte[] bytes) {
		String encodedString = Base64.getEncoder().encodeToString(bytes);
        return encodedString;
	}

	private byte[] base64ToBytes(String encoded) {
		byte[] decodedBytes = Base64.getDecoder().decode(encoded);
        return decodedBytes;
	}
	
}
