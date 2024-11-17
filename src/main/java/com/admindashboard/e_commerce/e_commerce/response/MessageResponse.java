package com.admindashboard.e_commerce.e_commerce.response;

import com.admindashboard.e_commerce.e_commerce.allenum.ResponseType;
import lombok.Data;

@Data
public class MessageResponse {
    private String message;
    private String data;
    private ResponseType responseType;

    public MessageResponse(String message, ResponseType responseType) {
        this.message = message;
        this.responseType = responseType;
    }
}
