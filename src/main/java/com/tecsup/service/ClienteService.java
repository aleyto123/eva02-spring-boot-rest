package com.tecsup.service;

import com.tecsup.model.Cliente;
import com.tecsup.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClienteService {
    @Autowired private ClienteRepository repository;
    public List<Cliente> listar() { return repository.findAll(); }
    public Cliente buscarPorId(Long id) { return repository.findById(id).orElseThrow(() -> new RuntimeException("Cliente no encontrado")); }
    public Cliente guardar(Cliente c) { return repository.save(c); }
    public void eliminar(Long id) { repository.deleteById(id); }
}