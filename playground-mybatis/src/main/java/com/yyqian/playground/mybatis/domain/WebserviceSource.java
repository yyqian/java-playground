package com.yyqian.playground.mybatis.domain;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created on 2017-01-09T17:42:02+08:00.
 *
 * @author Yinyin Qian
 */
public class WebserviceSource {
    @NotEmpty
    private String uri;
    @NotEmpty
    private String method;
    private String requestBody;
    @NotEmpty
    private String dataFormat;

    public WebserviceSource() {
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getDataFormat() {
        return dataFormat;
    }

    public void setDataFormat(String dataFormat) {
        this.dataFormat = dataFormat;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(String requestBody) {
        this.requestBody = requestBody;
    }
}
