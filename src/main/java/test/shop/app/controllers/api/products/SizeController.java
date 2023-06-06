package test.shop.app.controllers.api.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import test.shop.app.entities.products.Color;
import test.shop.app.entities.products.Size;
import test.shop.app.helper.ui.ResponseStatus;
import test.shop.app.helper.ui.ServiceResponse;
import test.shop.app.services.products.SizeService;

import java.util.List;

@RestController
@RequestMapping("/api/size")
public class SizeController {
    @Autowired
    private SizeService service;

    @GetMapping("/")
    public ServiceResponse<Size> getAll() {
        try {
            List<Size> result = service.getAll();
            long totalCount=service.getAllCount();
            return new ServiceResponse<Size>(ResponseStatus.SUCCESS, result,totalCount);
        } catch (Exception e) {
            return new ServiceResponse<Size>(e);
        }
    }


    @GetMapping("/getAll")
    public ServiceResponse<Size> getAll(
            @RequestParam Integer pageSize,
            @RequestParam Integer pageNumber
    ) {
        try {
            List<Size> result = service.getAll(pageSize,pageNumber);
            long totalCount=service.getAllCount();
            return new ServiceResponse<Size>(ResponseStatus.SUCCESS, result,totalCount);
        } catch (Exception e) {
            return new ServiceResponse<Size>(e);
        }
    }


    @GetMapping("/{id}")
    public ServiceResponse<Size> getById(@PathVariable long id){
        try{
            Size result=service.getById(id);
            return new ServiceResponse<Size>(ResponseStatus.SUCCESS,result);
        }catch (Exception e){
            return new ServiceResponse<Size>(e);
        }
    }


    @PostMapping("/")
    public ServiceResponse<Size> add(@RequestBody Size data){
        try{
            Size result=service.add(data);
            return new ServiceResponse<Size>(ResponseStatus.SUCCESS,result);
        }catch (Exception e){
            return new ServiceResponse<Size>(e);
        }
    }
    @PutMapping("/")
    public ServiceResponse<Size> update(@RequestBody Size data){
        try{
            Size result=service.update(data);
            return new ServiceResponse<Size>(ResponseStatus.SUCCESS,result);
        }catch (Exception e){
            return new ServiceResponse<Size>(e);
        }
    }
    @DeleteMapping("/{id}")
    public ServiceResponse<Boolean> delete(@PathVariable long id){
        try{
            boolean result = service.deleteById(id);
            return new ServiceResponse<Boolean>(ResponseStatus.SUCCESS,result);
        }catch (Exception e){
            return new ServiceResponse<Boolean>(e);
        }
    }

}
