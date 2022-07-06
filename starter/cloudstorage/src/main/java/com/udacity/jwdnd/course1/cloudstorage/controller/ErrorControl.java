package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.web.servlet.error.ErrorController;

@Controller
public class ErrorControl implements ErrorController {

    @RequestMapping("/error")
    @Override
    public String getErrorPath() {
        return "/error";
    }

//    @RequestMapping("/error")
//    public String C_ErrorPageView(HttpServletRequest request) {
//
//        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
//        int statusCode = Integer.parseInt(status.toString());
//
//        List<Integer> error_status = new ArrayList<>();
//
//        error_status.add(HttpStatus.NOT_FOUND.value());
//        error_status.add(HttpStatus.INTERNAL_SERVER_ERROR.value());
//        error_status.add(HttpStatus.FORBIDDEN.value());
//
//        if (statusCode == error_status.get(0)) return "404 ERROR";
//        if (statusCode == error_status.get(1)) return "500 ERROR";
//        if (statusCode == error_status.get(2)) return "403 ERROR";
//
//
//        return "error";
//
//    }
}
