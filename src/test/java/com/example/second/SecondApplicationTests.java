package com.example.second;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

@SpringBootTest
class SecondApplicationTests {
    private static final String PUBLIC_KEY = "public.key";
    @Test
    void contextLoads() {
        Resource resource = new ClassPathResource(PUBLIC_KEY);
        String publicKey = null;
        try {
            publicKey = inputStream2String(resource.getInputStream());
            System.out.println(publicKey);
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }

    String inputStream2String(InputStream is) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(is));
        StringBuffer buffer = new StringBuffer();
        String line = "";
        while ((line = in.readLine()) != null) {
            buffer.append(line);
        }
        return buffer.toString();
    }

}
