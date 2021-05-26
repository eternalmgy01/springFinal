package kz.iitu.test.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import kz.iitu.test.entity.Firm;
import kz.iitu.test.entity.Role;
import kz.iitu.test.repository.RoleRepository;
import kz.iitu.test.service.FirmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/firms")
@Api(value = "Firm Controller Class")
@ApiResponses( value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 401, message = "You are not authorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Resource not found")
}
)
public class FirmController {

    @Autowired
    FirmService firmService;
    @Autowired
    RoleRepository roleRepository;
    @GetMapping("")
    @ApiOperation(value = "To get list of firms (allowed to Admin only)", response = List.class)
    public List<Firm> findAll() {
        return firmService.findAll();
    }

    @PostMapping("")
    @ApiOperation(value = "Adding firm manually (allowed to admin only))")
    public void addFirm(@RequestBody Firm firm) {
        firmService.addFirm(firm);
    }

    @PostMapping("/register")
    @Transactional
    @ApiOperation(value = "Method to register", response = List.class)
    public void registerFirm(@RequestBody Firm firm) {
        Role role = roleRepository.findDistinctByName("USER");
        firm.getRoles().add(role);
        firmService.addFirm(firm);
    }
    @PostMapping("/registerManager")
    @ApiOperation(value = "Method to register", response = List.class)
    public void registerManager(@RequestBody Firm firm) {
        Role role = roleRepository.findDistinctByName("MANAGER");
        firm.getRoles().add(role);
        firmService.addFirm(firm);
    }
    @GetMapping("/{id}")
    @ApiOperation(value = "Method to get one Firm", response = List.class)
    public Firm getOne(@PathVariable Long id) {
        return firmService.getOne(id);
    }
}
