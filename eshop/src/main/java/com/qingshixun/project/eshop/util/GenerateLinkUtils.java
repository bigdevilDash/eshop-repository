/*****************************************************************************
 * Copyright (c) 2015, www.qingshixun.com
 *
 * All rights reserved
 *
 *****************************************************************************/
package com.qingshixun.project.eshop.util;

import com.qingshixun.project.eshop.core.Constants;
import com.qingshixun.project.eshop.dto.MemberDTO;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/** 
 * 生成帐户激活、重新设置密码的链接 
 */
public class GenerateLinkUtils {

    /** 
     * 生成帐户激活链接 
     * @throws Exception 
     */
    public static String generateActivateLink(MemberDTO member, String path) throws Exception {
        return path + "/front/member/activate/" + CryptoUtils.aesEncryptUrl(String.valueOf(member.getId()), Constants.KEY) + "/" + generateCheckcode(member);
    }

    /** 
     * 生成重设密码的链接 
     * @throws Exception 
     */
    public static String generateResetPwdLink(MemberDTO member, String path) throws Exception {
        return path + "/admin/teacher/find/password/form/" + CryptoUtils.aesEncryptUrl(member.getEmail(), Constants.KEY) + "/" + generateCheckcode(member);
    }

    /** 
     * 生成验证帐户的MD5校验码 
     * @return 将用户名和密码组合后，通过md5加密后的16进制格式的字符串
     */
    public static String generateCheckcode(MemberDTO member) {
        String teacherName = member.getUserName();
        String validateCode = member.getValidateCode();
        return md5(teacherName + ":" + validateCode);
    }

    /** 
     * 验证校验码是否和注册时发送的验证码一致 
     * @param member 要激活的帐户 
     * @param validateCode 注册时发送的校验码 
     * @return 如果一致返回true，否则返回false 
     */
    public static boolean verifyCheckcode(MemberDTO member, String validateCode) {
        return generateCheckcode(member).equals(validateCode);
    }

    private static String md5(String string) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("md5");
            md.update(string.getBytes());
            byte[] md5Bytes = md.digest();
            return bytes2Hex(md5Bytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static String bytes2Hex(byte[] byteArray) {
        StringBuffer strBuf = new StringBuffer();
        for (int i = 0; i < byteArray.length; i++) {
            if (byteArray[i] >= 0 && byteArray[i] < 16) {
                strBuf.append("0");
            }
            strBuf.append(Integer.toHexString(byteArray[i] & 0xFF));
        }
        return strBuf.toString();
    }
}
