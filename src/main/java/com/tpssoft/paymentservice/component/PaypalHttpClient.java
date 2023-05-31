package com.tpssoft.paymentservice.component;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tpssoft.paymentservice.configuration.PaypalConfigure;
import com.tpssoft.paymentservice.dto.CardDto;
import com.tpssoft.paymentservice.dto.DirectPaymentDto;


@Component
public class PaypalHttpClient {
	private final HttpClient httpClient;
    private final PaypalConfigure paypalConfig;
    private final ObjectMapper objectMapper;
    
    @Autowired
   	public PaypalHttpClient(PaypalConfigure paypalConfig, ObjectMapper objectMapper) {
   		super();
   		this.paypalConfig = paypalConfig;
   		this.objectMapper = objectMapper;
   		httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1).build();
   	}
    
    public String doDirectPayment(DirectPaymentDto dto) throws IOException, InterruptedException {

    	Map<String, String> parameters = new HashMap<>();
    	parameters.put("VERSION", "56.0");
    	parameters.put("SIGNATURE", paypalConfig.getSignature());
    	parameters.put("USER", paypalConfig.getUser());
    	parameters.put("PWD", paypalConfig.getPwd());
    	parameters.put("METHOD", "DoDirectPayment");
    	parameters.put("PAYMENTACTION", "Sale");
    	parameters.put("IPADDRESS", "192.168.0.1");
    	parameters.put("AMT", dto.getAMT().toString());
    	parameters.put("CREDITCARDTYPE", dto.getCREDITCARDTYPE());
    	parameters.put("ACCT", dto.getACCT());
    	parameters.put("EXPDATE", dto.getEXPDATE());
    	parameters.put("CVV2", dto.getCVV2());
    	parameters.put("FIRSTNAME", dto.getFIRSTNAME());
    	parameters.put("LASTNAME", dto.getLASTNAME());
//    	parameters.put("STREET", dto.getSTREET());
//    	parameters.put("CITY", dto.getCITY());
//    	parameters.put("STATE", dto.getSTATE());
//    	parameters.put("ZIP", dto.getZIP());
//    	parameters.put("COUNTRYCODE", dto.getCOUNTRYCODE());

    	String form = parameters.entrySet().stream()
    	    .map(e -> e.getKey() + "=" + e.getValue())
    	    .collect(Collectors.joining("&"));
    	

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api-3t.sandbox.paypal.com/nvp"))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .POST(HttpRequest.BodyPublishers.ofString(form))
                .build();
        var response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        var result = "";
        try {
             result = java.net.URLDecoder.decode(response.body().toString(), StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            // not going to happen - value came from JDK's own StandardCharsets
        }
        var content = response.body().toString();
        return result;
    }
}
