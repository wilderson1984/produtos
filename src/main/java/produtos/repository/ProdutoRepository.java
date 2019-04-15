package produtos.repository;

import org.springframework.data.repository.CrudRepository;

import produtos.entity.Produto;


public interface ProdutoRepository extends CrudRepository<Produto, Long>{

}
