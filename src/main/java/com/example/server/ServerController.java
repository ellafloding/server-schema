package com.example.server;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;


@RestController
@RequestMapping("/apiproxy")
@CrossOrigin(origins = "http://localhost:3000/")
public class ServerController {

    private final RestTemplate restTemplate;

    public ServerController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostMapping
    public ResponseEntity<String> proxyCalendarEventRequest(@RequestBody String requestBody) {
        String targetApiUrl = "https://ltu.instructure.com/api/v1/calendar_events.json";

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer 3755~ibTv6HTwA02LPjard6bpFngTsfYw3ZKKU4PeJlionVo2hr5lL4lv0hjrE44NED5g");
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(targetApiUrl, HttpMethod.POST, requestEntity, String.class);

        return ResponseEntity.status(responseEntity.getStatusCode()).body(responseEntity.getBody());
    }

}
