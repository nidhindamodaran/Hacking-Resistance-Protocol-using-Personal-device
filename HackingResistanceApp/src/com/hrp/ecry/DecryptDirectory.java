package com.hrp.ecry;

import com.hrp.bean.FileInfoBean;
import com.hrp.util.AppVariables;
import java.io.File;
import java.util.LinkedHashMap;

public class DecryptDirectory implements Runnable {

    LinkedHashMap<String, FileInfoBean> fileInfos = null;

    public DecryptDirectory(LinkedHashMap<String, FileInfoBean> fileInfos) {
        this.fileInfos = fileInfos;
    }

    public void run() {
        Object[] fileObjects = fileInfos.values().toArray();
        for (Object object : fileObjects) {
            FileInfoBean infoBean = (FileInfoBean) object;
            File decryptFile = new AESEncryption().decryptFile(new File(infoBean.getFileName()),
                    AppVariables.settingsInfo.getIMEI());
            decryptFile.renameTo(new File(infoBean.getFileName()));
        }
    }
}
