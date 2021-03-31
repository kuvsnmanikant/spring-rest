package com.teja.springrestass;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class customerservice {
@Autowired
Crudrepo crud;

public void addcus(customer cus) {
	crud.save(cus);
}
public void updatecus(customer cus , int ID) {
	crud.save(cus);
}
public void deletecus(String ID) {
	crud.deleteById(ID);
}
public customer findbyid(int ID) {
	return crud.findById("ID").get();
}
public List<customer> findall() {
	List<customer> cus= new ArrayList<customer>();
	crud.findAll().forEach(t->cus.add(t));
	return cus;
}
}
