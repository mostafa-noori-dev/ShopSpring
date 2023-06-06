package test.shop.app.services.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import test.shop.app.entities.products.Product;
import test.shop.app.entities.products.ProductCategory;
import test.shop.app.entities.site.Slider;
import test.shop.app.helper.exceptions.DataNotFoundException;
import test.shop.app.repositories.products.ProductCategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductCategoryService {
    @Autowired
    private ProductCategoryRepository repository;

    @Autowired
    private ProductService productService;

    public List<ProductCategory> findAllEnableIsTrue(){
        return repository.findAllByEnableIsTrue(Sort.by("id"));
    }

    public List<ProductCategory> getAll(Integer pageSize, Integer pageNumber) {
        Pageable page = PageRequest.of(pageNumber, pageSize, Sort.by("id"));
        Page<ProductCategory> all = repository.findAll(page);
        return all.toList();
    }

    public long getAllCount() {
        return repository.count();
    }

    public ProductCategory getById(long id){
        Optional<ProductCategory> data = repository.findById(id);
        if(data.isPresent()) return data.get();
        return null;
    }

    public ProductCategory add(ProductCategory data){
        return repository.save(data);
    }

    public ProductCategory update(ProductCategory data) throws DataNotFoundException {
        ProductCategory oldData = getById(data.getId());
        if(oldData==null)
            throw new DataNotFoundException("data with id: "+data.getId()+" not found");
        oldData.setTitle(data.getTitle());
        oldData.setEnable(data.isEnable());
        oldData.setDescription(data.getDescription());
        oldData.setImage(data.getImage());
        return repository.save(oldData);
    }

    public boolean deleteById(long id) throws Exception {
        ProductCategory oldData = getById(id);
        if(oldData==null)
            throw new DataNotFoundException("data with id: "+id+" not found");
        List<Product> productsList = productService.findAllByCategory(id);
        if(productsList.size()>0)
            throw new Exception("Please delete products in this category at first");
        repository.deleteById(id);
        return true;
    }
}
