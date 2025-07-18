package com.projetolpoo.business;

import java.util.List;

import com.projetolpoo.entities.Account;
import com.projetolpoo.entities.Meta;
import com.projetolpoo.exception.BusinessException;

public class MetaController {

    private Account account; // A conta financeira cujas metas este controller gerencia

    public MetaController(Account account) {
        if (account == null) {
            throw new IllegalArgumentException("Account não pode ser nula para o MetaController.");
        }
        this.account = account;
    }

    public void addMeta(Meta meta) {
        if (meta == null) {
            throw new BusinessException("A meta não pode ser nula.");
        }
        if (this.account.getMetas().size() >= 5) { // Regra de negócio: limite de 5 metas
            throw new BusinessException("Você atingiu o limite máximo de 5 metas.");
        }
        this.account.adicionarMeta(meta); // Adiciona a meta na lista da Account
        // *** PONTO DE PERSISTÊNCIA ***
        // Chamar seu MetaRepository para salvar esta meta no DB.
        // Ex: metaRepository.save(meta, this.account.getId());
    }

    public void removeMeta(Meta meta) {
        if (meta == null) {
            throw new BusinessException("A meta não pode ser nula para remoção.");
        }
        if (!this.account.getMetas().remove(meta)) { // Remove diretamente da lista de metas da Account
            throw new BusinessException("Meta não encontrada na conta para remoção.");
        }
        // *** PONTO DE PERSISTÊNCIA ***
        // Chamar seu MetaRepository para remover esta meta do DB.
        // Ex: metaRepository.delete(meta.getId());
    }

    public List<Meta> getAllMetas() {
        return this.account.getMetas();
    }
}