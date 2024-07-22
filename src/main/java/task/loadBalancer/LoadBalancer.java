package task.loadBalancer;

import java.util.ArrayList;
import java.util.List;


public class LoadBalancer {
    public static final int MAX_CONNECTIONS = 10;
    private final List<Server> servers = new ArrayList<>(MAX_CONNECTIONS);

    public LoadBalancer() {
    }

    public void addServer(Server server) {
        if (servers.size() > MAX_CONNECTIONS) {
            throw new IllegalStateException("Max connections reached");
        }

        if (!servers.contains(server)) {
            servers.add(server);
        }
    }

    public List<Server> getServers() {
        return servers;
    }
}
