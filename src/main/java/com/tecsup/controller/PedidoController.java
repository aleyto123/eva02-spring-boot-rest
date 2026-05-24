// PedidoController.java
package com.tecsup.controller;
import com.tecsup.model.Pedido;
import com.tecsup.service.PedidoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    @Autowired private PedidoService service;
    @GetMapping public List<Pedido> listar() { return service.listar(); }
    @GetMapping("/{id}") public Pedido porId(@PathVariable Long id) { return service.buscarPorId(id); }
    @PostMapping public Pedido crear(@Valid @RequestBody Pedido p) { return service.registrarPedido(p); }
    @DeleteMapping("/{id}") public void borrar(@PathVariable Long id) { service.eliminar(id); }
}