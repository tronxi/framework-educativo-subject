package es.upm.frameworkeducativosubject.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfig {

    @Bean
    public Declarables createRabbitmqSchema() {
        return new Declarables(
                new FanoutExchange("user.deleted", true, false, null),
                new Queue("user.deleted.subject"),
                new Binding("user.deleted.subject", Binding.DestinationType.QUEUE, "user.deleted", "", null),

                new FanoutExchange("group.deleted", true, false, null),
                new FanoutExchange("userGroup.deleted", true, false, null));
    }

}
