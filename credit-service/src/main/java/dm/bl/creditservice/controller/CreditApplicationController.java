package dm.bl.creditservice.controller;


import dm.bl.api.CreditApplicationsApi;
import dm.bl.creditservice.mapper.CreditApplicationMapper;
import dm.bl.creditservice.service.CreditApplicationService;
import dm.bl.model.CreditApplicationRequest;
import dm.bl.model.CreditApplicationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class CreditApplicationController implements CreditApplicationsApi {
    private final CreditApplicationService creditApplicationService;
    private final CreditApplicationMapper creditApplicationMapper;

    @Override
    public ResponseEntity<CreditApplicationResponse> createCreditApplication(CreditApplicationRequest creditApplicationRequest) {
        return ResponseEntity.ok(creditApplicationMapper.toCreditApplicationResponse(creditApplicationService.save(creditApplicationRequest)));
    }
}
