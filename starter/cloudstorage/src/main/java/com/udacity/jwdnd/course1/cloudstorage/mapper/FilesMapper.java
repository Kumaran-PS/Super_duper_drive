package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Files;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FilesMapper {

    @Select("SELECT * FROM FILES WHERE filename = #{filename}")
    Files getting_A_File(String fileName);

    @Select("SELECT * FROM FILES WHERE userid = #{userid}")
    List<Files> getAllUserFiles(Integer userId);

    @Insert("INSERT INTO FILES (filename,contenttype,filesize,filedata,userid) VALUES(#{fileName},#{contentType},#{fileSize},#{fileData},#{userId})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int FileInsert(Files File);

    @Delete("DELETE FROM FILES WHERE filename=#{filename}")
    void deleting_A_File(String fileName);
}
