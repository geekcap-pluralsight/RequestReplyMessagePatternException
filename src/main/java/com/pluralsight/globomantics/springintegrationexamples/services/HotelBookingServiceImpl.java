package com.pluralsight.globomantics.springintegrationexamples.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@MessageEndpoint
@Service
public class HotelBookingServiceImpl implements HotelBookingService {
    private static final Logger logger = LogManager.getLogger(HotelBookingServiceImpl.class);

    @ServiceActivator(inputChannel = "hotelBookingChannel")
    @Override
    public Message<Boolean> checkAvailability(Message<Integer> numberOfGuests) {
        logger.info("checkAvailability: received message: {}", numberOfGuests);

        Integer guests = numberOfGuests.getPayload();
        logger.info("Number of guests: {}", guests);

        // Validate that the number of guests is valid
        if (guests <= 0) {
            throw new RuntimeException("Invalid party size: " + guests);
        }

        // Return true
        return MessageBuilder.withPayload(true).build();
    }
}
