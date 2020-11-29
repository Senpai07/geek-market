package com.geekbrains.geek.market.endpoints;

import com.geekbrains.geek.market.dto.products.GetProductsRequest;
import com.geekbrains.geek.market.dto.products.GetProductsResponse;
import com.geekbrains.geek.market.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
@RequiredArgsConstructor
public class ProductEndpoint {
    private static final String NAMESPACE_URI = "http://www.geekbrains.com/spring/ws/products";
    private final ProductService productService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getProductsRequest")
    @ResponsePayload
    public GetProductsResponse getAllProducts(@RequestPayload GetProductsRequest request) {
        GetProductsResponse response = new GetProductsResponse();
        productService.getAllProducts().forEach(response.getProducts()::add);
        return response;
    }
}
