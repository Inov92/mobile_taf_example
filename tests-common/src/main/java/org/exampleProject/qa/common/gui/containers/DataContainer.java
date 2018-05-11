package org.exampleProject.qa.common.gui.containers;
/*
* Copyright 2002 - 2017 the original author or authors.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

import com.fasterxml.jackson.databind.DeserializationFeature;
import org.exampleProject.qa.common.gui.services.rest.RestServiceOutput;
import org.exampleProject.qa.common.gui.services.rest.models.randomquote.Quote;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by Anton_Shapin on 5/23/17.
 */
@Component
@Lazy
public class DataContainer {
    private String searchQuery;
    private RestServiceOutput restServiceOutput;
    byte[] responseEntity;
    private Quote quoteServiceResponse;

    public DataContainer() {
    }

    public void setQuoteServiceResponse() throws IOException {
        this.quoteServiceResponse = getModelFromJson(Quote.class);
    }

    public void getRestServiceOutput(RestServiceOutput restServiceOutput) {
        this.restServiceOutput = restServiceOutput;
        if (restServiceOutput.getResponse() != null){
            this.responseEntity = this.restServiceOutput.getResponse().getBody();
        }
    }


    public <T> T getModelFromJson(Class<?> type) throws IOException {
        if (restServiceOutput.getResponse() != null) {
            return new com.fasterxml.jackson.databind.ObjectMapper()
                    .enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
                    .readerFor(type)
                    .readValue(restServiceOutput.getResponse().getBody());
        } else {
            return null;
        }
    }

    public String getSearchQuery() {
        return this.searchQuery;
    }

    public RestServiceOutput getRestServiceOutput() {
        return this.restServiceOutput;
    }

    public byte[] getResponseEntity() {
        return this.responseEntity;
    }

    public Quote getQuoteServiceResponse() {
        return this.quoteServiceResponse;
    }

    public void setSearchQuery(String searchQuery) {
        this.searchQuery = searchQuery;
    }

    public void setRestServiceOutput(RestServiceOutput restServiceOutput) {
        this.restServiceOutput = restServiceOutput;
    }

    public void setResponseEntity(byte[] responseEntity) {
        this.responseEntity = responseEntity;
    }

    public void setQuoteServiceResponse(Quote quoteServiceResponse) {
        this.quoteServiceResponse = quoteServiceResponse;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof DataContainer)) return false;
        final DataContainer other = (DataContainer) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$searchQuery = this.getSearchQuery();
        final Object other$searchQuery = other.getSearchQuery();
        if (this$searchQuery == null ? other$searchQuery != null : !this$searchQuery.equals(other$searchQuery))
            return false;
        final Object this$restServiceOutput = this.getRestServiceOutput();
        final Object other$restServiceOutput = other.getRestServiceOutput();
        if (this$restServiceOutput == null ? other$restServiceOutput != null : !this$restServiceOutput.equals(other$restServiceOutput))
            return false;
        if (!java.util.Arrays.equals(this.getResponseEntity(), other.getResponseEntity())) return false;
        final Object this$quoteServiceResponse = this.getQuoteServiceResponse();
        final Object other$quoteServiceResponse = other.getQuoteServiceResponse();
        if (this$quoteServiceResponse == null ? other$quoteServiceResponse != null : !this$quoteServiceResponse.equals(other$quoteServiceResponse))
            return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $searchQuery = this.getSearchQuery();
        result = result * PRIME + ($searchQuery == null ? 43 : $searchQuery.hashCode());
        final Object $restServiceOutput = this.getRestServiceOutput();
        result = result * PRIME + ($restServiceOutput == null ? 43 : $restServiceOutput.hashCode());
        result = result * PRIME + java.util.Arrays.hashCode(this.getResponseEntity());
        final Object $quoteServiceResponse = this.getQuoteServiceResponse();
        result = result * PRIME + ($quoteServiceResponse == null ? 43 : $quoteServiceResponse.hashCode());
        return result;
    }

    protected boolean canEqual(Object other) {
        return other instanceof DataContainer;
    }

    public String toString() {
        return "DataContainer(searchQuery=" + this.getSearchQuery() + ", restServiceOutput=" + this.getRestServiceOutput() + ", responseEntity=" + java.util.Arrays.toString(this.getResponseEntity()) + ", quoteServiceResponse=" + this.getQuoteServiceResponse() + ")";
    }
}
