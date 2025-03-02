package com.crown.employee.attendance_register.data;

public class ResponseDto<T> {
    private String statusCode;
    private String message;
    private T data;

    public ResponseDto() {
    }

    public ResponseDto(String statusCode, String message, T data) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

   public static <T> ResponseDto<T> success(String message, T data) {
        return new ResponseDto<>("00", message, data);
    }

    public static <T> ResponseDto<T> error(String message) {
        return new ResponseDto<>("99", message, null);
    }
}
