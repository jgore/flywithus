package tech.lideo.flywithus.service.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tech.lideo.flywithus.controller.dto.CreditCardDetailsDto;

import java.util.UUID;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final RestTemplate restTemplate;
    private final String paymentUrl ;

    @Autowired
    public PaymentServiceImpl(RestTemplateBuilder restTemplateBuilder, @Value("${payment_url}") String paymentUrl) {
        this.restTemplate = restTemplateBuilder.build();
        this.paymentUrl = paymentUrl;
    }


    @Override
    public PaymentResponse creditCardPayment(CreditCardDetailsDto creditCardDetailsDto, UUID secretCode) {

        HttpEntity<CreditCardDetailsDto> request = new HttpEntity<>(creditCardDetailsDto);
        return restTemplate.postForObject(paymentUrl + secretCode, request, PaymentResponse.class);

    }
}
