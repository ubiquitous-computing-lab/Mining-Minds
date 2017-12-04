/*
 Copyright [2016] [Taqdir Ali]

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

 */

package org.uclab.mm.datamodel.sl;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

/**
 *
 * @author Taqdir
 */
public class HashClass {
    protected static String hashinput;
    /**
     * The constructor of HashClass will take user id and device id and combine
     * it with current time stamp to generate unique input for hash function
     * @param uid user id
     * @param deviceid  device id
     */
     HashClass(String uid, String deviceid){
      String timestamp=Long.toString(System.currentTimeMillis());
       hashinput=uid+deviceid+timestamp;  
    }
    public HashClass(String uid){
      String timestamp=Long.toString(System.currentTimeMillis());
       hashinput=uid+timestamp;  
    }
    /**
     * 
     * @return  String value of SHA1 
     */
    
    
    public static String getHashSha1()
{
    //String timestamp=Long.toString(System.currentTimeMillis());
    //String hash_function_input=uid+deviceid+timestamp;
    String sha1 = "";
    try
    {
        MessageDigest crypt = MessageDigest.getInstance("SHA-1");
        crypt.reset();
        crypt.update(hashinput.getBytes("UTF-8"));
        sha1 = byteToHex(crypt.digest());
    }
    catch(NoSuchAlgorithmException e)
    {
        e.printStackTrace();
    }
    catch(UnsupportedEncodingException e)
    {
        e.printStackTrace();
    }
    return sha1;
}

private static String byteToHex(final byte[] hash)
{
    Formatter formatter = new Formatter();
    for (byte b : hash)
    {
        formatter.format("%02x", b);
    }
    String result = formatter.toString();
    formatter.close();
    return result;
}
//***********************
public String getHashMD5() {
        byte[] source;
        try {
            //Get byte according by specified coding.
            source = hashinput.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            source = hashinput.getBytes();
        }
        String MD5result = null;
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7',
                '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(source);
            //The result should be one 128 integer
            byte temp[] = md.digest();
            char str[] = new char[16 * 2];
            int k = 0;
            for (int i = 0; i < 16; i++) {
                byte byte0 = temp[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            MD5result = new String(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return MD5result;
    }
}
