package pl.edu.pw.infstos.szsdsr.charges.core.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import pl.edu.pw.infstos.szsdsr.charges.core.domain.Payment;
import pl.edu.pw.infstos.szsdsr.charges.core.repo.PaymentRepository;
import pl.edu.pw.infstos.szsdsr.generated.models.PaymentDTO;

import java.util.Optional;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final ObjectMapper objectMapper;

    public PaymentService(PaymentRepository paymentRepository, ObjectMapper objectMapper) {
        this.paymentRepository = paymentRepository;
        this.objectMapper = objectMapper;
    }

    public PaymentDTO addPayment(PaymentDTO paymentDto) {
        Payment payment = dtoToPayment(paymentDto);
        payment.setId(null);
        Payment newPayment = paymentRepository.save(payment);
        return paymentToDto(newPayment);
    }

    public Optional<PaymentDTO> getPayment(Long id) {
        return paymentRepository.findById(id).map(this::paymentToDto);
    }

    public Optional<PaymentDTO> updatePayment(PaymentDTO paymentDto) {
        Payment payment = dtoToPayment(paymentDto);
        if (paymentRepository.existsById(payment.getId())) {
            Payment newPayment = paymentRepository.save(payment);
            return Optional.of(paymentToDto(newPayment));
        } else {
            return Optional.empty();
        }
    }

    public boolean deletePayment(Long id) {
        if (paymentRepository.existsById(id)) {
            paymentRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    private PaymentDTO paymentToDto(Payment payment) {
        return objectMapper.convertValue(payment, PaymentDTO.class);
    }

    private Payment dtoToPayment(PaymentDTO paymentDTO) {
        return objectMapper.convertValue(paymentDTO, Payment.class);
    }

}
