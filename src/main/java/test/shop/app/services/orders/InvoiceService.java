package test.shop.app.services.orders;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import test.shop.app.entities.orders.Invoice;
import test.shop.app.entities.products.Size;
import test.shop.app.helper.exceptions.DataNotFoundException;
import test.shop.app.repositories.orders.InvoiceRepository;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {
    @Autowired
    private InvoiceRepository repository;

    public List<Invoice> findByCustomer(long customerId) {
        return repository.findAllByCustomer(customerId);
    }
    public List<Invoice> findByCustomer(long customerId,Integer pageSize, Integer pageNumber) {
        Pageable page = PageRequest.of(pageNumber, pageSize, Sort.by("id"));
        Page<Invoice> all = repository.findByCustomer(customerId,page);
        return all.toList();
    }
    public long getAllCount() {
        return repository.count();
    }
    public long getPayedCount() {
        return repository.countByPayedDateIsNotNull();
    }

    public Invoice getById(long id) {
        Optional<Invoice> data = repository.findById(id);
        if (data.isPresent()) return data.get();
        return null;
    }

    public Invoice add(Invoice data) {
        return repository.save(data);
    }

    public Invoice update(Invoice data) throws DataNotFoundException {
        Invoice oldData = getById(data.getId());
        if (oldData == null)
            throw new DataNotFoundException("data with id: " + data.getId() + " not found");
        oldData.setPayedDate(data.getPayedDate());
        return repository.save(oldData);
    }

    public boolean deleteById(long id) throws DataNotFoundException {
        Invoice oldData = getById(id);
        if (oldData == null)
            throw new DataNotFoundException("data with id: " + id + " not found");
        repository.deleteById(id);
        return true;

    }
}
