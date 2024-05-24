package simulations;

import io.gatling.http.request.builder.HttpRequestBuilder;
import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.HttpDsl.*;
import io.gatling.javaapi.http.HttpProtocolBuilder;
import io.gatling.javaapi.http.HttpRequestActionBuilder;

import java.time.Duration;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

public class CartSimulation extends Simulation {

    // Define the base URL as a variable
    String baseUrl = "http://localhost:8080";

    // Define HTTP requests as variables
    HttpRequestActionBuilder createCartRequest = http("Create Cart")
            .post("/api/cart");
    HttpRequestActionBuilder putItem1Request = http("Put Item 1")
            .put("/api/cart/items/1");

    HttpRequestActionBuilder putItem2Request = http("Put Item 2")
            .put("/api/cart/items/2");

    HttpRequestActionBuilder postCheckoutRequest = http("Post Checkout")
            .post("/api/cart/checkout");

    HttpProtocolBuilder httpProtocol = http
            .baseUrl(baseUrl) // Use the variable here
            .acceptHeader("application/json")
            .contentTypeHeader("application/json");

    ScenarioBuilder scn = scenario("Cart Simulation")
            .exec(createCartRequest)
            .exec(putItem1Request)
            .exec(putItem2Request)
            .exec(putItem1Request)
            .exec(putItem2Request)
            .exec(putItem1Request)
            .exec(putItem2Request)
            .exec(postCheckoutRequest)
            .exec(createCartRequest)
            .exec(putItem1Request)
            .exec(putItem2Request)
            .exec(putItem1Request)
            .exec(putItem2Request)
            .exec(putItem1Request)
            .exec(putItem2Request)
            .exec(postCheckoutRequest)
            ;

    {
        setUp(
                scn.injectOpen(atOnceUsers(1))
        ).protocols(httpProtocol);
    }
}
