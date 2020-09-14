package br.com.gporpino.apishoppingcart.application.converter;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.gporpino.apishoppingcart.application.converter.mocks.ProductMock;
import br.com.gporpino.apishoppingcart.application.vo.ProductVO;
import br.com.gporpino.apishoppingcart.domain.entities.Product;

public class ConverterTests {

  ProductMock inputObject;

  @Before
  public void setUp() {
    inputObject = new ProductMock();
  }

  @Test
  public void parseEntityToVOTest() {
    ProductVO output = Converter.parse(inputObject.mockEntity(), ProductVO.class);
    Assert.assertEquals(0L, output.getId());
    Assert.assertEquals("First Name Test0", output.getName());
    Assert.assertEquals(0, output.getPrice());
  }

  @Test
  public void parseEntityListToVOListTest() {
    List<ProductVO> outputList = Converter.parse(inputObject.mockEntityList(), ProductVO.class);
    ProductVO outputZero = outputList.get(0);

    Assert.assertEquals(0L, outputZero.getId());
    Assert.assertEquals("First Name Test0", outputZero.getName());
    Assert.assertEquals(0, outputZero.getPrice());

    ProductVO outputSeven = outputList.get(7);

    Assert.assertEquals(7L, outputSeven.getId());
    Assert.assertEquals("First Name Test7", outputSeven.getName());
    Assert.assertEquals(7, outputSeven.getPrice());

    ProductVO outputTwelve = outputList.get(12);

    Assert.assertEquals(12L, outputTwelve.getId());
    Assert.assertEquals("First Name Test12", outputTwelve.getName());
    Assert.assertEquals(12, outputTwelve.getPrice());

  }

  @Test
  public void parseVOToEntityTest() {
    Product output = Converter.parse(inputObject.mockVO(), Product.class);
    Assert.assertEquals(0L, output.getId());
    Assert.assertEquals("First Name Test0", output.getName());
    Assert.assertEquals(0, output.getPrice());

  }

  @Test
  public void parserVOListToEntityListTest() {
    List<Product> outputList = Converter.parse(inputObject.mockVOList(), Product.class);
    Product outputZero = outputList.get(0);

    Assert.assertEquals(0L, outputZero.getId());
    Assert.assertEquals("First Name Test0", outputZero.getName());
    Assert.assertEquals(0, outputZero.getPrice());

    Product outputSeven = outputList.get(7);

    Assert.assertEquals(7L, outputSeven.getId());
    Assert.assertEquals("First Name Test7", outputSeven.getName());
    Assert.assertEquals(7, outputSeven.getPrice());

    Product outputTwelve = outputList.get(12);

    Assert.assertEquals(12L, outputTwelve.getId());
    Assert.assertEquals("First Name Test12", outputTwelve.getName());
    Assert.assertEquals(12, outputTwelve.getPrice());

  }
}
