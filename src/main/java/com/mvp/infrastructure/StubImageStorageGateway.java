package com.mvp.infrastructure;

import com.mvp.core.ports.ImageStorageGateway;
import org.springframework.stereotype.Component;

@Component
public class StubImageStorageGateway implements ImageStorageGateway {
    @Override
    public String store(byte[] imageBytes) {
        return "stub-url";
    }
}
