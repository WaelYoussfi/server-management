package com.glitch.servermanagement.resource;

import com.glitch.servermanagement.enumeration.Status;
import com.glitch.servermanagement.models.Response;
import com.glitch.servermanagement.models.Server;
import com.glitch.servermanagement.services.implementations.ServerServiceImplementation;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Map;

import javax.validation.Valid;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/server")
@RequiredArgsConstructor
public class ServerResource {
    private final ServerServiceImplementation serverService;
    @Operation(summary = "Retrieving servers", description = "Listing servers from the database (pagination: 30 servers each time)", tags = "Get")
    @GetMapping("/list")
    public ResponseEntity<Response> getServers() {
        return ResponseEntity.ok(
            Response.builder()
            .timeStamp(LocalDateTime.now())
            .data(Map.of("servers", serverService.list(30)))
            .message("servers retrieved")
            .status(HttpStatus.OK).statusCode(HttpStatus.OK.value())
            .build());
    }
    @Operation(summary = "Pinging server", description = "Pinging a server to check whether its status is UP or DOWN and changing the status accordingly", tags = "Get")
    @GetMapping("/ping/{ipAddress}")
    public ResponseEntity<Response> pingServer(@PathVariable("ipAddress") String ipAddress) throws IOException {
        Server server = serverService.ping(ipAddress);
        return ResponseEntity.ok(
            Response.builder()
            .timeStamp(LocalDateTime.now())
            .data(Map.of("server", server))
            .message(server.getStatus() == Status.SERVER_UP ? "Ping success" : "Ping failed")
            .status(HttpStatus.OK).statusCode(HttpStatus.OK.value())
            .build());
    }
    @Operation(summary = "Adding a server", description = "Adding a server to the database", tags = "Post")
    @PostMapping("/save")
    public ResponseEntity<Response> saveSever(@RequestBody @Valid Server server) {
        return ResponseEntity.ok(
            Response.builder()
            .timeStamp(LocalDateTime.now())
            .data(Map.of("server", serverService.create(server)))
            .message("Server created")
            .status(HttpStatus.CREATED).statusCode(HttpStatus.CREATED.value())
            .build());
    }
    @Operation(summary = "Retrieve a server", description = "Retrieving a specific server via its ID", tags = "Get")
    @GetMapping("/get/{id}")
    public ResponseEntity<Response> getServer(@PathVariable("id") Long id) {
        return ResponseEntity.ok(
            Response.builder()
            .timeStamp(LocalDateTime.now())
            .data(Map.of("server", serverService.get(id)))
            .message("Server retrieved")
            .status(HttpStatus.OK).statusCode(HttpStatus.OK.value())
            .build());
    }
    @Operation(summary = "Delete a server", description = "Deleting a server using its ID", tags = "Delete")
    @DeleteMapping("/get/{id}")
    public ResponseEntity<Response> deleteServer(@PathVariable("id") Long id) {
        return ResponseEntity.ok(
            Response.builder()
            .timeStamp(LocalDateTime.now())
            .data(Map.of("deleted", serverService.delete(id)))
            .message("Server deleted")
            .status(HttpStatus.OK).statusCode(HttpStatus.OK.value())
            .build());
    }
    @Operation(summary = "Retrieve server icon", description = "Retrieving the image file using its name", tags = "Get")
    @GetMapping(path = "/image/{fileName}", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getServerImage(@PathVariable("fileName") String fileName) throws IOException {
        return Files.readAllBytes(Paths.get(System.getProperty("user.home")+"/Downloads/"+fileName));
    }

}
