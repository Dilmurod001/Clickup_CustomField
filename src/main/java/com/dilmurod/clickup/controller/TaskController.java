package com.dilmurod.clickup.controller;

import com.dilmurod.clickup.constants.Constanta;
import com.dilmurod.clickup.entity.Task;
import com.dilmurod.clickup.payload.ApiResponse;
import com.dilmurod.clickup.payload.CustValDto;
import com.dilmurod.clickup.payload.CustomFieldDto;
import com.dilmurod.clickup.repository.TaskRepository;
import com.dilmurod.clickup.service.CustomFieldService;
import com.dilmurod.clickup.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/api/task")
public class TaskController {
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    TaskService taskService;
    @Autowired
    CustomFieldService customFieldService;


    @PostMapping
    public HttpEntity<?> add(@RequestBody Task task) {
        ApiResponse responseAdd = taskService.addTask(task);
        return ResponseEntity.ok(responseAdd);
    }

    @PostMapping("/addCustomField")
    public HttpEntity<?> addCustomField(@RequestBody CustomFieldDto customFieldDto) {
        ApiResponse responseAdd = customFieldService.addCustomField(customFieldDto, Constanta.TABLE_TASK);
        return ResponseEntity.status(responseAdd.isSuccess() ? 201 : 409).body(responseAdd.getMessage());

    }

    @GetMapping("/customFieldList/{id}")
    public HttpEntity<?> getList(@PathVariable Long id) {
        ApiResponse response = customFieldService.list(id, Constanta.TABLE_TASK);
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response.getMessage());
    }

    @PostMapping("/custValue")
    public HttpEntity<?> addCusVal(@RequestBody CustValDto custValDto) throws ParseException {
        ApiResponse apiResponse=customFieldService.addCustVal(custValDto);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse.getMessage());
    }

    @GetMapping("/listValueSorted/{id}")
    public HttpEntity<?> listSortedValue(@PathVariable Long id, @RequestParam boolean isSort){
        ApiResponse response = customFieldService.sortCustomField(id, Constanta.TABLE_TASK,isSort);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/list")
    public HttpEntity<?> list(){
        return ResponseEntity.ok(taskRepository.getAllByCustomField());
    }


    @GetMapping("/listValue")
    public HttpEntity<?> listValue(){
        return ResponseEntity.ok(taskRepository.getAll());
    }
}
