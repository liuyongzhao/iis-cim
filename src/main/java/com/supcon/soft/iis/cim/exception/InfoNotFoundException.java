package com.supcon.soft.iis.cim.exception;

import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception present NOT_FOUND
 * @author qiyuqi
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "the data is not found.")
public class InfoNotFoundException extends RuntimeException {
}
