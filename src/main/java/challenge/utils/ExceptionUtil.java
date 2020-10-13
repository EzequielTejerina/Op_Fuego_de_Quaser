package challenge.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionUtil {

    public static String getStackTra(Throwable ex){
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw, true);
        ex.printStackTrace(pw);
        return sw.getBuffer().toString();
    }
}
