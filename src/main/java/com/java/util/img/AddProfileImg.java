package com.java.util.img;

import com.java.dto.other.ImageHolder;
import com.java.entity.PersonInfo;
import com.java.util.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * @author:zhumeng
 * @desc:
 **/
public class AddProfileImg {
    public static void addProfileImg(PersonInfo personInfo, ImageHolder profileImg) throws FileNotFoundException {
        String desc = FileUtils.getPersonInfoImagePath();

        String profileImgAddr = ImageUtils.generateThumbnail(profileImg, desc);

        personInfo.setProfileImg(profileImgAddr);
    }
}