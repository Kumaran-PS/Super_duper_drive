package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialsMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.model.Notes;
import com.udacity.jwdnd.course1.cloudstorage.model.User;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;
import java.util.UUID;





// progrma flows from Controller to the service
// responsible for provides services like adding , updading , deleting the Credential details

@Service
public class CredentialsService {

    private final CredentialsMapper credentialsMapper;
    private final UserMapper userMapper;
    private final EncryptionService encryptionService;

    public CredentialsService(CredentialsMapper credentialsMapper, UserMapper userMapper, EncryptionService encryptionService) {
        this.userMapper = userMapper;
        this.credentialsMapper = credentialsMapper;
        this.encryptionService = encryptionService;
    }

    public int updateUserCredentials(Credentials credentials) {
        String keys = credentials.getKey();
        String encryptedPassword = encryptionService.encryptValue(credentials.getPassword(), keys);
        return credentialsMapper.updateCredential(credentials.getCredentialsId(),
                credentials.getUrl(),
                credentials.getUserName(),
                encryptedPassword);
    }

    public Credentials getUserCredentialByCredentialId(Integer credentialId) {
        Credentials credential = credentialsMapper.getCredential_ById(credentialId);
        //        System.out.println(credential);
        return credential;
    }

    public List<Credentials> getUserCredentialsByUserId(Integer userid) {
        List<Credentials> credential = credentialsMapper.getUserCredentials(userid);
        return credential;
    }

    public int addUserCredentials(Credentials credentials, Authentication authentications) {
        String key = Salt();
        String encryptedPassword = encryptionService.encryptValue(credentials.getPassword(), key);
        User user = userMapper.getUserByName(authentications.getName());
        //        System.out.println(user);
        return credentialsMapper.insertCredential(new Credentials(null,
                credentials.getUrl(),
                credentials.getUserName(),
                key,
                encryptedPassword,
                user.getUserId()));
    }

    private String Salt() {
        SecureRandom rand = new SecureRandom();
        byte[] salt = new byte[16];
        rand.nextBytes(salt);
        String Keys = Base64.getEncoder().encodeToString(salt);
        return Keys;
    }
    public void deleteUserCredentials(Integer credentialsId) {
        credentialsMapper.deleteCredential(credentialsId);
    }





}
