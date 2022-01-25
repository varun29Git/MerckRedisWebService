package com.merck.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.merck.demo.model.Item;
import com.merck.demo.repo.ItemRepository;

@RestController
public class ItemController {

	@Autowired
	ItemRepository itemRepo;
	
	@PostMapping(value="/addItem", consumes = {"application/json"}, produces = {"application/json"})
	@ResponseBody
	public ResponseEntity<Item> addItem(@RequestBody Item item, UriComponentsBuilder builder){
		itemRepo.addItem(item);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/addItem/{id}").buildAndExpand(item.getId()).toUri());
		 
        return new ResponseEntity<Item>(headers, HttpStatus.CREATED);
 
    }
	
	@RequestMapping("/getAllItems")
    @ResponseBody
    public ResponseEntity<Map<Integer, Item>> getAllItems() {
        Map<Integer, Item> items = itemRepo.getAllItems();
        return new ResponseEntity<Map<Integer, Item>>(items, HttpStatus.OK);
    }
	
	@PutMapping("/updateItem")
    @ResponseBody
    public ResponseEntity<Item> updateItem(@RequestBody Item item) {
        itemRepo.updateItem(item);
        return new ResponseEntity<Item>(item, HttpStatus.OK);
 
    }
 
    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteItem(@PathVariable int id) {
        itemRepo.deleteItem(id);
        return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
 
    }
	

}
