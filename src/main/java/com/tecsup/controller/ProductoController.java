package com.tecsup.controller;
import com.tecsup.model.Producto;
import com.tecsup.service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {
    @Autowired private ProductoService service;
    @GetMapping public List<Producto> listar() { return service.listar(); }
    @GetMapping("/{id}") public Producto porId(@PathVariable Long id) { return service.buscarPorId(id); }
    @PostMapping public Producto crear(@Valid @RequestBody Producto p) { return service.guardar(p); }
    @PutMapping("/{id}") public Producto editar(@PathVariable Long id, @Valid @RequestBody Producto p) { p.setId(id); return service.guardar(p); }
    @DeleteMapping("/{id}") public void borrar(@PathVariable Long id) { service.eliminar(id); }
}