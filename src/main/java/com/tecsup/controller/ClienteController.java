package com.tecsup.controller;
import com.tecsup.model.Cliente;
import com.tecsup.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired private ClienteService service;
    @GetMapping public List<Cliente> listar() { return service.listar(); }
    @GetMapping("/{id}") public Cliente porId(@PathVariable Long id) { return service.buscarPorId(id); }
    @PostMapping public Cliente crear(@Valid @RequestBody Cliente c) { return service.guardar(c); }
    @PutMapping("/{id}") public Cliente editar(@PathVariable Long id, @Valid @RequestBody Cliente c) { c.setId(id); return service.guardar(c); }
    @DeleteMapping("/{id}") public void borrar(@PathVariable Long id) { service.eliminar(id); }
}