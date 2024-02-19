package com.controleestorno.estornos.repositories;

import com.controleestorno.estornos.entities.EstornoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EstornoRepository extends JpaRepository<EstornoEntity, Long> {

    /**
     * Busca por ID
     *
     * @param id O ID passado para a busca
     * @return O ID buscado
     */
    Optional<EstornoEntity> findById(Long id);

    /**
     * Busca todos
     *
     * @return Todos os estornos encontrados
     */
    List<EstornoEntity> findAll();

    /**
     * Delete por ID
     *
     * @param id O ID a ser deletado
     */
    void deleteById(Long id);

    /**
     * Busca pela descrição dos produtos - independente do formato do case
     *
     * @param descricaoProduto A descrição do produto passada para a busca
     * @return Todo estorno em que a descrição tenha alguma relação com a buscada
     */
    @Query("SELECT e FROM EstornoEntity e WHERE lower(e.descricaoProduto) LIKE lower(concat('%', :descricaoProduto, '%'))")
    List<EstornoEntity> findByDescricaoProduto(String descricaoProduto);

    /**
     * Busca pelo código do produto
     *
     * @param codigoProduto O código do produto passado para a busca
     * @return Todo estorno em que o item tem o código buscado
     */
    List<EstornoEntity> findByCodigoProduto(int codigoProduto);
}
