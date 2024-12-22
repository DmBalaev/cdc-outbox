package dm.bl.creditservice.service;

import dm.bl.creditservice.entity.CreditApplicationEntity;
import dm.bl.creditservice.entity.CreditStatus;
import dm.bl.creditservice.mapper.CreditApplicationMapper;
import dm.bl.creditservice.publisher.KafkaPublisher;
import dm.bl.creditservice.repository.CreditApplicationRepository;
import dm.bl.model.CreditApplicationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
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
    private final ApplicationEventPublisher applicationEventPublisher;

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

        applicationEventPublisher.publishEvent(status);
        return creditApplicationRepository.save(application);
    }
}

