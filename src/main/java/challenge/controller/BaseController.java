package challenge.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/challenge")
public abstract class BaseController {
    protected Logger logger = LogManager.getLogger(getClass());
    protected static final String JSON_UTF8 = MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8";

    protected String getError(String msgError){
        this.logger.error(msgError);
        JSONObject jsonError = new JSONObject();
        jsonError.put("ErrorMessage", msgError);
        return jsonError.toString();
    }
}
