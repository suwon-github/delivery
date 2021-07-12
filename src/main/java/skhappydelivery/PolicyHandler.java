package skhappydelivery;

import skhappydelivery.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler{
    @Autowired DeliveryRepository deliveryRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverStoreCooked_DeliveryStart(@Payload StoreCooked storeCooked){

        if(!storeCooked.validate()) return;

        System.out.println("\n\n##### listener DeliveryStart : " + storeCooked.toJson() + "\n\n");

        // Sample Logic //
        Delivery delivery = new Delivery();
        deliveryRepository.save(delivery);
            
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverStoreOrderCancelled_DeliveryCancel(@Payload StoreOrderCancelled storeOrderCancelled){

        if(!storeOrderCancelled.validate()) return;

        System.out.println("\n\n##### listener DeliveryCancel : " + storeOrderCancelled.toJson() + "\n\n");

        // Sample Logic //
        Delivery delivery = new Delivery();
        deliveryRepository.save(delivery);
            
    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString){}


}
