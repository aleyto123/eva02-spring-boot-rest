package com.tecsup.service;

import com.tecsup.model.Categoria;
import com.tecsup.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoriaService {
    @Autowired private CategoriaRepository repository;
    public List<Categoria> listar() { return repository.findAll(); }
    public Categoria guardar(Categoria c) { return repository.save(c); }
    public void eliminar(Long id) { repository.deleteById(id); }
}