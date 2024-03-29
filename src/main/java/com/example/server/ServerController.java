package com.example.server;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("/apiproxy")
@CrossOrigin(origins = "http://localhost:3000")
public class ServerController {

    private final RestTemplate restTemplate;

    public ServerController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // Post till Canvas kalendern.
    @PostMapping("/test")
    public String test(@RequestBody Calendarevent requestBody){
        String targetApiUrl = "https://ltu.instructure.com/api/v1/calendar_events.json";
        MultiValueMap<String, Object> formData = new LinkedMultiValueMap<>();
        formData.add("calendar_event[context_code]", requestBody.getContextCode());
        formData.add("calendar_event[title]", requestBody.getTitle());
        formData.add("calendar_event[start_at]", requestBody.getStartAt());
        formData.add("calendar_event[end_at]", requestBody.getEndAt());
        formData.add("calendar_event[description]", requestBody.getDescription());
        formData.add("calendar_event[location_name]", requestBody.getLocationName());

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer 3755~fDHYldReJsZKJso1TTFNXmsDHbFp6C7LxMpgOIE7DAdU1GTw8WtQPUqwaHwzYRDk");
        headers.set("Cookie", "_csrf_token=q9c63MoetjArmOKlteHtpzMRPg1jluLB0THnw46N20PzoXGxqU%2FGdmza1uiAu8LqYyBZQ0ympZabVbCU296pMQ%3D%3D; _legacy_normandy_session=6q4Gfq8xWbFER3tJwqQDbw.NDHsOPhEUYEjqir8JtZIkIFv932hWAgp9nbqmhnpZdU4BbVg74DkOTgZZ2n8yoGCQQtJ3hP4H_4wYQvnh_muTiRRXy5uuyMWMXad5RLcX-ELn39VV-pT4MF9hMOKzGyG.WLtukKan9gz1Pkv0_lQiYPD2odk.ZYKdOQ; canvas_session=6q4Gfq8xWbFER3tJwqQDbw.NDHsOPhEUYEjqir8JtZIkIFv932hWAgp9nbqmhnpZdU4BbVg74DkOTgZZ2n8yoGCQQtJ3hP4H_4wYQvnh_muTiRRXy5uuyMWMXad5RLcX-ELn39VV-pT4MF9hMOKzGyG.WLtukKan9gz1Pkv0_lQiYPD2odk.ZYKdOQ; log_session_id=5fe15f84194ba58a7ecf1178596827b4");
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(formData, headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(targetApiUrl, HttpMethod.POST, requestEntity, String.class);
        return responseEntity.toString();
    }

    //Get från Canvas kalendern.
    @GetMapping("/canvasget")
    public ResponseEntity<String> getCanvas(){
        String targetApiUrl = "https://ltu.instructure.com/api/v1/calendar_events.json?all_events=true";
        MultiValueMap<String, Object> formData = new LinkedMultiValueMap<>();
        formData.add("all_events", "true");
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer 3755~fDHYldReJsZKJso1TTFNXmsDHbFp6C7LxMpgOIE7DAdU1GTw8WtQPUqwaHwzYRDk");
        headers.set("Cookie", "_csrf_token=q9c63MoetjArmOKlteHtpzMRPg1jluLB0THnw46N20PzoXGxqU%2FGdmza1uiAu8LqYyBZQ0ympZabVbCU296pMQ%3D%3D; _legacy_normandy_session=6q4Gfq8xWbFER3tJwqQDbw.NDHsOPhEUYEjqir8JtZIkIFv932hWAgp9nbqmhnpZdU4BbVg74DkOTgZZ2n8yoGCQQtJ3hP4H_4wYQvnh_muTiRRXy5uuyMWMXad5RLcX-ELn39VV-pT4MF9hMOKzGyG.WLtukKan9gz1Pkv0_lQiYPD2odk.ZYKdOQ; canvas_session=6q4Gfq8xWbFER3tJwqQDbw.NDHsOPhEUYEjqir8JtZIkIFv932hWAgp9nbqmhnpZdU4BbVg74DkOTgZZ2n8yoGCQQtJ3hP4H_4wYQvnh_muTiRRXy5uuyMWMXad5RLcX-ELn39VV-pT4MF9hMOKzGyG.WLtukKan9gz1Pkv0_lQiYPD2odk.ZYKdOQ; log_session_id=5fe15f84194ba58a7ecf1178596827b4");
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(targetApiUrl, HttpMethod.GET, requestEntity, String.class);
        return ResponseEntity.status(responseEntity.getStatusCode()).body(responseEntity.getBody());
    }
}