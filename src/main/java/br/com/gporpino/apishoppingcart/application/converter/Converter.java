package br.com.gporpino.apishoppingcart.application.converter;

import java.util.ArrayList;
import java.util.List;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

public class Converter {

  private static Mapper mapper = DozerBeanMapperBuilder.buildDefault();

  public static <O, D> D parse(O origin, Class<D> destination) {
    return mapper.map(origin, destination);
  }

  public static <O, D> List<D> parse(List<O> origin, Class<D> destination) {

    List<D> list = new ArrayList<D>();

    origin.forEach(o -> list.add(parse(o, destination)));

    return list;
  }

}
