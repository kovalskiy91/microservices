package kovalskiy91.selfstudy.microservices.api.gateway.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Setter
@Getter
@Entity(name = "account")
public class AccountEntity {
    @Id
    private Long id;
    private String email;
}
