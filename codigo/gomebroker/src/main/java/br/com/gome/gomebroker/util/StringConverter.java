package br.com.gome.gomebroker.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
*
* @author bruno
*/
public class StringConverter {
   
   private StringConverter() {
       
   }
   
	public static Date convert(String value, String pattern) {

		try {

			return new SimpleDateFormat(pattern).parse(value);
			
		} catch (ParseException e) {

			throw new RuntimeException("Cannot convert from " 
		               + value.getClass().getName() + " to " + Date.class.getName()
		               + ". Conversion failed with " + e.getMessage(), e);
			
		}

	}
   
   public static <T> T convert(String from, Class<T> to) {

       if (from == null) {
           
           return null;
           
       }

       from = from.trim();
       
       if (to.isAssignableFrom(from.getClass())) {
           
           return to.cast(from);
           
       }
       
       try {
       
           return to.getConstructor(String.class).newInstance(from);
           
       } catch (Exception e) {
           
           throw new RuntimeException("Cannot convert from " 
               + from.getClass().getName() + " to " + to.getName()
               + ". Conversion failed with " + e.getMessage(), e);
           
       }
       
   }
   
}