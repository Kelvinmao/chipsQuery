package chipsmanager.tools;

import java.util.Base64;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author MaoKaining(毛凯宁)
 * 功 能:给密码做MD5加密
 *
 */
public class Md5Encode {
	public static String getMd5Code(String usrPwd){
		MessageDigest mDigest=null;
		Base64.Encoder bEncoder=Base64.getEncoder();
		String digestedUsrPwd=null;
		try{
			mDigest=MessageDigest.getInstance("md5");
			mDigest.update(usrPwd.getBytes());
			digestedUsrPwd=new String(bEncoder.encodeToString(mDigest.digest()));
		}catch(NoSuchAlgorithmException ex){
			System.out.println(ex.getMessage());
		}catch (NullPointerException e) {
			System.out.println(e.getMessage());
		}
		return digestedUsrPwd;
	}
}