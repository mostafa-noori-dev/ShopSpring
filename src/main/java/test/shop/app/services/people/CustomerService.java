package test.shop.app.services.people;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import test.shop.app.entities.people.Customer;
import test.shop.app.entities.people.User;
import test.shop.app.helper.exceptions.DataNotFoundException;
import test.shop.app.repositories.people.CustomerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository repository;

    public Customer getByUserId(long id){
        Customer data = repository.findByUserId(id);
        return data;
    }

    public List<Customer> getAll(Integer pageSize, Integer pageNumber) {
        Pageable page = PageRequest.of(pageNumber, pageSize, Sort.by("id"));
        Page<Customer> all = repository.findAll(page);
        return all.toList();
    }

    public long getAllCount() {
        return repository.count();
    }


    public Customer getById(long id) {
        Optional<Customer> data = repository.findById(id);
        if (data.isPresent()) return data.get();
        return null;
    }

    public Customer add(Customer data) {
        return repository.save(data);
    }

    public Customer update(Customer data) throws DataNotFoundException {
        Customer oldData = getById(data.getId());
        if (oldData == null)
            throw new DataNotFoundException("data with id: " + data.getId() + " not found");
        oldData.setAddress(data.getAddress());
        oldData.setEmail(data.getEmail());
        oldData.setFirstName(data.getFirstName());
        oldData.setMobile(data.getMobile());
        oldData.setLastName(data.getLastName());
        oldData.setPostalCode(data.getPostalCode());
        oldData.setTel(data.getTel());
        return repository.save(oldData);
    }

    public boolean deleteById(long id) throws DataNotFoundException {
        Customer oldData = getById(id);
        if (oldData == null)
            throw new DataNotFoundException("data with id: " + id + " not found");
        repository.deleteById(id);
        return true;
    }
}
