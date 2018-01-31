package com.cencosud.middleware.category.model;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

import com.cencosud.middleware.category.client.VtexCategory;

public class CategoryTest {

	private static final String VT_ENV = "qa";
	private static final String PATH = "path/";

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void CreateCategoryFromVetexTest() {
		Category category = new Category(createVtexCategory(), PATH, VT_ENV, "path/images/png/qa/"); 
		assertEquals("The icon path mustbe equal",PATH+"images/png/"+VT_ENV+"/icon-"+category.getId()+".png",category.getIcon());
		assertEquals("The icon path mustbe equal",PATH+"images/svg/"+VT_ENV+"/"+category.getId()+".svg",category.getIcon_svg());
	}

	private VtexCategory createVtexCategory(){
		VtexCategory vc = new VtexCategory(10001, "Deportes", false, "http://cencosud/10001", null, 100002) ;
		return vc;
	}
}
