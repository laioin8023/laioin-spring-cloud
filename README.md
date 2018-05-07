# laioin-spring-cloud
spring boot + spring cloud ，微服务

eureka-server:(服务注册中心)

    端口：5001 ，支持端口：5001 - 5099
        
eureka-client:(服务提供者)

    端口：5100 ，支持端口：5100 - 5199
     
eureka-consumer:(服务消费者) -

        端口：5200 ，支持端口：5200 - 5299
        
        使用 LoadBalancerClient 获取host和端口，拼接url，结合RestTemplate发送请求，如下：
        
             @Autowired
             private LoadBalancerClient loadBalancerClient;
             @Autowired
             private RestTemplate restTemplate;
            
             @RequestMapping("/consumer")
             public String consumer() {
                 ServiceInstance serviceInstance = loadBalancerClient.choose("eureka-client");
                 String url = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/dc";
                 LGR.info("request url = [{}] . ", url);
                 String data = restTemplate.getForObject(url, String.class);
                 LGR.info("request url = [{}] .data = [{}]. ", url, data);
                 return data;
             }
            
eureka-consumer-ribbon:(服务消费者)
    
        端口：5201
        
        使用 @LoadBalanced 自动获取host和端口，进行调用，如下：
        
            @Autowired
            private RestTemplate restTemplate;
        
            @RequestMapping("/consumer")
            public String consumer() {
                String url = "http://eureka-client/dc";
                LGR.info("request url = [{}] . ", url);
                String data = restTemplate.getForObject(url, String.class);
                LGR.info("request url = [{}] .data = [{}]. ", url, data);
                return data;
            }

eureka-consumer-feign:(服务消费者)
    
        端口：5201
            
        使用 @EnableFeignClients + 接口的方式，自动注解服务提供者；本地调用，如下：
         
         接口声明：
            @FeignClient("eureka-client")
            public interface IDcServer {
            
                @GetMapping("/dc")
                String consumer();
            }
         本地调用：
             @Autowired
             private IDcServer dcServer;
         
             @RequestMapping("/consumer")
             public String dc() {
                 String data = dcServer.consumer();
                 return data;
             }
        