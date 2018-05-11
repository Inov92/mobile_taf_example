package org.exampleProject.qa.common.gui.services.rest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class RestServiceOutput {
    private ResponseEntity<String> responseString;
    private ResponseEntity<byte[]> response;
    private RestClientException exception;

    public ResponseEntity<byte[]> getResponse() {
        return response;
    }

    public void setResponse(ResponseEntity<byte[]> response) {
        this.response = response;
    }

    public RestClientException getException() {
        return exception;
    }

    public void setException(RestClientException exception) {
        this.exception = exception;
    }

    public InputStream getContent() {
        InputStream stream = null;
        if (response != null) {
            stream = new ByteArrayInputStream(response.getBody());
        }
        return stream;
    }

    public ResponseEntity<String> getResponseString() {
        if (responseString != null) {
            return responseString;
        }
        return null;
    }

    public void setResponseString(ResponseEntity<String> responseString) {
        this.responseString = responseString;
    }

    public HttpStatus getHttpStatus() {
        HttpStatus status = null;
        if (response != null) {
            status = response.getStatusCode();
        } else if (responseString != null) {
            status = responseString.getStatusCode();
        } else if (exception != null && exception instanceof HttpStatusCodeException) {
            status = ((HttpStatusCodeException) exception).getStatusCode();
        }
        return status;
    }

    public HttpHeaders getHttpHeaders() {
        HttpHeaders headers = null;
        if (response != null) {
            headers = response.getHeaders();
        } else if (exception != null && exception instanceof HttpStatusCodeException) {
            headers = ((HttpStatusCodeException) exception).getResponseHeaders();
        }
        return headers;
    }

    public String getExceptionDescription() {
        String description = null;
        if (exception != null && exception instanceof HttpStatusCodeException) {
            description = ((HttpStatusCodeException) exception).getResponseBodyAsString();
        }
        return description;
    }

    public ByteArrayInputStream getExceptionDescriptionAsStream() {
        String exceptionDescription = getExceptionDescription();
        if (exceptionDescription == null) {
            return null;
        }
        return new ByteArrayInputStream(exceptionDescription.getBytes(StandardCharsets.UTF_8));
    }
}
