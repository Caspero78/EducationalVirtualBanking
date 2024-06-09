package Project.VirtualBanking.models.entities;


import Project.VirtualBanking.Encryption.EncryptEntities.EncryptChild;
import Project.VirtualBanking.models.dtos.ChildDto;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Child {

    @Id
    @GeneratedValue
    private Integer childId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parentId", referencedColumnName = "parentId")
    private Parent parent;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false)
    private LocalDate dateOfBirth;

    @Column(nullable = false)
    private String emailAddress;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    private LocalDateTime accountCreationDate;

    private boolean active;

    private String details;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "keyID", referencedColumnName = "keyID")
    private EncryptionKey encryptionKey;

    public Child() {
    }

    public Child(Parent parent, String name, String surname, LocalDate dateOfBirth,
                 String emailAddress, String username, String password, String details,
                 EncryptionKey encryptionKey) {
        this.parent = parent;
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.emailAddress = emailAddress;
        this.username = username;
        this.password = password;
        this.accountCreationDate = LocalDateTime.now();
        this.active = true;
        this.details = details;
        this.encryptionKey = encryptionKey;
    }

    public Integer getChildId() {
        return childId;
    }
    public void setChildId(Integer childId) {
        this.childId = childId;
    }

    public Parent getParent() {
        return parent;
    }
    public void setParent(Parent parent) {
        this.parent = parent;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmailAddress() {
        return emailAddress;
    }
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getAccountCreationDate() {
        return accountCreationDate;
    }
    public void setAccountCreationDate(LocalDateTime accountCreationDate) {
        this.accountCreationDate = accountCreationDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getDetails() {
        return details;
    }
    public void setDetails(String details) {
        this.details = details;
    }

    public EncryptionKey getEncryptionKey() {
        return encryptionKey;
    }
    public void setEncryptionKey(EncryptionKey encryptionKey) {
        this.encryptionKey = encryptionKey;
    }

    public static Child fromDto(ChildDto childDto, Parent parent, EncryptionKey encryptionKey) {
        Child child = new Child(
                parent,
                childDto.getName(),
                childDto.getSurname(),
                childDto.getDateOfBirth(),
                childDto.getEmailAddress(),
                childDto.getUsername(),
                childDto.getPassword(),
                childDto.getDetails(),
                encryptionKey
        );
        EncryptChild.encryptChild(child, child.getEncryptionKey().getEncryptionKey());
        return child;
    }
}