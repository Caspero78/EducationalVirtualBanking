package Project.VirtualBanking.models.dtos;

import Project.VirtualBanking.models.entities.Subscription;

import java.time.LocalDate;

public class SubscriptionDto {
    private Integer subscriptionId;
    private Integer childId;
    private Integer subscriptionTypeId;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean active;
    private Boolean recursive;
    private String details;

    public SubscriptionDto() {
    }

    public SubscriptionDto(Integer subscriptionId, Integer childId, Integer subscriptionTypeId, LocalDate startDate,
                           LocalDate endDate, Boolean active, Boolean recursive, String details) {
        this.subscriptionId = subscriptionId;
        this.childId = childId;
        this.subscriptionTypeId = subscriptionTypeId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.active = active;
        this.recursive = recursive;
        this.details = details;
    }

    public Integer getSubscriptionId() {
        return subscriptionId;
    }
    public void setSubscriptionId(Integer subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public Integer getChildId() {
        return childId;
    }
    public void setChildId(Integer childId) {
        this.childId = childId;
    }

    public Integer getSubscriptionTypeId() {
        return subscriptionTypeId;
    }
    public void setSubscriptionTypeId(Integer subscriptionTypeId) {
        this.subscriptionTypeId = subscriptionTypeId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Boolean isActive() {
        return active;
    }
    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean isRecursive() {
        return recursive;
    }
    public void setRecursive(Boolean recursive) {
        this.recursive = recursive;
    }

    public String getDetails() {
        return details;
    }
    public void setDetails(String details) {
        this.details = details;
    }

    public static SubscriptionDto fromEntity(Subscription Subscription) {
        return new SubscriptionDto(
                Subscription.getSubscriptionId(),
                Subscription.getChild().getChildId(),
                Subscription.getSubscriptionType().getSubscriptionTypeId(),
                Subscription.getStartDate(),
                Subscription.getEndDate(),
                Subscription.isActive(),
                Subscription.isRecursive(),
                Subscription.getDetails()
        );
    }
}
