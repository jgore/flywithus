package tech.lideo.flywithus.service.payment;

import java.util.Objects;
import java.util.UUID;

public class PaymentResponse {

    private PaymentStatus paymentStatus;
    private UUID reservationSecretCode;

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public UUID getResevationSecredCode() {
        return reservationSecretCode;
    }

    public void setResevationSecredCode(UUID resevationSecredCode) {
        this.reservationSecretCode = resevationSecredCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentResponse that = (PaymentResponse) o;
        return getPaymentStatus() == that.getPaymentStatus() &&
                Objects.equals(reservationSecretCode, that.reservationSecretCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPaymentStatus(), reservationSecretCode);
    }
}
