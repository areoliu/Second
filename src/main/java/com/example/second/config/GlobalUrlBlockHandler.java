package com.example.second.config;

import com.alibaba.csp.sentinel.adapter.servlet.callback.UrlBlockHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import com.example.second.entity.HttpResponseBody;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GlobalUrlBlockHandler implements UrlBlockHandler {
    @Override
    public void blocked(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, BlockException ex) throws IOException {
//        ErrorMsg msg;
        HttpResponseBody responseBody = new HttpResponseBody();
        if(ex instanceof FlowException){
//            msg = new ErrorMsg(100,"")
            responseBody.setResultCode("100");
            responseBody.setResultMessage("限流了");
        }
        if(ex instanceof DegradeException){
//            msg = new ErrorMsg(100,"")
            responseBody.setResultCode("100");
            responseBody.setResultMessage("降级了");
        }

        if(ex instanceof SystemBlockException){
//            msg = new ErrorMsg(100,"")
            responseBody.setResultCode("100");
            responseBody.setResultMessage("系统规则不满足 ");
        }

        if(ex instanceof AuthorityException){
//            msg = new ErrorMsg(100,"")
            responseBody.setResultCode("100");
            responseBody.setResultMessage("授权规则不满足 ");
        }

        httpServletResponse.setStatus(500);
        httpServletResponse.setCharacterEncoding("utf-8");
        httpServletResponse.setHeader("Content-Type","application/json;charset=utf-8");
        httpServletResponse.getOutputStream().write(new Gson().toJson(responseBody).getBytes());
    }

}
