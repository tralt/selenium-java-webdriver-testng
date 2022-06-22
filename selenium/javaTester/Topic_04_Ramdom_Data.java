package javaTester;

import org.apache.commons.lang3.RandomStringUtils;

public class Topic_04_Ramdom_Data {
	
	public static String getRandomString(int length){
        return RandomStringUtils.randomAlphanumeric(length).toUpperCase();
    }
	
	public static String getRandomNumber(int length){
        return RandomStringUtils.randomNumeric(length);
    }

}
