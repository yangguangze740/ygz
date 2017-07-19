package luju.common.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Util {

    /**
     * 把一个字符串转成md5字符串
     *
     * @param beMd5ed 需要转换的字符串
     * @return 转换后的md5字符串
     */
    public static String getMD5String(String beMd5ed) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(beMd5ed.getBytes("UTF-8"));
            byte[] photoByte = md.digest();

            StringBuffer hexString = new StringBuffer();
            for (byte aPhotoByte : photoByte) {
                String hex = Integer.toHexString(0xff & aPhotoByte);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();

            return beMd5ed;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();

            return beMd5ed;
        }
    }
}
