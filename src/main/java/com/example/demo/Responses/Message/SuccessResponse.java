package com.example.demo.Responses.Message;

public class SuccessResponse {
    private String status;
    private int code;

    public SuccessResponse(String status, int code) {
        this.status = status;
        this.code = code;
    }

    public SuccessResponse() {
        this.status = "Success";
        this.code = 300;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
