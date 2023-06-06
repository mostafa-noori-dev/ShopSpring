package test.shop.app.controllers.api.site;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import test.shop.app.entities.site.Nav;
import test.shop.app.helper.ui.ResponseStatus;
import test.shop.app.helper.ui.ServiceResponse;
import test.shop.app.services.site.NavService;

import java.util.List;

@RestController
@RequestMapping("/api/nav")
public class NavController {
    @Autowired
    private NavService service;

    @GetMapping("")
    public ServiceResponse<Nav> get() {
        try {
            List<Nav> result = service.findAllOrderByItemOrder();
            return new ServiceResponse<Nav>(ResponseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceResponse<Nav>(e);
        }
    }
    @GetMapping("/getAll")
    public ServiceResponse<Nav> getAll(
            @RequestParam Integer pageSize,
            @RequestParam Integer pageNumber
    ) {
        try {
            List<Nav> result = service.getAll(pageSize,pageNumber);
            long totalCount=service.getAllCount();
            return new ServiceResponse<Nav>(ResponseStatus.SUCCESS, result,totalCount);
        } catch (Exception e) {
            return new ServiceResponse<Nav>(e);
        }
    }

    @GetMapping("/{id}")
    public ServiceResponse<Nav> getById(@PathVariable long id) {
        try {
            Nav result = service.getById(id);
            return new ServiceResponse<Nav>(ResponseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceResponse<Nav>(e);
        }
    }

	@PostMapping("/changeOrder/{id}/{direction}")
    public ServiceResponse<Nav> changeOrder(@PathVariable long id,@PathVariable int direction) {
        try {
            Nav result = service.changeOrder(id,direction);
            return new ServiceResponse<Nav>(ResponseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceResponse<Nav>(e);
        }
    }

    @PostMapping("/")
    public ServiceResponse<Nav> add(@RequestBody Nav data) {
        try {
            Nav result = service.add(data);
            return new ServiceResponse<Nav>(ResponseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceResponse<Nav>(e);
        }
    }
    @PutMapping("/")
    public ServiceResponse<Nav> update(@RequestBody Nav data) {
        try {
            Nav result = service.update(data);
            return new ServiceResponse<Nav>(ResponseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceResponse<Nav>(e);
        }
    }
    @DeleteMapping("/{id}")
    public ServiceResponse<Boolean> delete(@PathVariable long id) {
        try {
            boolean result = service.deleteById(id);
            return new ServiceResponse<Boolean>(ResponseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceResponse<Boolean>(e);
        }
    }
}
