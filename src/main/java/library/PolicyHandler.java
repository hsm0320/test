package library;

import library.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PolicyHandler{
    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString){

    }

    @Autowired
    BookRepository bookRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverRentaled_UpdateStatus(@Payload Rentaled rentaled){

        if(rentaled.isMe()){
            //추가
            Optional<Book> bookOptional = bookRepository.findById(rentaled.getRentalId());
            Book book = bookOptional.get();
            book.setStatus(rentaled.getRentalStatus());
            bookRepository.save(book);

            System.out.println("##### listener UpdateStatus : " + rentaled.toJson());
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverCancelled_UpdateStatus(@Payload Cancelled cancelled){

        if(cancelled.isMe()){
            //추가
            Optional<Book> bookOptional = bookRepository.findById(cancelled.getRentalId());
            Book book = bookOptional.get();
            book.setStatus(cancelled.getRentalStatus());
            bookRepository.save(book);

            System.out.println("##### listener UpdateStatus : " + cancelled.toJson());
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverReserved_UpdateStatus(@Payload Reserved reserved){

        if(reserved.isMe()){
            //추가
            Optional<Book> bookOptional = bookRepository.findById(reserved.getRentalId());
            Book book = bookOptional.get();
            book.setStatus(reserved.getRentalStatus());
            bookRepository.save(book);

            System.out.println("##### listener UpdateStatus : " + reserved.toJson());
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverReturned_UpdateStatus(@Payload Returned returned){

        if(returned.isMe()){
            //추가
            Optional<Book> bookOptional = bookRepository.findById(returned.getRentalId());
            Book book = bookOptional.get();
            book.setStatus(returned.getRentalStatus());
            bookRepository.save(book);

            System.out.println("##### listener UpdateStatus : " + returned.toJson());
        }
    }

}
