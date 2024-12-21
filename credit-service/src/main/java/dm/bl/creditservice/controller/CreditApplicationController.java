package dm.bl.creditservice.controller;


import dm.bl.api.CreditApplicationsApi;
import dm.bl.creditservice.entity.CreditApplicationEntity;
import dm.bl.creditservice.entity.CreditStatus;
import dm.bl.creditservice.mapper.CreditApplicationMapper;
import dm.bl.creditservice.service.CreditApplicationService;
import dm.bl.model.CreditApplicationRequest;
import dm.bl.model.CreditApplicationResponse;
import dm.bl.model.UpdateCreditApplicationStatusRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class CreditApplicationController implements CreditApplicationsApi {
    private final CreditApplicationService creditApplicationService;
    private final CreditApplicationMapper creditApplicationMapper;

    public CreditApplicationController(CreditApplicationService creditApplicationService, CreditApplicationMapper creditApplicationMapper) {
        this.creditApplicationService = creditApplicationService;
        this.creditApplicationMapper = creditApplicationMapper;
    }

    @Override
    public ResponseEntity<CreditApplicationResponse> createCreditApplication(CreditApplicationRequest creditApplicationRequest) {
        return ResponseEntity.ok(creditApplicationMapper.toCreditApplicationResponse(creditApplicationService.save(creditApplicationRequest)));
    }

    @Override
    public ResponseEntity<List<CreditApplicationResponse>> getAllCreditApplications() {
        return ResponseEntity.ok(creditApplicationService.getAllApplication().stream()
                .map(creditApplicationMapper::toCreditApplicationResponse)
                .toList());
    }

    @Override
    public ResponseEntity<CreditApplicationResponse> updateCreditApplicationStatus(UUID id, UpdateCreditApplicationStatusRequest updateCreditApplicationStatusRequest) {
        CreditApplicationEntity application = creditApplicationService.updateStatus(id, CreditStatus.valueOf(updateCreditApplicationStatusRequest.getStatus().toUpperCase()));
        return ResponseEntity.ok(creditApplicationMapper.toCreditApplicationResponse(application));
    }
}
