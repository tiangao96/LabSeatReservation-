package com.lab.common.security.crypto;

import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class RewritableBodyRequestWrapper
extends HttpServletRequestWrapper {
    private final byte[] body;

    public RewritableBodyRequestWrapper(HttpServletRequest request, byte[] body) {
        super(request);
        this.body = body;
    }

    public ServletInputStream getInputStream() {
        final ByteArrayInputStream in = new ByteArrayInputStream(this.body);
        return new ServletInputStream(){

            @Override
            public int read() {
                return in.read();
            }

            @Override
            public boolean isFinished() {
                return in.available() == 0;
            }

            @Override
            public boolean isReady() {
                return true;
            }

            @Override
            public void setReadListener(ReadListener readListener) {
            }
        };
    }

    public BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader((InputStream)this.getInputStream(), StandardCharsets.UTF_8));
    }

    public int getContentLength() {
        return this.body.length;
    }

    public long getContentLengthLong() {
        return this.body.length;
    }
}
