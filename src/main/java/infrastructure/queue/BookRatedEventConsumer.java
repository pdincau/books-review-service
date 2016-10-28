package infrastructure.queue;

import com.google.gson.Gson;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import domain.View;
import domain.ViewRepository;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

public class BookRatedEventConsumer extends DefaultConsumer {

    private static final Logger LOG = LoggerFactory.getLogger(BookRatedEventConsumer.class);
    private final ViewRepository repository;

    public BookRatedEventConsumer(Channel channel, ViewRepository repository) {
        super(channel);
        this.repository = repository;
    }

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)  throws IOException {
        String message = new String(body, "UTF-8");
        LOG.info("Processing received message: {}", message);
        Map values = new Gson().fromJson(message, Map.class);
        View view = repository.findBy((String) values.get("id"));
        if (StringUtils.isEmpty(view.getIsbn())) {
            view.updateWith(values);
            repository.insert(view);
        } else {
            view.updateWith(values);
            repository.update(view);
        }
    }
}