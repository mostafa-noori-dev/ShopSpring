package test.shop.app.controllers.api.site;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import test.shop.app.entities.site.Content;
import test.shop.app.entities.site.Nav;
import test.shop.app.helper.ui.ResponseStatus;
import test.shop.app.helper.ui.ServiceResponse;
import test.shop.app.services.site.ContentService;

import java.util.List;

@RestController
@RequestMapping("/api/content")
public class ContentController {
    @Autowired
    private ContentService service;

    @GetMapping("/getAll")
    public ServiceResponse<Content> getAll(
            @RequestParam Integer pageSize,
            @RequestParam Integer pageNumber
    ) {
        try {
            List<Content> result = service.getAll(pageSize,pageNumber);
            long totalCount=service.getAllCount();
            return new ServiceResponse<Content>(ResponseStatus.SUCCESS, result,totalCount);
        } catch (Exception e) {
            return new ServiceResponse<Content>(e);
        }
    }

    @GetMapping("/getAllData")
    public ServiceResponse<Content> getAllData() {
        try {
            List<Content> result = service.getAllData();
            long totalCount=service.getAllCount();
            return new ServiceResponse<Content>(ResponseStatus.SUCCESS, result,totalCount);
        } catch (Exception e) {
            return new ServiceResponse<Content>(e);
        }
    }

    @GetMapping("")
    public ServiceResponse<Content> find(@RequestParam String key) {
        try {
            Content result = service.findByKey(key);
            return new ServiceResponse<Content>(ResponseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceResponse<Content>(e);
        }
    }

    @GetMapping("/{id}")
    public ServiceResponse<Content> getById(@PathVariable long id) {
        try {
            Content result = service.getById(id);
            return new ServiceResponse<Content>(ResponseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceResponse<Content>(e);
        }
    }

    @PostMapping("/")
    public ServiceResponse<Content> add(@RequestBody Content data) {
        try {
            Content result = service.add(data);
            return new ServiceResponse<Content>(ResponseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceResponse<Content>(e);
        }
    }
    @PutMapping("/")
    public ServiceResponse<Content> update(@RequestBody Content data) {
        try {
            Content result = service.update(data);
            return new ServiceResponse<Content>(ResponseStatus.SUCCESS, result);
        } catch (Exception e) {
            return new ServiceResponse<Content>(e);
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
