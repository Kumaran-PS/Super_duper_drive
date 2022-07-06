package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Notes;
import com.udacity.jwdnd.course1.cloudstorage.services.NotesService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;


// program flows from html output window to the the controller `
// create , update , delete --- Notes

@Controller
@RequestMapping("/notes")
public class NotesController {

    private NotesService notesService;

    public NotesController(NotesService notesService) {
        this.notesService = notesService;
    }

    @PostMapping("/save")
    public String C_NotesSaveAndUpdate(@ModelAttribute("notes") Notes notes, Model model, Authentication authentication) throws IOException {
        String error = null;
        Integer numberOfNotes;

        switch (notes.getNoteId()) {
            case 0:
                numberOfNotes = notesService.addUserNotes(notes, authentication);
//                System.out.println("notes");
                if (numberOfNotes < 0) error = "Error occurred while adding notes.";
                break;
            default:
                numberOfNotes = notesService.updateUserNotes(notes);
//                System.out.println("notes");
                if (numberOfNotes < 0) error = "Error occurred while updating notes.";
                break;
        }
        if (error != null) model.addAttribute("error", error);
        else {
            Boolean bool = true;
            model.addAttribute("success", bool);
        }
        model.addAttribute("activeTab", "notes");
        return "result";
    }


    @GetMapping("/delete/{noteId}")
    public String C_NotesDelete(@PathVariable Integer noteId, Model model) {
        notesService.deleteUserNotes(noteId);
        model.addAttribute("activeTab", "notes");
        Boolean bool = true;
        model.addAttribute("success", bool);

        return "result";
    }
}
