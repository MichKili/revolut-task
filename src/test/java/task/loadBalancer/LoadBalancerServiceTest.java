package task.loadBalancer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.LongStream;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class LoadBalancerServiceTest {

    @InjectMocks
    private LoadBalancerService loadBalancerService;

    @Test
    void shouldRouteRequestToServer() {
        //given
        List<Request> requestsToRoute = LongStream.range(0, 100).mapToObj(Request::new).toList();
        Server server = new Server("192.14.52.424");
        Server server2 = new Server("192.14.52.544");
        Server server3 = new Server("192.14.52.123");
        LoadBalancer loadBalancer = new LoadBalancer();
        loadBalancer.addServer(server);
        loadBalancer.addServer(server);
        loadBalancer.addServer(server2);
        loadBalancer.addServer(server3);

        //when
        loadBalancerService.routeRequestToServers(requestsToRoute, loadBalancer);

        //then
        assertThat(server.getRequests()).hasSize(34);
        assertThat(server2.getRequests()).hasSize(33);
        assertThat(server3.getRequests()).hasSize(33);

    }



}