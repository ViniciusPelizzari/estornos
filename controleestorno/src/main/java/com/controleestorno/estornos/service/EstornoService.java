package com.controleestorno.estornos.service;

import com.controleestorno.estornos.entities.EstornoEntity;
import com.controleestorno.estornos.repositories.EstornoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Classe responsável pelas operações e serviços relacionados aos estornos
 */
@Component
public class EstornoService {

    /**
     * Injetando a dependência do EstornoRepository
     */
    @Autowired
    private EstornoRepository estornoRepository;

    /**
     * Cria um novo estorno
     *
     * @param estornoEntity O estorno a ser criado
     */
    @Transactional
    public void criarEstorno(EstornoEntity estornoEntity) {
        estornoRepository.save(estornoEntity);
    }

    /**
     * Exclui um estorno através do JSON
     *
     * @param estornoEntity O estorno a ser excluído
     */
    @Transactional
    public void excluirEstorno(EstornoEntity estornoEntity) {
        estornoRepository.delete(estornoEntity);
    }

    /**
     * Exclui um estorno por ID na URL
     *
     * @param id O ID a ser excluído
     * @return
     */
    @Transactional
    public void excluirEstornoId(Long id) {
        estornoRepository.deleteById(id);
    }

    /**
     * Altera um estorno
     *
     * @param estornoEntity O estorno a ser alterado
     */
    @Transactional
    public EstornoEntity atualizarEstorno(Long codigo, EstornoEntity estornoEntity) {
        EstornoEntity estornoAtual = estornoRepository.findById(codigo).orElse(null);
        if (estornoAtual == null) {
            return null;
        }
        estornoAtual.setDescricaoProduto(estornoEntity.getDescricaoProduto());
        estornoAtual.setCodigoProduto(estornoEntity.getCodigoProduto());
        estornoAtual.setSaldoAnterior(estornoEntity.getSaldoAnterior());
        estornoAtual.setSaldoAtualizado(estornoEntity.getSaldoAtualizado());
        estornoAtual.setNomeResponsavel(estornoEntity.getNomeResponsavel());
        estornoAtual.setDateAtualizacaoEstorno(estornoEntity.getDateAtualizacaoEstorno());
        return estornoRepository.save(estornoAtual);
    }

    /**
     * Busca todos os estornos
     *
     * @return Todos os estornos encontrados
     */
    public List<EstornoEntity> buscarTodosEstornos() {
        return estornoRepository.findAll();
    }

    /**
     * Busca estornos pelo ID
     *
     * @param id O ID a ser buscado
     * @return O ID buscado
     */
    public Optional<EstornoEntity> buscarEstornoById(Long id) {
        return estornoRepository.findById(id);
    }

    /**
     * Busca pela descrição dos produtos - independente do formato do case
     *
     * @param descricaoProduto A descrição do produto passada para a busca
     * @return Todo estorno em que a descrição tenha alguma relação com a buscada
     */
    public List<EstornoEntity> buscarEstornosDescricaoProduto(String descricaoProduto){
        return estornoRepository.findByDescricaoProduto(descricaoProduto);
    }

    /**
     * Busca pelo código do produto
     *
     * @param codigoProduto O código do produto passado para a busca
     * @return Todo estorno em que o item tem o código buscado
     */
    public List<EstornoEntity> buscarEstornoCodigoProduto(int codigoProduto){
        return estornoRepository.findByCodigoProduto(codigoProduto);
    }
}
