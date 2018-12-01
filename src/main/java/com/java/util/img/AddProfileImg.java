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
    public static void addProfileImg(PersonInfo personInfo, File profileImg) throws FileNotFoundException {
        String desc = FileUtils.getPersonInfoImagePath();

        InputStream is = new FileInputStream(profileImg);

        ImageHolder imageHolder = new ImageHolder(profileImg.getName(), is);

        String profileImgAddr = ImageUtils.generateThumbnail(imageHolder, desc);

        personInfo.setProfileImg(profileImgAddr);
    }
}