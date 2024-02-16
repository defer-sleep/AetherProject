package com.example.demo.Responses.Message;

public class MessageResponse {
    public static final int BAD_REQUEST_CODE = 400, SUCCESS_CODE = 300;;
    private String message;
    private int code = BAD_REQUEST_CODE;

    public MessageResponse(String message) {
        this.message = message;
    }

    public MessageResponse(String message, int code) {
        this.message = message;
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
