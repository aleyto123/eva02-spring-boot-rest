package com.tecsup.service;

import com.tecsup.model.DetallePedido;
import com.tecsup.model.Pedido;
import com.tecsup.model.Producto;
import com.tecsup.repository.PedidoRepository;
import com.tecsup.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProductoRepository productoRepository;

    public List<Pedido> listar() { return pedidoRepository.findAll(); }
    public Pedido buscarPorId(Long id) { return pedidoRepository.findById(id).orElseThrow(() -> new RuntimeException("Pedido no encontrado")); }

    @Transactional
    public Pedido registrarPedido(Pedido pedido) {
        if (pedido.getDetalles() == null || pedido.getDetalles().isEmpty()) {
            throw new RuntimeException("Un pedido debe contener al menos un producto.");
        }

        double totalPedido = 0.0;

        for (DetallePedido detalle : pedido.getDetalles()) {
            Producto producto = productoRepository.findById(detalle.getProducto().getId())
                    .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

            if (producto.getStock() < detalle.getCantidad()) {
                throw new RuntimeException("Stock insuficiente para el producto: " + producto.getNombre());
            }

            // Descontar Stock [text: 95]
            producto.setStock(producto.getStock() - detalle.getCantidad());
            productoRepository.save(producto);

            // Calcular Subtotal [text: 92]
            double subtotal = detalle.getCantidad() * producto.getPrecio();
            detalle.setSubtotal(subtotal);
            detalle.setPedido(pedido);
            detalle.setProducto(producto);

            totalPedido += subtotal;
        }

        pedido.setTotal(totalPedido); // Total automático [text: 91]
        return pedidoRepository.save(pedido);
    }

    public void eliminar(Long id) { pedidoRepository.deleteById(id); }
}