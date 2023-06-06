package test.shop.app.services.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import test.shop.app.entities.products.Color;
import test.shop.app.entities.products.Feature;
import test.shop.app.entities.products.Product;
import test.shop.app.entities.products.Size;
import test.shop.app.helper.exceptions.DataNotFoundException;
import test.shop.app.helper.uimodels.product.ProductVM;
import test.shop.app.repositories.products.ProductRepository;

import java.util.*;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    @Autowired
    private FeatureService featureService;

    @Autowired
    private ColorService colorService;

    @Autowired
    private SizeService sizeService;

    @Autowired
    @Lazy
    private ProductCategoryService productCategoryService;

    public List<Product> getAllProducts(Integer pageSize, Integer pageNumber) {
        Pageable page = PageRequest.of(pageNumber, pageSize, Sort.by("id"));
        Page<Product> all = repository.findAll(page);
        return all.toList();
    }

    public long getAllCount() {
        return repository.count();
    }

    public long getExistsCount() {
        return repository.countByExistsIsTrue();
    }
    public long getEnableCount() {
        return repository.countByEnableIsTrue();
    }

    public List<ProductVM> getAllProductsByProductCategoryId(long productCategoryId, Integer pageSize, Integer pageNumber) {
        Pageable page = PageRequest.of(pageNumber, pageSize, Sort.by("id"));
        Page<Product> all = repository.findAllProductsByProductCategoryIdWithPageble(productCategoryId, page);
        List<ProductVM> vmList=new ArrayList<>();
        all.toList().forEach(x-> vmList.add(new ProductVM(x)));
        return vmList;
    }

    public long getAllCountByProductCategoryId(long productCategoryId) {
        return repository.countByCategoryId(productCategoryId);
    }

    public List<Product> findAllByCategory(long productCategoryId) {
        return repository.findAllProductByCategoryId(productCategoryId);
    }

    public List<Product> search(String keyword) {
        return repository.findAllByEnableIsTrueAndTitleContainsOrDescriptionContains(keyword);
    }

    public Product getById(long id) {
        Optional<Product> data = repository.findById(id);
        if (data.isPresent()) return data.get();
        return null;
    }
    public List<ProductVM> findTop6ByOrderByAddDateDesc(){
        List<ProductVM> vmList= new ArrayList<>();
        List<Product> top6=repository.findTop6ByOrderByAddDateDesc();
        top6.forEach(x->vmList.add(new ProductVM(x)));
        return vmList;
    }
    public List<ProductVM> findTop6ByOrderByVisitCountDesc(){
        List<ProductVM> vmList= new ArrayList<>();
        List<Product> top6=repository.findTop6ByOrderByVisitCountDesc();
        top6.forEach(x->vmList.add(new ProductVM(x)));
        return vmList;
    }

    public Product add(ProductVM vm) {
        Product data = vm.convert();
        if (vm.getFeatures() != null)
            vm.getFeatures().forEach(x -> data.addFeature(featureService.getById(x)));
        if (vm.getColors() != null)
            vm.getColors().forEach(x -> data.addColor(colorService.getById(x)));
        if (vm.getSizes() != null)
            vm.getSizes().forEach(x -> data.addSize(sizeService.getById(x)));
        data.setCategory(productCategoryService.getById(vm.getCategoryId()));
        data.setAddDate(new Date());
        return repository.save(data);
    }

    public Product update(ProductVM data) throws DataNotFoundException {
        Product oldData = getById(data.getId());
        if (oldData == null) {
            throw new DataNotFoundException("data with id " + data.getId() + " not found");
        }
        oldData.setTitle(data.getTitle());
        oldData.setEnable(data.isEnable());
        oldData.setDescription(data.getDescription());
        oldData.setImage(data.getImage());
        oldData.setExists(data.isExists());
        oldData.setPrice(data.getPrice());
        if (data.getColors() != null) {
            for (long colorId : data.getColors()) {
                if (!oldData.getColors().stream().map(x -> x.getId()).anyMatch(z -> z == colorId))
                    oldData.addColor(colorService.getById(colorId));
            }
            //Changed
            List<Long> removeColor=new ArrayList<>();
            for (Color color : oldData.getColors()) {
                if (!data.getColors().stream().anyMatch(x -> x == color.getId())) {
                    removeColor.add(color.getId());
                }
            }
            for(Long colorId:removeColor){
                oldData.removeColor(colorId);
            }
        }
        if (data.getSizes() != null) {
            for (long sizeId : data.getSizes()) {
                if (!oldData.getSizes().stream().map(x -> x.getId()).anyMatch(z -> z == sizeId))
                    oldData.addSize(sizeService.getById(sizeId));
            }
            List<Long> removeSize=new ArrayList<>();
            for (Size size : oldData.getSizes()) {
                if (!data.getSizes().stream().anyMatch(x -> x == size.getId())) {
                    removeSize.add(size.getId());
                }
            }
            for(Long sizeId:removeSize){
                oldData.removeSize(sizeId);
            }
        }
        if (data.getFeatures() != null) {
            for (long featureId : data.getFeatures()) {
                if (!oldData.getFeatures().stream().map(x -> x.getId()).anyMatch(z -> z == featureId))
                    oldData.addFeature(featureService.getById(featureId));
            }
            List<Long> removeFeature=new ArrayList<>();
            for (Feature feature : oldData.getFeatures()) {
                if (!data.getFeatures().stream().anyMatch(x -> x == feature.getId())) {
                    removeFeature.add(feature.getId());
                }
            }
            for(Long featureId:removeFeature){
                oldData.removeFeature(featureId);
            }
        }
        return repository.save(oldData);
    }

    public boolean deleteById(long id) throws DataNotFoundException {
        Product oldData = getById(id);
        if (oldData == null)
            throw new DataNotFoundException("data with id: " + id + " not found");
        List<Long> deletingFeatures=new ArrayList<>();
        oldData.getFeatures().forEach(x->deletingFeatures.add(x.getId()));
        repository.deleteById(id);
        deletingFeatures.forEach(x->{
            try {
                featureService.deleteById(x);
            } catch (DataNotFoundException e) {
                throw new RuntimeException(e);
            }
        });

        return true;
    }

    public Product increaseVisitCount(long id) throws DataNotFoundException {
        Product oldData = getById(id);
        if (oldData == null)
            throw new DataNotFoundException("data with id: " + id + " not found");
        oldData.setVisitCount(oldData.getVisitCount() + 1);
        return repository.save(oldData);
    }
}
