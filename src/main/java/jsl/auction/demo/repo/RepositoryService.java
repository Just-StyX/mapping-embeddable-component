package jsl.auction.demo.repo;

import jsl.auction.demo.entities.Item;
import jsl.auction.demo.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepositoryService {
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

    public RepositoryService(ItemRepository itemRepository, UserRepository userRepository) {
        this.itemRepository = itemRepository;
        this.userRepository = userRepository;
    }

    public void save(User user) { userRepository.save(user); }
    public void save(Item item) { itemRepository.save(item); }

    public User findUser(int id) { return userRepository.findById(id).orElse(null);}
    public List<User> findAllUsers() {return userRepository.findAll(); }
    public Item findItem(int id) { return  itemRepository.findById(id).orElse(null);}
    public List<Item> findAllItem() { return itemRepository.findAll(); }
}
