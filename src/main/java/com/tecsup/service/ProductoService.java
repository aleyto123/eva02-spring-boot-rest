package com.tecsup.service;

import com.tecsup.model.Producto;
import com.tecsup.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductoService {
    @Autowired private ProductoRepository repository;
    public List<Producto> listar() { return repository.findAll(); }
    public Producto buscarPorId(Long id) { return repository.findById(id).orElseThrow(() -> new RuntimeException("Producto no encontrado")); }
    public Producto guardar(Producto p) { return repository.save(p); }
    public void eliminar(Long id) { repository.deleteById(id); }
}