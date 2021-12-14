package com.glitch.servermanagement.repos;

import com.glitch.servermanagement.models.Server;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ServerRepo extends JpaRepository<Server,Long> {
    Server findByIpAddress(String ipAddress);
}
