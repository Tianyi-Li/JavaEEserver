package com.info5059.casestudy.po;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class PurchaseOrderController {

    @Autowired
    private PurchaseOrderDAO purchaseOrderDAO;

    @PostMapping("/api/pos")
    public ResponseEntity<Long> addOne(@RequestBody PurchaseOrder clientrep) {
        Long purchaseOrderId = purchaseOrderDAO.create(clientrep);
        return new ResponseEntity<Long>(purchaseOrderId, HttpStatus.OK);
    }

    @GetMapping("/api/pos/{id}")
    public ResponseEntity<Iterable<PurchaseOrder>> findByVendorId(@PathVariable long id) {
        Iterable<PurchaseOrder> vendorReports = purchaseOrderDAO.findByVendor(id);
        return new ResponseEntity<Iterable<PurchaseOrder>>(vendorReports, HttpStatus.OK);
    }
}
