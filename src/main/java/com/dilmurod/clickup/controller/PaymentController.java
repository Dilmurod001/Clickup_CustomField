package com.dilmurod.clickup.controller;

import com.dilmurod.clickup.constants.Constanta;
import com.dilmurod.clickup.payload.ApiResponse;
import com.dilmurod.clickup.payload.CustValDto;
import com.dilmurod.clickup.payload.CustomFieldDto;
import com.dilmurod.clickup.service.CustomFieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    CustomFieldService customFieldService;

    @PostMapping("/addCustomField")
    public HttpEntity<?> add(@RequestBody CustomFieldDto customFieldDto){
        ApiResponse responseAdd = customFieldService.addCustomField(customFieldDto, Constanta.TABLE_PAYMENT);
        return ResponseEntity.ok(responseAdd);
    }

    @PostMapping("/custValue")
    public HttpEntity<?> addCusVal(@Valid @RequestBody CustValDto custValDto) throws ParseException {
        ApiResponse apiResponse=customFieldService.addCustVal(custValDto, Constanta.TABLE_PAYMENT );
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse.getMessage());
    }

    @GetMapping("/listValueSorted/{id}")
    public HttpEntity<?> listSortedValue(@PathVariable Long id,boolean isSort){
        ApiResponse response = customFieldService.sortCustomField(id, Constanta.TABLE_PAYMENT,isSort);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/customFieldList/{id}")
    public HttpEntity<?> getList(@PathVariable Long id) {
        ApiResponse response = customFieldService.list(id, Constanta.TABLE_PAYMENT);
        return ResponseEntity.ok(response);
    }
}
