package kz.iitu.test.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import kz.iitu.test.entity.Request;
import kz.iitu.test.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/requests")
@Api(value = "Request Controller Class")
@ApiResponses( value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 401, message = "You are not authorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Resource not found")
}
)
public class RequestController {

    @Autowired
    RequestService requestService;

    @GetMapping("")
    @ApiOperation(value = "To get list of all requests(allowed only for manager)", response = List.class)
    public List<Request> findAll() {
        return requestService.findAll();
    }

    @GetMapping("/accepted")
    @ApiOperation(value = "To get list of all accepted requests", response = List.class)
    public List<Request> findAccepted(){
        return requestService.findAccepted();
    }

    @GetMapping("/pending")
    @ApiOperation(value = "To get list of all pending requests", response = List.class)
    public List<Request> findPending(){
        return requestService.findPending();
    }

    @PostMapping("/make")
    @ApiOperation(value = "Available for users, method to make request")
    public void makeRequest(@RequestParam Long medicineId, @RequestParam Long firmId, @RequestParam Long quantity) {
        requestService.makeRequest(medicineId, firmId, quantity);
    }

    @PostMapping("/accept")
    @ApiOperation(value = "To accept some requests (Acceptable by manager)")
    public void acceptRequest(@RequestBody Request request) {
        requestService.acceptRequest(request);
    }

    @PostMapping("/update")
    @ApiOperation(value = "To change some request")
    public void updateRequest(@RequestBody Request request) {
        requestService.updateRequest(request);
    }
}
