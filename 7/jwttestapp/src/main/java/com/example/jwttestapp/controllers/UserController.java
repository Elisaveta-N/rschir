package com.example.jwttestapp.controllers;


import com.example.jwttestapp.model.Book;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@RestController
@RequestMapping("/users")
public class UserController {

    @Value("${jwt.url_update}")
    private String url;

    @PutMapping("{name}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> add_role(@PathVariable String name, @RequestBody String role) {
        try {
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .header("Content-Type", "text/plain")
                    .uri(URI.create(url.concat("/"+ name)))
                    .method("PUT", HttpRequest.BodyPublishers.ofString(role))
                    .build();
            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            System.out.println(response.statusCode());
            if(response.statusCode() == 200){
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (Exception ex){
            System.out.println("User update failed: " + ex.getMessage());
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
