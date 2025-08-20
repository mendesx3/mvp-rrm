package com.mvp.adapters.api;

import com.mvp.adapters.api.dto.*;
import com.mvp.core.domain.Cart;
import com.mvp.core.domain.Order;
import com.mvp.core.usecase.AddItemToCart;
import com.mvp.core.usecase.CheckoutCart;
import com.mvp.core.usecase.CreateCart;
import com.mvp.core.usecase.GetCart;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/carts")
@Tag(name = "Carrinhos", description = "Operações com carrinho de compras")
public class CartController {
    private final CreateCart createCart;
    private final GetCart getCart;
    private final AddItemToCart addItemToCart;
    private final CheckoutCart checkoutCart;

    public CartController(CreateCart createCart, GetCart getCart, AddItemToCart addItemToCart, CheckoutCart checkoutCart) {
        this.createCart = createCart;
        this.getCart = getCart;
        this.addItemToCart = addItemToCart;
        this.checkoutCart = checkoutCart;
    }

    @PostMapping
    @Operation(summary = "Cria um novo carrinho", description = "Gera um novo identificador de carrinho")
    @ApiResponse(responseCode = "200", description = "Carrinho criado",
            content = @Content(schema = @Schema(implementation = CartResponse.class)))
    public CartResponse createCart() {
        Cart cart = createCart.execute();
        return toResponse(cart);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Busca um carrinho", description = "Recupera o estado atual do carrinho")
    @ApiResponse(responseCode = "200", description = "Carrinho encontrado",
            content = @Content(schema = @Schema(implementation = CartResponse.class)))
    public CartResponse getCart(@PathVariable UUID id) {
        Cart cart = getCart.execute(id);
        return toResponse(cart);
    }

    @PostMapping("/{id}/items")
    @Operation(summary = "Adiciona item", description = "Adiciona um produto ao carrinho")
    @ApiResponse(responseCode = "200", description = "Carrinho atualizado",
            content = @Content(schema = @Schema(implementation = CartResponse.class)))
    public CartResponse addItem(@PathVariable UUID id, @RequestBody AddItemRequest request) {
        Cart cart = addItemToCart.execute(id, request.productId(), request.quantity());
        return toResponse(cart);
    }

    @PostMapping("/{id}/checkout")
    @Operation(summary = "Finaliza o carrinho", description = "Realiza o checkout do carrinho e gera um pedido")
    @ApiResponse(responseCode = "200", description = "Pedido criado",
            content = @Content(schema = @Schema(implementation = OrderResponse.class)))
    public OrderResponse checkout(@PathVariable UUID id) {
        Order order = checkoutCart.execute(id);
        return new OrderResponse(order.getId(), order.getTotal().amount());
    }

    private CartResponse toResponse(Cart cart) {
        List<CartItemResponse> items = cart.getItems().stream()
                .map(i -> new CartItemResponse(i.productId(), i.quantity(), i.price().amount()))
                .collect(Collectors.toList());
        return new CartResponse(cart.getId(), items);
    }
}
