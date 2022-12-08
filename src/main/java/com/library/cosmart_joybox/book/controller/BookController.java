package com.library.cosmart_joybox.book.controller;

import com.library.cosmart_joybox.book.entity.PickupEntry;
import com.library.cosmart_joybox.book.service.BookService;
import com.library.cosmart_joybox.exception.EmptyResultException;
import com.library.cosmart_joybox.exception.ServiceException;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/book")
public class BookController {

    @Autowired
    private BookService service;

    @PostMapping(value = "/pickup")
    public ResponseEntity submitPickupSchedule(@NonNull @RequestBody PickupEntry entry) {
        var response = service.savePickupSchedule(entry);
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "")
    public ResponseEntity getBookList(@RequestParam(value = "subject", defaultValue = "love") String subject) {
        try {
            var response = service.getBookListBySubject(subject);
            return ResponseEntity.ok(response);
        } catch (ServiceException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        } catch (EmptyResultException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping(value = "/pickup")
    public ResponseEntity getPickUpSchedule() {
        var pickupSchedules = service.getPickupSchedules();
        return ResponseEntity.ok(pickupSchedules);
    }

}
