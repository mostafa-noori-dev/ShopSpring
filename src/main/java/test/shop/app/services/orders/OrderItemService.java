package test.shop.app.services.orders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.shop.app.entities.orders.OrderItem;
import test.shop.app.helper.exceptions.DataNotFoundException;
import test.shop.app.repositories.orders.OrderItemRepository;

import java.util.Optional;

@Service
public class OrderItemService {
    @Autowired
    private OrderItemRepository repository;

    public OrderItem getById(long id){
        Optional<OrderItem> data = repository.findById(id);
        if(data.isPresent()) return data.get();
        return null;
    }

    public OrderItem add(OrderItem data){
        return repository.save(data);
    }

    public OrderItem update(OrderItem data) throws DataNotFoundException {
        OrderItem oldData = getById(data.getId());
        if(oldData==null)
            throw new DataNotFoundException("data with id: "+data.getId()+" not found");
        oldData.setCount(data.getCount());
        oldData.setPrice(data.getPrice());
        return repository.save(oldData);
    }

    public boolean deleteById(long id) throws DataNotFoundException {
        OrderItem oldData = getById(id);
        if(oldData==null)
            throw new DataNotFoundException("data with id: "+id+" not found");
        repository.deleteById(id);
        return true;
    }
}
