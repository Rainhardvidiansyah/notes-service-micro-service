package com.rainhard.notes.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class ApiService {

    private static final Logger log = LoggerFactory.getLogger(ApiService.class);
    @Autowired
    private WebClient webClient;

    public Mono<Object> getData(){
        //https://jsonplaceholder.typicode.com/users/1
        return webClient.get().uri("https://jsonplaceholder.typicode.com/users/1")
                .retrieve()
                .bodyToMono(Object.class);
    }

    public String getDataHttp() throws URISyntaxException, IOException, InterruptedException {

        String res= "";
        try{
            HttpClient httpClient = HttpClient.newBuilder().build();

            HttpRequest request = HttpRequest.newBuilder().uri(new URI("https://jsonplaceholder.typicode.com/users/1"))
                    .GET().build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if(response.statusCode() == 200) {
                res = response.body();
            }else if(response.statusCode() == 404) {
                return "Not Found";
            }else {
                return "error";
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e);
        }
        return res;
    }

}
