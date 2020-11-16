package com.info5059.casestudy.po;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//@CrossOrigin
@RestController
public class PurchaseOrderController {

    @Autowired
    private PurchaseOrderDAO purchaseOrderDAO;

    @PostMapping("/api/pos")
    public ResponseEntity<Long> addOne(@RequestBody PurchaseOrder clientrep) {
        Long purchaseOrderId = purchaseOrderDAO.create(clientrep);
        return new ResponseEntity<Long>(purchaseOrderId, HttpStatus.OK);
    }
}
