Gateway是用来接受用户请求并将他们分发给其他service的，是的。要写gateway，你可以使用Spring Cloud Gateway这个项目，它是一个基于Spring WebFlux和Project Reactor的API网关，可以提供路由、负载均衡、鉴权、监控等功能。12


I'm developing multiple microservices instance with a discovery service using eureka and a gateway service using spring-cloud-starter-gateway. I want you to help me rewrite a application.yml for the gatway.
I wrote a pos-api service to guide the implementation of other services.These are the services I write and their openApi apis. 
pos-carts:
/carts
/carts/{cartId}
/carts/{cartId}/total
pos-products:
/products
/products/{productId}
pos-counter:
/counter/checkout

Can you describe how the project work with 