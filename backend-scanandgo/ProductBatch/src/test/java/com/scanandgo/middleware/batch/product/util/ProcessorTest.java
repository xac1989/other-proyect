package com.scanandgo.middleware.batch.product.util;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.cencosud.middleware.product.model.Product;
import com.scanandgo.middleware.batch.product.service.VtexService;
import com.scanandgo.middleware.batch.product.service.impl.ProductMongoServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class ProcessorTest {

	@InjectMocks
	private Processor processor;

	@Mock
	private ProductMongoServiceImpl serviceImpl;

	@Mock
	private VtexService service;
	@Mock
	private Product product;

	private List<String> lstStrings;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		lstStrings = new ArrayList<>();

		lstStrings.add("  000000249649820036210100KG00100000CHORIZO BLANCO                000 00006760");
		lstStrings.add(" g0000002496498200000000360802002000001652630000000000000165263000000  0000000");
		lstStrings.add(" h000000249649820000000000000000000000                            0000000000  ");
		lstStrings.add(" i0000002496498200000000000000000000000000000000000000109000000000000000000000");
		lstStrings.add(" y00000024964982CHORIZO BLANO JUMBO ARTESANAL VACIO KG                        ");
		lstStrings.add(" z00000024964982000000000000000000KG               0          0201            ");
		lstStrings.add("  02496498000000****000000  00000000@@@@  00000024964982              00000000");

	}

	@Test
	public void process_TestCorrect() {
		when(serviceImpl.findByEAN(anyString())).thenReturn(null);
		when(serviceImpl.saveOrUpdate(any(Product.class))).thenReturn(product);

		try {
			processor.process(lstStrings);

			verify(serviceImpl, times(1)).findByEAN(anyString());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
