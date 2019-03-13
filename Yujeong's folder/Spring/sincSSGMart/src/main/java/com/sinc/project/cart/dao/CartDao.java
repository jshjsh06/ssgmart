package com.sinc.project.cart.dao;

import java.util.List;
import java.util.Map;

public interface CartDao {

	public List<Object> cartListsRow();
	public void cartDelete(int cart_id);

}
