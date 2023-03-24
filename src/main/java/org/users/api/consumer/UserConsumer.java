package org.users.api.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.users.api.usecases.AddToClassListUseCase;
import org.users.api.usecases.RemoveFromClassListUseCase;

import java.util.Objects;

@Component
@AllArgsConstructor
public class UserConsumer {

    private final ObjectMapper objectMapper;
    private final AddToClassListUseCase updateListUseCase;
    private final RemoveFromClassListUseCase removeFromClassListUseCase;

    @RabbitListener(queues = "classes.queue")
    public void receiveEventClass(String message) throws JsonProcessingException {
        ClassEvent event = objectMapper.readValue(message, ClassEvent.class);
        System.out.println(event.getEventType());
        //updateListUseCase.add(event.getIdUser(), event.getClassSubscribed());
        if (Objects.equals(event.getEventType(), "unsubscribed")){
            System.out.println("Entered unsubscribed event");
            removeFromClassListUseCase.remove(event.getIdUser(), event.getClassSubscribed()).subscribe();
        }
        else {
            System.out.println("Entered subscribed event");
            updateListUseCase.add(event.getIdUser(), event.getClassSubscribed()).subscribe();
        }
        //updateListUseCase.add(event.getIdUser(), event.getClassSubscribed()).subscribe();




    }
}
