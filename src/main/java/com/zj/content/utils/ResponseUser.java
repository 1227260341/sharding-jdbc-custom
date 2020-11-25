package com.zj.content.utils;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseUser {

    private Integer count;
	private Integer code;
	private String message;

    public ResponseUser setAll(Integer code, String message, Integer count) {
        ResponseUser item = new ResponseUser();
        item.setCode(code);
        item.setCount(count);
        item.setMessage(message);
        return item;
    }
	
	
	
}
