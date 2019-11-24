package cz.johnczek.kas.api.controller;

import cz.johnczek.kas.api.request.BaseRequest;
import cz.johnczek.kas.api.response.BaseResponse;
import cz.johnczek.kas.api.service.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BaseController {

    private final BaseService baseService;

    @PostMapping("/kas")
    public BaseResponse defaultMethod(@RequestBody BaseRequest request) {
        return baseService.baseMethod(request.getMessage());
    }
}
