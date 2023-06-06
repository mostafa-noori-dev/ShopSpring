package test.shop.app.services.people;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import test.shop.app.entities.people.User;
import test.shop.app.entities.site.Nav;
import test.shop.app.helper.exceptions.DataNotFoundException;
import test.shop.app.helper.utils.SecurityUtils;
import test.shop.app.repositories.people.UserRepository;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    @Autowired
    private SecurityUtils securityUtils;

    public User auth(String username, String password) {
        try {
            password = securityUtils.encryptSHA1(password);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return repository.findFirstByUsernameAndPassword(username, password);
    }

    public User getByUsername(String username) {
        return repository.findFirstByUsername(username);
    }

    public List<User> getAll(Integer pageSize, Integer pageNumber) {
        Pageable page = PageRequest.of(pageNumber, pageSize, Sort.by("id"));
        Page<User> all = repository.findAll(page);
        return all.toList();
    }

    public long getAllCount() {
        return repository.count();
    }
    public long getEnableCount() {
        return repository.countByEnableIsTrue();
    }

    public User getById(long id) {
        Optional<User> data = repository.findById(id);
        if (data.isPresent()) return data.get();
        return null;
    }

    public User add(User data) throws Exception {
        if (data.getUsername() == null || data.getUsername().equals(""))
            throw new Exception("please enter username");
        User oldUser = getByUsername(data.getUsername());
        if (oldUser != null)
            throw new Exception("Duplicated username.please change your username.");
        //TODO: ex: check password strength
        if (data.getPassword() == null || data.getPassword().equals(""))
            throw new Exception("please enter password");
        data.setPassword(securityUtils.encryptSHA1(data.getPassword()));
        return repository.save(data);
    }

    public User update(User data) throws DataNotFoundException, NoSuchAlgorithmException {
        User oldData = getById(data.getId());
        if (oldData == null)
            throw new DataNotFoundException("data with id: " + data.getId() + " not found");
        oldData.setEmail(data.getEmail());
        oldData.setEnable(data.isEnable());
        oldData.setFirstName(data.getFirstName());
        oldData.setLastName(data.getLastName());
        if (data.getPassword() != null && !data.getPassword().equals(""))
            oldData.setPassword(securityUtils.encryptSHA1(data.getPassword()));
        return repository.save(oldData);
    }

    public boolean deleteById(long id) throws DataNotFoundException {
        User oldData = getById(id);
        if (oldData == null)
            throw new DataNotFoundException("data with id: " + id + " not found");
        repository.deleteById(id);
        return true;
    }

    public User changePassword(long id, String oldPassword, String newPassword) throws Exception {
        try {
            oldPassword = securityUtils.encryptSHA1(oldPassword);
            newPassword = securityUtils.encryptSHA1(newPassword);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        User user = getById(id);
        if (user == null)
            throw new DataNotFoundException("User not found");
        if (!user.getPassword().equals(oldPassword))
            throw new Exception("invalid old password");
        user.setPassword(newPassword);
        return repository.save(user);
    }
}
