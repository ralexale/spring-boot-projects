package com.ralexale.springboot.di.app.springbootdi.services;

import com.ralexale.springboot.di.app.springbootdi.models.Product;
import com.ralexale.springboot.di.app.springbootdi.repositories.ProductRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Value;
// import org.springframework.beans.factory.annotation.Qualifier;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

  @Value("${config.tax}")
  private double tax;

  // @Autowired
  // private ProductRepositoryImpl repository;

  /*
   * Cambiamos la clase por la interfaz para tener un bajo acoplamiento solamente
   * se inyecta mediante la interfaz
   * Como lo hace Spring ?
   * Cuando encuentre el componente que implemente esta interfaz lo inyecta claro que si
   * tenemos dos componentes con la misma interfaz va a dar un error porque no va a saber
   * cual inyectar, para esto tenemos dos mecanismos
   */

  // @Autowired
  // @Qualifier("productRepositoryFoo")
  private ProductRepository repository;

  // @Autowired
  // public void setRepository(ProductRepository repository) {
  //   this.repository = repository;
  // }

  /*
   * Cuando lo inicializamos con un constructor no es necesario
   * colocar el @Autowired Spring se encarga de hacerlo automáticamente
   * con el @Qualifier le decimos que implementación vamos a usar por si
   * queremos usar otra que no sea la que tiene el @Primary
   */
  // public ProductServiceImpl(
  //   @Qualifier("productList") ProductRepository repository
  // ) {
  //   this.repository = repository;
  // }

  public ProductServiceImpl(ProductRepository repository) {
    this.repository = repository;
  }

  /*
   * Cada vez que ejecutamos este método estamos creando una nueva instancia de la lista
   * de productos entonces estamos modificando la lista original y también la nueva lista
   * para esto creamos una nueva instancia del producto y lo retornamos con los valores
   * modificados
   */
  @Override
  public List<Product> findAll() {
    /*
     * Estamos retornando todos los productos pero con el precio
     * modificado, primero entramos en la lista con el stream().map() y recorremos todos los productos
     * luego multiplicamos el precio por el impuesto que es 1.25 y retornamos el objeto.
     *
     * El map nos retorna un string pero necesitamos devolver una lista de productos
     * para eso usamos el método collect(Collectors.toList())
     */
    return repository
      .findAll()
      .stream()
      .map(p -> {
        double priceTax = p.getPrice() * tax;
        // creamos una nueva instancia del producto para no modificar el producto original
        // asi respetamos el principio de inmutabilidad

        // Product productTemp = new Product(p.getId(), p.getName(), priceTax);
        p.setPrice(priceTax);
        return p;
        // También podemos usar el clone() para no tener que crear una instancia
        // esto lo implementamos en el model del producto

        // Product productTemp = (Product) p.clone();
        // productTemp.setPrice(priceTax);

        // return productTemp;
      })
      .collect(Collectors.toList());
  }

  @Override
  public Product findById(Long id) {
    return repository.findById(id);
  }
}
