package com.mvp.adapters.api;

import com.mvp.adapters.api.dto.*;
import com.mvp.core.domain.Cart;
import com.mvp.core.domain.Order;
import com.mvp.core.usecase.AddItemToCart;
import com.mvp.core.usecase.CheckoutCart;
import com.mvp.core.usecase.CreateCart;
import com.mvp.core.usecase.GetCart;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/carts")
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
    public CartResponse createCart() {
        Cart cart = createCart.execute();
        return toResponse(cart);
    }

    @GetMapping("/{id}")
    public CartResponse getCart(@PathVariable UUID id) {
        Cart cart = getCart.execute(id);
        return toResponse(cart);
    }

    @PostMapping("/{id}/items")
    public CartResponse addItem(@PathVariable UUID id, @RequestBody AddItemRequest request) {
        Cart cart = addItemToCart.execute(id, request.productId(), request.quantity());
        return toResponse(cart);
    }

    @PostMapping("/{id}/checkout")
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
