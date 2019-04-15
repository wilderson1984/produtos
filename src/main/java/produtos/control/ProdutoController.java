package produtos.control;

import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import produtos.entity.Produto;
import produtos.repository.ProdutoRepository;

@RestController
public class ProdutoController {
	private ProdutoRepository produtoRepository;

	public ProdutoController(ProdutoRepository produtoRepository) {
		this.produtoRepository = produtoRepository;
	}

	@PostMapping("/produto/")
	public Produto createProduto(@RequestBody Produto request) {
		return produtoRepository.save(request);
	}

	@GetMapping("/produto/{id}/")
	public ResponseEntity<Produto> getProduto(@PathVariable("id") Long id) {
		Optional<Produto> maybeProduto = produtoRepository.findById(id);
		if (!maybeProduto.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(maybeProduto.get(), HttpStatus.OK);
	}
	
	@GetMapping("/produto/list/")
    public ResponseEntity<Iterable<Produto>> listAllUsers() {
        Iterable<Produto> users = produtoRepository.findAll();
        return new ResponseEntity<Iterable<Produto>>(users, HttpStatus.OK);
    }
	
	@DeleteMapping("/produto/{id}")
	public ResponseEntity<Produto> deleteProduto(@PathVariable("id") Long id){
		produtoRepository.deleteById(id);
		return new ResponseEntity<Produto>(HttpStatus.OK);
	}
}
