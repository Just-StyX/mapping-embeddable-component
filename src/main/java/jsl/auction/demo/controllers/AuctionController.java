package jsl.auction.demo.controllers;

import jsl.auction.demo.entities.Address;
import jsl.auction.demo.entities.Item;
import jsl.auction.demo.entities.Response;
import jsl.auction.demo.entities.User;
import jsl.auction.demo.repo.RepositoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("auction")
public class AuctionController {
    private final RepositoryService repositoryService;

    public AuctionController(RepositoryService repositoryService) {
        this.repositoryService = repositoryService;
    }

    @PostMapping("/user")
    public ResponseEntity<Response> postUser(@RequestBody User user) {
        repositoryService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(new Response("user saved"));
    }

    @PutMapping("/user/address/{id}")
    public ResponseEntity<Response> updateUserAddress(
            @PathVariable Integer id,
            @RequestBody Address address,
            @RequestParam(required = false) String change
    ) {
        var user = repositoryService.findUser(id);
        if (change == null){
            user.setHomeAddress(address);
        } else if (change.equals("billing")) {
            user.setBillingAddress(address);
        } else if (change.equals("shipping")) {
            user.setShippingAddress(address);
        }
        var changer = change == null ? "home" : change;
        repositoryService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(new Response("user " + changer + " address updated"));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUser(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(repositoryService.findUser(id));
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(repositoryService.findAllUsers());
    }

    @PostMapping("/item")
    public ResponseEntity<Response> saveItem(@RequestBody Item item) {
        repositoryService.save(item);
        return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Item " + item.getName() + " saved"));
    }

    @GetMapping("/item/{id}")
    public ResponseEntity<Item> getItem(@PathVariable Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(repositoryService.findItem(id));
    }

    @GetMapping("/items")
    public ResponseEntity<List<Item>> getAllItem() {
        return ResponseEntity.status(HttpStatus.OK).body(repositoryService.findAllItem());
    }
}
