package com.controleestorno.estornos.controllers;

import com.controleestorno.estornos.entities.EstornoEntity;
import com.controleestorno.estornos.service.EstornoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controlador responsável por lidar com as requisições relacionadas aos estornos
 */
@RestController
@RequestMapping("/estornos")
public class EstornoController {

    /**
     * Injetando a dependência do EstornoService
     */
    @Autowired
    private EstornoService estornoService;

    /**
     *
     * @param estornoEntity Passa os dados da pessoa a serem cadastrados.
     */
    @PostMapping
    public String criarEstorno(@RequestBody EstornoEntity estornoEntity) {
        estornoService.criarEstorno(estornoEntity);
        return "Criado com sucesso";
    }

    /**
     * Cria estorno através de uma lista - mais que um estorno por JSON
     *
     * @param estornoEntities Todos os estornos a serem criados
     * @return Mensagem de sucesso
     */
    @PostMapping("/lista")
    public String criarListaEstorno(@RequestBody List<EstornoEntity> estornoEntities) {
        for (EstornoEntity estornoEntity : estornoEntities) {
            estornoService.criarEstorno(estornoEntity);
        }
        return "Criado com sucesso";
    }

    /**
     * Excluí um estorno pelo JSON
     *
     * @param id O ID do estorno a ser excluído
     * @return Mensagem de sucesso
     */
    @DeleteMapping
    public String excluirEstorno(@RequestBody EstornoEntity id){
        estornoService.excluirEstorno(id);
        return "Deletado com sucesso";
    }

    /**
     * Exclui um estorno pelo ID na URL
     *
     * @param id O ID do estorno a ser excluído
     * @return Mensagem de sucesso
     */
    @DeleteMapping("/{id}")
    public String excluirEstornoId(@PathVariable Long id){
        estornoService.excluirEstornoId(id);
        return "Deletado com sucesso";
    }

    /**
     * Altera os dados de um estorno
     *
     * @param id O ID do estorno que será alterado
     * @param estornoEntity Os novos dados do estorno
     * @return Mensagem
     */
    @PutMapping("/{id}")
    public ResponseEntity<String> alterarEstorno(@PathVariable Long id, @RequestBody EstornoEntity estornoEntity){
        EstornoEntity estornoAtualizado = estornoService.atualizarEstorno(id, estornoEntity);
        if(estornoAtualizado != null){
            return new ResponseEntity<>("Atualização bem-sucedida", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Erro na atualização", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Busca todos os estornos
     *
     * @return Todos os estornos encontrados ou mensagem de erro
     */
    @GetMapping
    public ResponseEntity<?> buscarTodosEstornos(){
        List<EstornoEntity> estorno = estornoService.buscarTodosEstornos();
        if (estorno == null || estorno.isEmpty()) {
            return new ResponseEntity<>("Sem retorno. Nenhum item encontrado!", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(estorno, HttpStatus.OK);
        }
    }

    /**
     * Busca estorno por ID
     *
     * @param id O ID a ser buscado
     * @return Todos os estornos encontrados ou mensagem de erro
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarEstornosById(@PathVariable Long id){
        Optional<EstornoEntity> estorno = estornoService.buscarEstornoById(id);
        if (estorno.isEmpty()) {
            return new ResponseEntity<>("Sem retorno. Nenhum item encontrado!", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(estorno, HttpStatus.OK);
        }
    }

    /**
     * Busca pela descrição dos produtos - independente do formato do case
     *
     * @param descricao A descrição do produto passada para a busca
     * @return Todos os estornos encontrados ou mensagem de erro
     */
    @GetMapping("descricao/{descricao}")
    public ResponseEntity<?> buscarEstornosDescricaoProduto(@PathVariable String descricao){
        List<EstornoEntity> estorno = estornoService.buscarEstornosDescricaoProduto(descricao);

        if (estorno == null || estorno.isEmpty()) {
            return new ResponseEntity<>("Sem retorno. Nenhum item encontrado!", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(estorno, HttpStatus.OK);
        }
    }

    /**
     * Busca pelo código do produto
     *
     * @param codigo O código do produto passado para a busca
     * @return Todos os estornos encontrados ou mensagem de erro
     */
    @GetMapping("codigo-produto/{codigo}")
    public ResponseEntity<?> buscarEstornosCodigoProduto(@PathVariable int codigo){
        List<EstornoEntity> estorno = estornoService.buscarEstornoCodigoProduto(codigo);

        if (estorno == null || estorno.isEmpty()) {
            return new ResponseEntity<>("Sem retorno. Nenhum item encontrado!", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(estorno, HttpStatus.OK);
        }
    }
}
