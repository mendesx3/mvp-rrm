package com.mvp.core.ports;

public interface ImageStorageGateway {
    String store(byte[] imageBytes);
}
