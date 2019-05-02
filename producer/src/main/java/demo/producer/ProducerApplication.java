package demo.producer;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.stereotype.Service;

@SpringBootApplication
public class ProducerApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ProducerApplication.class, args);
	}

	@Bean
	Queue queueCharlie() {
		return new Queue("queue.charlie", false);
	}
	
	@Bean
	Queue queueBravo() {
		return new Queue("queue.bravo", false);
	}

	@Bean
	TopicExchange exchange() {
		return new TopicExchange("exchange");
	}

	@Bean
	Binding bindingExchangeFoo(Queue queueCharlie, TopicExchange exchange) {
		return BindingBuilder.bind(queueCharlie).to(exchange).with("queue.charlie");
	}
	
	@Bean
	Binding bindingExchangeBar(Queue queueBravo, TopicExchange exchange) {
		return BindingBuilder.bind(queueBravo).to(exchange).with("queue.bravo");
	}

	@Bean
	public MappingJackson2MessageConverter jackson2Converter() {
		MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
		return converter;
	}

	@Autowired
	private Sender sender;

	@Override
	public void run(String... args) throws Exception {
		sender.sendToRabbitmq(new Charlie(), new Bravo());
	}
}

@Service
class Sender {

	@Autowired
	private RabbitMessagingTemplate rabbitMessagingTemplate;
	@Autowired
	private MappingJackson2MessageConverter mappingJackson2MessageConverter;

	public void sendToRabbitmq(final Charlie charlie, final Bravo bravo) {

		this.rabbitMessagingTemplate.setMessageConverter(this.mappingJackson2MessageConverter);
		this.rabbitMessagingTemplate.convertAndSend("exchange", "queue.charlie", charlie);
		this.rabbitMessagingTemplate.convertAndSend("exchange", "queue.bravo", bravo);
		this.rabbitMessagingTemplate.convertAndSend("exchange", "queue.bravo", bravo);
		this.rabbitMessagingTemplate.convertAndSend("exchange", "queue.bravo", bravo);
		this.rabbitMessagingTemplate.convertAndSend("exchange", "queue.bravo", bravo);
		this.rabbitMessagingTemplate.convertAndSend("exchange", "queue.charlie", charlie);
		this.rabbitMessagingTemplate.convertAndSend("exchange", "queue.bravo", bravo);
		this.rabbitMessagingTemplate.convertAndSend("exchange", "queue.bravo", bravo);
		this.rabbitMessagingTemplate.convertAndSend("exchange", "queue.bravo", bravo);
		this.rabbitMessagingTemplate.convertAndSend("exchange", "queue.bravo", bravo);

	}
}

class Bravo {
	public int count = 5 ;
}

class Charlie {
	public String name = "Charlie Employee";
}
