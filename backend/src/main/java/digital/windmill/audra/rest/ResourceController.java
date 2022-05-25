package digital.windmill.audra.rest;

import digital.windmill.audra.dao.entity.ResourceEntity;
import digital.windmill.audra.rest.facade.RestResourceFacade;
import digital.windmill.audra.service.ResourceService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/resources")
@AllArgsConstructor
public class ResourceController {

    private static final String HEADER_CONTENT_TYPE = "Content-Type";
    private static final String HEADER_CONTENT_DISPOSITION = "Content-Disposition";
    private static final ResponseEntity<Resource> NOT_FOUND_RESOURCE_RESPONSE = ResponseEntity
            .notFound()
            .build();

    private final RestResourceFacade resourceFacade;
    private final ResourceService resourceService;

    @GetMapping("/{resourceUuid}")
    public ResponseEntity<Resource> fileById(@PathVariable("resourceUuid") UUID resourceUuid) {
        return resourceService.findResourceByUuid(resourceUuid)
                .map(resourceEntity ->
                        resourceFacade.findById(resourceEntity.getUuid())
                                .map(resource -> prepareResponse(resourceEntity, resource))
                                .orElse(NOT_FOUND_RESOURCE_RESPONSE))
                .orElse(NOT_FOUND_RESOURCE_RESPONSE);
    }

    private ResponseEntity<Resource> prepareResponse(ResourceEntity resourceEntity, Resource resource) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add(HEADER_CONTENT_TYPE, resourceEntity.getMimeType());
        responseHeaders.add(HEADER_CONTENT_DISPOSITION, "attachment; filename=" + resource.getFilename());
        return ResponseEntity.ok()
                .headers(responseHeaders)
                .body(resource);
    }

}
