package com.example.ProTrack.Repository;

import com.example.ProTrack.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // Recherche par statut (ex: READY, DISPATCHED)
    List<Product> findByStatusIgnoreCase(String status);

    // Recherche par code-barres
    Product findByBarcode(String barcode);

    // Recherche par QR code
    Product findByQrCode(String qrCode);
}
