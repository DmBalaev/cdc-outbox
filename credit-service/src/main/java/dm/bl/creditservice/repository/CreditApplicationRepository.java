package dm.bl.creditservice.repository;

import dm.bl.creditservice.entity.CreditApplicationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CreditApplicationRepository extends JpaRepository<CreditApplicationEntity, UUID> {
}
