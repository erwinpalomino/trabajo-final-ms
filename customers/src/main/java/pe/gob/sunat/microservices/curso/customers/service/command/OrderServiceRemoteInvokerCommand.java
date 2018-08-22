package pe.gob.sunat.microservices.curso.customers.service.command;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;

import pe.gob.sunat.microservices.curso.orders.client.OrderServiceClient;

public class OrderServiceRemoteInvokerCommand extends HystrixCommand<Boolean> {
  public static final int CONCURRENT_REQUESTS = 20;
  private final OrderServiceClient orderServiceClient;
  private final Long id;

  public OrderServiceRemoteInvokerCommand(OrderServiceClient orderServiceClient, Long id) {
    super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("GroupComandoLatencia"))
      .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
        .withExecutionIsolationSemaphoreMaxConcurrentRequests(CONCURRENT_REQUESTS)));
    this.orderServiceClient = orderServiceClient;
    this.id = id;
  }


  @Override
  protected Boolean run() throws Exception {
    return orderServiceClient.get(id).execute().isSuccessful();
  }

  @Override
  protected Boolean getFallback() {
    return false;
  }
}
