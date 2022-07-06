package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FilesMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Files;
import com.udacity.jwdnd.course1.cloudstorage.model.User;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;




// progrma flows from Controller to the service
// responsible for provides services like adding , updading , deleting the Notes details


@Service
public class FileService {

    private final FilesMapper filesMapper;
    private final UserMapper userMapper;

    public FileService(FilesMapper filesMapper, UserMapper userMapper) {
        this.userMapper = userMapper;
        this.filesMapper = filesMapper;
    }

    public boolean isFileNamePresent(String filename) {
        //        System.out.println(filename);
        Boolean bool = filesMapper.getting_A_File(filename) == null;
        return bool;
    }

    public int addingFilesToCloud(MultipartFile file, Authentication authentication) throws IOException {
        User user = userMapper.getUserByName(authentication.getName());
        //        System.out.println(user);
        return filesMapper.FileInsert(new Files(null,
                file.getOriginalFilename(),
                file.getContentType(),
                file.getSize(),
                user.getUserId(),
                file.getBytes() ));
    }

    public List<Files> gettingUserFilesFromCloud(Integer userid) {
        List<Files> userFile = (filesMapper.getAllUserFiles(userid));
        return userFile;
    }

    public void deletingFilesInCloud(String NameOfTheFile) {
        filesMapper.deleting_A_File(NameOfTheFile);
    }

    public Files downloadingFilesFromCloud(String NameOfTheFile) {
        Files userFile = filesMapper.getting_A_File(NameOfTheFile);
        return userFile;
    }
}
