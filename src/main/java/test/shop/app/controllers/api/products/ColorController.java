package test.shop.app.controllers.api.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import test.shop.app.entities.products.Color;
import test.shop.app.entities.site.Nav;
import test.shop.app.helper.ui.ResponseStatus;
import test.shop.app.helper.ui.ServiceResponse;
import test.shop.app.services.products.ColorService;

import java.util.List;

@RestController
@RequestMapping("/api/color")
public class ColorController {
    @Autowired
    private ColorService service;

    @GetMapping("/getAll")
    public ServiceResponse<Color> getAll(
            @RequestParam Integer pageSize,
            @RequestParam Integer pageNumber
    ) {
        try {
            List<Color> result = service.getAll(pageSize,pageNumber);
            long totalCount=service.getAllCount();
            return new ServiceResponse<Color>(ResponseStatus.SUCCESS, result,totalCount);
        } catch (Exception e) {
            return new ServiceResponse<Color>(e);
        }
    }

    @GetMapping("/")
    public ServiceResponse<Color> getAll(){
        try{
            List<Color> result=service.getAll();
            return new ServiceResponse<Color>(ResponseStatus.SUCCESS,result);
        }catch (Exception e){
            return new ServiceResponse<Color>(e);
        }
    }

    @GetMapping("/{id}")
    public ServiceResponse<Color> getById(@PathVariable long id){
        try{
            Color result=service.getById(id);
            return new ServiceResponse<Color>(ResponseStatus.SUCCESS,result);
        }catch (Exception e){
            return new ServiceResponse<Color>(e);
        }
    }

    @PostMapping("/")
    public ServiceResponse<Color> add(@RequestBody Color data){
        try{
            Color result=service.add(data);
            return new ServiceResponse<Color>(ResponseStatus.SUCCESS,result);
        }catch (Exception e){
            return new ServiceResponse<Color>(e);
        }
    }
    @PutMapping("/")
    public ServiceResponse<Color> update(@RequestBody Color data){
        try{
            Color result=service.update(data);
            return new ServiceResponse<Color>(ResponseStatus.SUCCESS,result);
        }catch (Exception e){
            return new ServiceResponse<Color>(e);
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
