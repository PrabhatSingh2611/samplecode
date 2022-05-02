package digital.windmill.audra.rest;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import digital.windmill.audra.storage.StorageService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/resources")
@AllArgsConstructor
public class ResourceController {
    
    private static final String HEADER_CONTENT_TYPE = "Content-Type";
    private static final String HEADER_CONTENT_DISPOSITION = "Content-Disposition";
    private static final ResponseEntity<Resource> NOT_FOUND_RESOURCE_RESPONSE =  ResponseEntity
            .notFound()
            .build();

    private final StorageService service;

    @GetMapping("/{resourceId}")
    public ResponseEntity<Resource> fileById(@PathVariable("resourceId") String resourceId) {
        return service.findById(resourceId)
            .map(this::prepareResponse)
            .orElse(NOT_FOUND_RESOURCE_RESPONSE);
    }

    private ResponseEntity<Resource> prepareResponse(Resource resource) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add(HEADER_CONTENT_TYPE, "application/octet-stream");
        responseHeaders.add(HEADER_CONTENT_DISPOSITION, "attachment; filename=" + resource.getFilename());
        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(resource);
    }

}
