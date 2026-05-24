package com.tecsup.controller;
import com.tecsup.model.Categoria;
import com.tecsup.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
    @Autowired private CategoriaService service;
    @GetMapping public List<Categoria> listar() { return service.listar(); }
    @PostMapping public Categoria crear(@Valid @RequestBody Categoria c) { return service.guardar(c); }
    @PutMapping("/{id}") public Categoria editar(@PathVariable Long id, @Valid @RequestBody Categoria c) { c.setId(id); return service.guardar(c); }
    @DeleteMapping("/{id}") public void borrar(@PathVariable Long id) { service.eliminar(id); }
}