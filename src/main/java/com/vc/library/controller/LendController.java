package com.vc.library.controller;

import com.vc.library.LendStatus;
import com.vc.library.dto.LendDto;
import com.vc.library.service.LendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author shankar.s
 */

@RestController
@RequestMapping("/lend")
public class LendController {

    @Autowired
    private LendService lendService;

    @GetMapping(path = "/getLendDetails", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<LendDto> getLendDetails(@RequestParam(name = "status", defaultValue = "PENDING,BORROWED") LendStatus[] status, @RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "10") int size) {
        return lendService.getBookingDetails(status,1L);
    }

    @PostMapping(path = "/request", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void lendItem(@RequestBody LendDto lend) {
        lendService.lendItem(lend);
    }

    @PutMapping(path = "/request/approve/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void approveItem(@PathVariable(name = "id") Long id) {
        lendService.approveLendItem(id);
    }

    @PutMapping(path = "/request/return/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void returnItem(@PathVariable(name = "id") Long id) {
        lendService.returnLendItem(id);
    }

    @DeleteMapping(path = "/request/cancel/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void cancelItem(@PathVariable(name = "id") Long id) {
        lendService.cancelLendItem(id);
    }
}
