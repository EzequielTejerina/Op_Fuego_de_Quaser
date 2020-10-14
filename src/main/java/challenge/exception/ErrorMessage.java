package challenge.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@JsonInclude(Include.NON_NULL)
public class ErrorMessage implements Serializable {

    private HttpStatus httpStatus;
    private String message;
    private Integer code;

    public ErrorMessage() {
    }

    public ErrorMessage(ServiceException ex) {
        this.httpStatus = ex.getHttpStatus();
        this.message = ex.getMessage();
        this.code = ex.getCode();
    }

    public ErrorMessage(HttpStatus httpStatus, String message, Integer code) {
        super();
        this.httpStatus = httpStatus;
        this.message = message;
        this.code = code;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
