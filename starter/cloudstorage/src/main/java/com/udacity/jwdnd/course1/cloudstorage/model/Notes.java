package com.udacity.jwdnd.course1.cloudstorage.model;

public class Notes {
    private String noteTitle;
    private Integer userId;
    private Integer noteId;
    private String noteDescription;

    public Notes(Integer noteId, String noteTitle, String noteDescription, Integer userId) {
        this.noteId = noteId;
        this.noteTitle = noteTitle;
        this.noteDescription = noteDescription;
        this.userId = userId;
    }

    public String getNoteTitle() {return noteTitle; }

    public void setNoteTitle(String noteTitle) {this.noteTitle = noteTitle; }

    public Integer getUserId() { return userId; }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getNoteId() {
        return noteId;
    }

    public void setNoteId(Integer noteId) {
        this.noteId = noteId;
    }

    public String getNoteDescription() {
        return noteDescription;
    }

    public void setNoteDescription(String noteDescription) {
        this.noteDescription = noteDescription;
    }

//    @Override
//    public String toString() {
//        return "Notes{" +
//                "noteTitle='" + noteTitle + '\'' +
//                ", userId=" + userId +
//                ", noteId=" + noteId +
//                ", noteDescription='" + noteDescription + '\'' +
//                '}';
//    }
}
