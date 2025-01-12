package com.techlabs.service.cart.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techlabs.service.auth.AuthClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

@Configuration
public class AppConfig {

    @Bean
    public AuthClient userServiceClient(@Value("${techlabs.authServiceUrl}") String userServiceUrl) {
        return getRetrofitInstance(userServiceUrl, AuthClient.class);
    }

    private <T> T getRetrofitInstance(String apiServiceUrl, Class<T> apiClientClass) {
        return new Retrofit.Builder()
                .baseUrl(apiServiceUrl)
                //.client(AuthorizedHttpClient.INSTANCE) // TODO
                .addConverterFactory(JacksonConverterFactory.create(new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)))
                .build()
                .create(apiClientClass);
    }
}
