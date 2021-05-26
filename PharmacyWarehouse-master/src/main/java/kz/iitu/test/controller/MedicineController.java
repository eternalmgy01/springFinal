package kz.iitu.test.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import kz.iitu.test.entity.Medicine;
import kz.iitu.test.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicines")
@Api(value = "Medicine Controller Class")
@ApiResponses( value = {
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 401, message = "You are not authorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Resource not found")
}
)
public class MedicineController {

    @Autowired
    MedicineService medicineService;

    @GetMapping("/list")
    @ApiOperation(value = "To get list of all medicines", response = List.class)
    public List<Medicine> findAllMedicines() {
        return medicineService.findMedicines();
    }

    @PostMapping("/add")
    @ApiOperation(value = "To add medicine (allowed to Manager and admin)")
    public Medicine addMedicine(@RequestBody Medicine medicine) {
        medicineService.addMedicine(medicine);
        return medicine;
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "To delete medicine (allowed to Manager and admin)")
    public void deleteMedicine(@PathVariable Long id) {
        medicineService.deleteMedicine(medicineService.getOne(id));
    }

    @PostMapping("/edit")
    @ApiOperation(value = "To update medicine (allowed to Manager and admin)")
    public Medicine updateMedicine(@RequestBody Medicine medicine) {
        medicineService.updateMedicine(medicine);
        return medicine;
    }

    @GetMapping("/find/{id}")
    @ApiOperation(value = "To find medicine (allowed to Manager and admin)")
    public Medicine getOne(@PathVariable Long id) {
        return medicineService.getOne(id);
    }
}
