package Project.VirtualBanking.OtherMethods.EntityValidationCheck;

import Project.VirtualBanking.OtherMethods.ValidationCheckMethods.CardCvvValidationCheck;
import Project.VirtualBanking.OtherMethods.ValidationCheckMethods.CardExpirationDateValidationCheck;
import Project.VirtualBanking.OtherMethods.ValidationCheckMethods.CardHolderNameValidationCheck;
import Project.VirtualBanking.OtherMethods.ValidationCheckMethods.CardNumberValidationCheck;
import Project.VirtualBanking.models.dtos.PaymentInfoDto;
import Project.VirtualBanking.models.entities.Parent;
import Project.VirtualBanking.models.entities.PaymentInfo;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public class PaymentInfoValidationCheck {

    public static void saveCreditCardValidationCheck(
            PaymentInfoDto paymentInfoDto, Parent parent, List<PaymentInfo> paymentsInfo
    ) {
        if (!parent.isActive()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Konto rodzica jest nieaktywne");
        }
        if (!parent.isEmailAddressVerified()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Adres e-mail rodzica nie został zweryfikowany");
        }
        if (parent.getPaymentInfo().stream().anyMatch(paymentMethod -> paymentMethod.isActive())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Rodzic ma już aktywną metodę płatności");
        }

        for(PaymentInfo paymentInfo : paymentsInfo) {
            if(paymentInfo.isActive() && paymentInfo.getCardNumber().equals(paymentInfoDto.getCardNumber())) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "W systemie istnieje już aktywna metoda płatności z podanym numerem karty"
                );
            }
        }

        CardNumberValidationCheck.cardNumberValidationCheck(paymentInfoDto.getCardNumber());
        CardHolderNameValidationCheck.cardHolderNameValidationCheck(paymentInfoDto.getCardHolderName());
        CardExpirationDateValidationCheck.cardExpirationDateValidationCheck(paymentInfoDto.getCardExpirationDate());
        CardCvvValidationCheck.cardCvvValidationCheck(paymentInfoDto.getCardCvv());
    }
}
