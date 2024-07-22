package task.loadBalancer;

import java.util.List;

public class LoadBalancerService {

    public void routeRequestToServers(List<Request> requestsToRoute, LoadBalancer loadBalancer) {
        List<Server> servers = loadBalancer.getServers();
        int requestsCount = requestsToRoute.size();
        int requestIndex = 0;

        while (requestIndex < requestsCount) {
            for (Server server : servers) {
                if (requestIndex >= requestsCount) {
                    break;
                }
                server.addRequestToServer(requestsToRoute.get(requestIndex));
                requestIndex++;
            }
        }
    }
}
