package org.exampleProject.qa.common.gui.services.rest.endpoints;

import org.exampleProject.qa.common.gui.services.rest.RestServiceOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;

@Lazy
@Component
public abstract class AbstractEndPoint {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractEndPoint.class);

    @Autowired
    protected RestTemplate servicesRestTemplate;

    @Value("${rest.api.uri}")
    protected String apiUri;

    protected URI targetUri;

    public RestServiceOutput sendGETRequest() throws UnsupportedEncodingException {
        RestServiceOutput serviceOutput = new RestServiceOutput();
        long startTime = System.currentTimeMillis();
        try {
            HttpHeaders headers = new HttpHeaders();
            HttpEntity<String> entity = new HttpEntity<>(headers);
            ResponseEntity<byte[]> response = servicesRestTemplate.exchange(targetUri, HttpMethod.GET, entity, byte[].class);
            serviceOutput.setResponse(response);
            LOG.info("REST_Execution_Time(ms):; {}; Request:; {}; Http_Response_Code:;{}",
                    String.valueOf(System.currentTimeMillis() - startTime),
                    URLDecoder.decode(targetUri.toASCIIString(), "UTF-8"),
                    serviceOutput.getHttpStatus().value());
        } catch (HttpServerErrorException e2) {
            serviceOutput.setException(e2);
            LOG.info("REST_Execution_Time(ms):; {}; Request:; {};Http_Response_Code:;{}",
                    String.valueOf(System.currentTimeMillis() - startTime),
                    URLDecoder.decode(targetUri.toASCIIString(), "UTF-8"),
                    serviceOutput.getHttpStatus().value());
        } catch (RestClientException e) {
            serviceOutput.setException(e);
            LOG.info("REST_Execution_Time(ms):; {}; Request:; {}; Http_Response_Code:;{}",
                    String.valueOf(System.currentTimeMillis() - startTime),
                    URLDecoder.decode(targetUri.toASCIIString(), "UTF-8"), serviceOutput.getHttpStatus() != null ?
                            serviceOutput.getHttpStatus().value() : "Export Service HTTP status code is null.");
        }
        return serviceOutput;
    }

    public String getUri(String restEndPoint) {
        return setUri(restEndPoint).toASCIIString();
    }


    protected URI setUri(String restEndPoint) {
        return UriComponentsBuilder.fromUriString(restEndPoint)
                .build()
                .toUri();
    }
}
