package pages;

import java.util.Locale;
import java.util.ResourceBundle;

public class Translation {

	public static String[] languages= {"en","es"};
	public static String actual_language="en";

	public static void changeLanguage(String new_language) {
		String old_lang=actual_language;
		actual_language=new_language;
		languages[0]=actual_language;
		languages[1]=old_lang;
	}
	
	public static String getString(String string) {
		ResourceBundle resourceBundle = ResourceBundle.getBundle("SystemMessages", Locale.forLanguageTag(actual_language));
		return resourceBundle.getString(string);
	}
}
