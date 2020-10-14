package challenge.exception;

import org.springframework.http.HttpStatus;

public class ServiceException extends Exception{

    private HttpStatus httpStatus;
    private String message;
    private Integer code;

    public ServiceException(HttpStatus httpStatus, String message) {
        this.code = httpStatus.value();
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public ServiceException(ErrorMessage errorMessage){
        this.code = errorMessage.getHttpStatus().value();
        this.httpStatus = errorMessage.getHttpStatus();
        this.message = errorMessage.getMessage();
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
