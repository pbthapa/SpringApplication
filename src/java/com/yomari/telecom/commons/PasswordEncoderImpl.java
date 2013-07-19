/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yomari.telecom.commons;

import org.springframework.security.authentication.encoding.ShaPasswordEncoder;

/**
 *
 * @author Dell
 */
public class PasswordEncoderImpl extends ShaPasswordEncoder {

    @Override
    public String encodePassword(String rawPass, Object salt) {
        ShaPasswordEncoder d = new ShaPasswordEncoder();
       // d.setEncodeHashAsBase64(true);
        String ss = d.encodePassword(rawPass, salt);
        System.out.println(ss);
        return ss;
    }

    public static void main(String[] args) {
        PasswordEncoderImpl i = new PasswordEncoderImpl();
        i.encodePassword("pushpa", null);
    }
}
