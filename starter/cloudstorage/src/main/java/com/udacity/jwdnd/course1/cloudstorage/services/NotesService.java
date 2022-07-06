package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FilesMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.NotesMapper;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Files;
import com.udacity.jwdnd.course1.cloudstorage.model.Notes;
import com.udacity.jwdnd.course1.cloudstorage.model.User;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


// progrma flows from Controller to the service
// responsible for provides services like adding , updading , deleting the Notes details


@Service
public class NotesService {

    private final NotesMapper notesMapper;
    private final UserMapper userMapper;

    public NotesService(NotesMapper notesMapper, UserMapper userMapper) {
        this.userMapper = userMapper;
        this.notesMapper = notesMapper;
    }

    public int updateUserNotes(Notes notes) {
        //        System.out.println(notes);
        return notesMapper.updateUserNotes(notes.getNoteId(),
                notes.getNoteTitle(),
                notes.getNoteDescription());
    }

    public List<Notes> getUserNotes(Integer userId) {
        //        System.out.println(userId);
        List<Notes> note = notesMapper.getUserNotesByUserId(userId);
        return note;

    }

    public int addUserNotes(Notes notes, Authentication authentication) {
        User user = userMapper.getUserByName(authentication.getName());
        return notesMapper.insertUserNotes(new Notes(null,
                notes.getNoteTitle(),
                notes.getNoteDescription(),
                user.getUserId()));
    }

    public void deleteUserNotes(Integer noteId) {
        notesMapper.deleteUserNote(noteId);
    }


}
