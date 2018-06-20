package com.example.dell.newscenter.utils;

public class UnicodeUtil {
/**
 * 中文转Unicode
 * @param gbString
 * @return
 */
static	public String gbEncoding(final String gbString) {
		char[] utfBytes = gbString.toCharArray();
		String unicodeBytes="";
		for (int btyeIndex = 0; btyeIndex < utfBytes.length; btyeIndex++) {
			String hexB = Integer.toHexString(utfBytes[btyeIndex]);
			if (hexB.length()<=2) {
				hexB="00"+hexB;
			}
			unicodeBytes = unicodeBytes+"\\u"+hexB;
		}
		System.out.println("unicodeBytes is " +unicodeBytes);
		return unicodeBytes;
	}
/**
 * Unicode 转中文
 * @param dataStr
 * @return
 */
static public String decodeUnicode(final String dataStr) {
	int start = 0;
	int end = 0;
	final StringBuffer buffer = new StringBuffer();
	while(start>-1) {
		end = dataStr.indexOf("\\u",start+2);
		String charStr = "";
		if (end==1) {
			charStr = dataStr.substring(start+2,dataStr.length());
		}else {
				char letter = (char) Integer.parseInt(charStr,16);
			}
			char letter = (char) Integer.parseInt(charStr,16);
			buffer.append(new Character(letter).toString());
			start = end;
		}
		return buffer.toString();
	
}	
public static void main(String[] args) {

   String aString = 1+"3"+5+"7";

   System.out.println(aString);
}
}
