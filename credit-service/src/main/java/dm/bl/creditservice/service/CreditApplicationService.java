package dm.bl.creditservice.service;

import dm.bl.creditservice.entity.CreditApplicationEntity;
import dm.bl.creditservice.entity.CreditStatus;
import dm.bl.creditservice.repository.CreditApplicationRepository;
import dm.bl.model.CreditApplicationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreditApplicationService {
    private final CreditApplicationRepository creditApplicationRepository;

    @Transactional
    public CreditApplicationEntity save(CreditApplicationRequest request) {
        CreditApplicationEntity newCreditApplication = CreditApplicationEntity.builder()
                .creationAt(LocalDate.now())
                .amount(BigDecimal.valueOf(request.getAmount()))
                .status(CreditStatus.CREATED)
                .term(request.getTermInMonths())
                .build();
        return creditApplicationRepository.save(newCreditApplication);
    }

    @Transactional(readOnly = true)
    public List<CreditApplicationEntity> getAllApplication() {
        return creditApplicationRepository.findAll();
    }

    @Transactional
    public CreditApplicationEntity updateStatus(UUID applicationid, CreditStatus status) {
        CreditApplicationEntity application = creditApplicationRepository.findById(applicationid)
                .orElseThrow(() -> new RuntimeException("Application not found"));
        application.setStatus(status);
        application.setUpdateAt(LocalDate.now());
        return creditApplicationRepository.save(application);
    }
}

