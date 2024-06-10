package Project.VirtualBanking.controllers;

import Project.VirtualBanking.models.dtos.PaymentMethodDto;
import Project.VirtualBanking.services.PaymentMethodService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PaymentMethodController {

    PaymentMethodService paymentMethodService;

    public PaymentMethodController(PaymentMethodService paymentMethodService) {
        this.paymentMethodService = paymentMethodService;
    }

    @PostMapping("/parent/{parentId}/payment-method")
    public ResponseEntity<PaymentMethodDto> savePaymentMethod(
            @RequestBody PaymentMethodDto paymentMethodDto,
            @PathVariable Integer parentId
    ) {
        return ResponseEntity.ok(paymentMethodService.savePaymentMethod(paymentMethodDto, parentId));
    }

    @GetMapping("/payment-methods")
    public ResponseEntity<List<PaymentMethodDto>> findAllPaymentMethods() {
        return ResponseEntity.ok(paymentMethodService.findAllPaymentMethods());
    }

    @GetMapping("/payment-methods/active")
    public ResponseEntity<List<PaymentMethodDto>> findAllActivePaymentMethods() {
        return ResponseEntity.ok(paymentMethodService.findAllActivePaymentMethods());
    }

    @GetMapping("/payment-method/{paymentMethodId}")
    public ResponseEntity<PaymentMethodDto> findPaymentMethodById(@PathVariable Integer paymentMethodId) {
        return ResponseEntity.ok(paymentMethodService.findPaymentMethodById(paymentMethodId));
    }

    @PutMapping("/payment-method/{paymentMethodId}/edit")
    public ResponseEntity<PaymentMethodDto> editPaymentMethod(
            @PathVariable Integer paymentMethodId,
            @RequestBody PaymentMethodDto paymentMethodDto
    ) {
        return ResponseEntity.ok(paymentMethodService.editPaymentMethod(paymentMethodDto, paymentMethodId));
    }

    @PutMapping("/payment-method/{paymentMethodId}/activate")
    public ResponseEntity<PaymentMethodDto> activatePaymentMethod(@PathVariable Integer paymentMethodId) {
        return ResponseEntity.ok(paymentMethodService.activatePaymentMethod(paymentMethodId));
    }

    @PutMapping("/payment-method/{paymentMethodId}/deactivate")
    public ResponseEntity<PaymentMethodDto> deactivatePaymentMethod(@PathVariable Integer paymentMethodId) {
        return ResponseEntity.ok(paymentMethodService.deactivatePaymentMethod(paymentMethodId));
    }

    @DeleteMapping("/payment-method/{paymentMethodId}/delete")
    public ResponseEntity<Void> deletePaymentMethod(@PathVariable Integer paymentMethodId) {
        paymentMethodService.deletePaymentMethod(paymentMethodId);
        return ResponseEntity.noContent().build();
    }
}