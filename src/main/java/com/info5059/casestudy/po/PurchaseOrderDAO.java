package com.info5059.casestudy.po;

import com.info5059.casestudy.product.Product;
import com.info5059.casestudy.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.util.Date;


@Component
public class PurchaseOrderDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public Long create(PurchaseOrder clientrep) {
        PurchaseOrder realPurchaseOrder = new PurchaseOrder();
        realPurchaseOrder.setPodate(new Date());
        realPurchaseOrder.setVendorid(clientrep.getVendorid());
        realPurchaseOrder.setAmount(clientrep.getAmount());
        entityManager.persist(realPurchaseOrder);

        Iterable<Product> products = productRepository.findAll();

        for(PurchaseOrderLineItem item:clientrep.getItems()) {
            PurchaseOrderLineItem realItem = new PurchaseOrderLineItem();
            realItem.setPoid(realPurchaseOrder.getId());
            realItem.setProductid(item.getProductid());
            realItem.setPrice(item.getPrice());
            realItem.setQty(item.getQty());
            entityManager.persist(realItem);
            // Update product qoo
            Product pp = productRepository.findById(item.getProductid()).get();
            pp.setQoo(item.getQty());
            productRepository.save(pp);
        }
        return realPurchaseOrder.getId();
    }

    public PurchaseOrder findOne(Long id) {
        PurchaseOrder purchaseOrder = entityManager.find(PurchaseOrder.class, id);
        if (purchaseOrder == null) {
            throw new EntityNotFoundException("Can't find purchaseorder for ID "
                    + id);
        }
        return purchaseOrder;
    }

}
