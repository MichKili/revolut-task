package task.loadBalancer;

import java.util.ArrayList;
import java.util.List;

public class Server {
    private String ipAddress;
    private List<Request> requests = new ArrayList<>(100);

    public Server(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public void addRequestToServer(Request request) {
        requests.add(request);
    }

    public List<Request> getRequests() {
        return requests;
    }
}
