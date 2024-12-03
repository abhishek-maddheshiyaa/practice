package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients


public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
/*
 * 1. registerHealthIndicator: true
This enables the registration of a health indicator for the circuit breaker in the system. 
When set to true, it integrates the circuit breaker into the application's health check mechanisms,
 allowing monitoring tools (like Spring Boot Actuator) to check the health of the circuit breaker.


2. eventConsumerBufferSize: 10
This parameter defines the maximum number of events that the event consumer will buffer in memory.
 Resilience4j can log events (like state transitions or failures), and this buffer controls how many events
  can be stored before older events are discarded.
  
3. failureRateThreshold: 50
This defines the threshold of the failure rate percentage at which the circuit breaker will trip 
(i.e., transition to the Open state). If the failure rate (i.e., failed calls divided by total calls) exceeds this threshold, the circuit breaker will open.
In this case, if more than 50% of calls fail, the circuit breaker will be triggered.

4. minimumNumberOfCalls: 5
This is the minimum number of calls that must be made before the circuit breaker considers the failure 
rate for determining whether to open the circuit. If fewer than 5 calls are made, the circuit breaker 
will not open, regardless of the failure rate. This helps to avoid premature tripping due to low traffic.

5. automaticTransitionFromOpenToHalfOpenEnabled: true
This setting allows the circuit breaker to automatically attempt to transition from the Open state to 
the Half-Open state after a defined wait period (waitDurationInOpenState). 
This automatic transition is triggered after the 
Open state has been held for the configured duration.
If set to false, the transition from Open to Half-Open would need to be triggered manually, or based on custom logic.

6. waitDurationInOpenState: 5s
This is the amount of time that the circuit breaker remains in the Open state before it 
automatically tries to transition to the Half-Open state. In this case, after 5 seconds,
 the circuit breaker will try to see if the underlying service has recovered, by moving to Half-Open 
 and testing a limited number of requests.
This value is used only when automaticTransitionFromOpenToHalfOpenEnabled is set to true.


7. permittedNumberOfCallsInHalfOpenState: 3
When the circuit breaker is in the Half-Open state, it will allow a limited number of calls to pass 
through (in this case, 3). The purpose of Half-Open is to test if the underlying system has recovered.
 If these test calls succeed, the circuit breaker will close; if they fail, the circuit breaker will open again.
This allows you to test the service without fully reopening the circuit and completely rejecting all requests.

8. slidingWindowSize: 10
This controls the number of calls considered by the circuit breaker when calculating failure rate and 
success rate. It's the size of the window that Resilience4j uses to evaluate the state of the system.
For example, with a sliding window size of 10, it will only consider the last 10 calls when determining
 whether to open or close the circuit.
 
9. slidingWindowType: COUNT_BASED
This determines how the sliding window is calculated.
COUNT_BASED means the sliding window is based on the count of calls made. The last slidingWindowSize calls 
(in this case, the last 10 calls) will be considered for calculating failure and success rates.
The alternative is TIME_BASED, where the sliding window would be based on a time duration 
(e.g., 10 seconds), and it would consider all calls made within that time span.
*/
