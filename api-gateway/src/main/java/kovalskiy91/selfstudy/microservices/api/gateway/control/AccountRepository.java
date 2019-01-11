package kovalskiy91.selfstudy.microservices.api.gateway.control;

import kovalskiy91.selfstudy.microservices.api.gateway.entity.AccountEntity;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<AccountEntity, Long> {
}
