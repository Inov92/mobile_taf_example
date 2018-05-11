package org.exampleProject.qa.common.gui.services.rest.endpoints;

import org.exampleProject.qa.common.gui.services.rest.RestServiceOutput;
import org.exampleProject.qa.common.gui.services.rest.models.randomquote.Quote;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

@Lazy
@Component
public class RandomQuoteEndpoint extends AbstractEndPoint {
    @Value("${rest.api.random.endpoint}")
    public String apiPath;

    Quote quote;

    public RestServiceOutput getQuote() throws UnsupportedEncodingException {
        super.targetUri = super.setUri(super.apiUri + apiPath);
        return super.sendGETRequest();
    }
}
