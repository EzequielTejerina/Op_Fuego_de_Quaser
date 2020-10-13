package challenge.utils;

import java.util.Arrays;
import java.util.List;

public class ArrayUtil {

    public static String toString(String... array){
        String message = "[";
        List<String> mappingMessages = Arrays.asList(array);
        for(String word : mappingMessages){
            if(message.isEmpty()){
                message = message.concat(word);
            }else{
                message = message.concat(", ").concat(word);
            }
        }
        message = message.concat("]");
        return message;
    }
}
