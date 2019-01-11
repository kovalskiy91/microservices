package kovalskiy91.selfstudy.microservices.api.gateway.boundary;

import kovalskiy91.selfstudy.microservices.api.gateway.control.AccountRepository;
import kovalskiy91.selfstudy.microservices.api.gateway.entity.AccountEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountsEndpoint {
    private final AccountRepository repository;

    @GetMapping
    public Iterable<AccountEntity> findAll() {
        return repository.findAll();
    }

}
