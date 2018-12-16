package com.yangliuxin.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class WeChatAuthorizeException extends Exception {

    private static final long serialVersionUID = 3174597560099309194L;

    public WeChatAuthorizeException(String message) {
        super(message);
    }
}
