package com.glitch.servermanagement.services;

import java.io.IOException;
import java.util.Collection;

import com.glitch.servermanagement.models.Server;

public interface ServerService {
    Server create(Server server);
    Collection<Server> list(int limit);
    Server get(Long id);
    Server ping(String ipAddress) throws IOException;
    Server update(Server server);
    Boolean delete(Long id);
}
