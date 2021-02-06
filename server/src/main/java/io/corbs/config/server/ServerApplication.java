package io.corbs.config.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.web.server.ManagementContextAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.bus.BusAutoConfiguration;
import org.springframework.cloud.bus.BusRefreshAutoConfiguration;
import org.springframework.cloud.bus.BusStreamAutoConfiguration;
import org.springframework.cloud.bus.PathServiceMatcherAutoConfiguration;
import org.springframework.cloud.bus.jackson.BusJacksonAutoConfiguration;
import org.springframework.cloud.config.monitor.EnvironmentMonitorAutoConfiguration;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;

@EnableConfigServer
@SpringBootApplication(exclude = {
	DataSourceAutoConfiguration.class,
	RedisAutoConfiguration.class,
	ManagementContextAutoConfiguration.class,
	ManagementWebSecurityAutoConfiguration.class,
	SecurityAutoConfiguration.class,
	org.springframework.cloud.stream.binder.kafka.config.ExtendedBindingHandlerMappingsProviderConfiguration.class,
	org.springframework.cloud.stream.binder.rabbit.config.ExtendedBindingHandlerMappingsProviderConfiguration.class,
	BusAutoConfiguration.class,
	PathServiceMatcherAutoConfiguration.class,
	BusAutoConfiguration.class,
	BusRefreshAutoConfiguration.class,
	BusStreamAutoConfiguration.class,
	BusJacksonAutoConfiguration.class,
	EnvironmentMonitorAutoConfiguration.class,
	KafkaAutoConfiguration.class,
	RabbitAutoConfiguration.class,
})
public class ServerApplication {
	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}
}

@Profile("!no-actuator")
@Configuration
@Import(ManagementContextAutoConfiguration.class)
class ActuatorBackendConfiguration { }

@Profile("jdbc")
@Configuration
@Import(DataSourceAutoConfiguration.class)
class JdbcBackendConfiguration {}

@Profile("cloud-bus-kafka")
@Configuration
@Import(value = {
	KafkaAutoConfiguration.class,
	org.springframework.cloud.stream.binder.kafka.config.ExtendedBindingHandlerMappingsProviderConfiguration.class})
class CloudBusKafkaConfiguration extends CloudBusConfiguration {

}

@Profile("cloud-bus-rabbit")
@Configuration
@Import(value = {
	RabbitAutoConfiguration.class,
	org.springframework.cloud.stream.binder.rabbit.config.ExtendedBindingHandlerMappingsProviderConfiguration.class})
class CloudBusRabbitConfiguration extends CloudBusConfiguration {

}

@Import({
	BusAutoConfiguration.class,
	BusRefreshAutoConfiguration.class,
	BusStreamAutoConfiguration.class,
	BusJacksonAutoConfiguration.class,
	PathServiceMatcherAutoConfiguration.class
})
abstract class CloudBusConfiguration { }

@Profile("redis")
@Configuration
@Import(RedisAutoConfiguration.class)
class RedisBackendConfiguration {}

@Profile("security")
@Configuration
@Import({ManagementWebSecurityAutoConfiguration.class, SecurityAutoConfiguration.class})
class SecurityConfiguration {}

