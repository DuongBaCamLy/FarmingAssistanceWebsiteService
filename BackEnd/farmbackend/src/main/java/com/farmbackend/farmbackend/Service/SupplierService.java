package com.farmbackend.farmbackend.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.farmbackend.farmbackend.Entities.Supplier;
import com.farmbackend.farmbackend.Repository.SupplierRepository;

@Service
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    public Supplier login(String username, String password) {
        Optional<Supplier> result = supplierRepository.findByAccountUsername(username);

        if (result.isPresent() && result.get().getAccount().getPassword().equals(password)) {
            return result.get();
        }

        return null;
    }

    public List<Supplier> getAllSuppliers() {
        List<Supplier> tmpSuppliers = this.supplierRepository.findAll();
        return tmpSuppliers.stream().map(rowData -> {
            Supplier tmp = new Supplier();
            tmp.setSupplierId(rowData.getSupplierId());
            tmp.setAccount(rowData.getAccount());
            return tmp;
        }).collect(Collectors.toList());
    }

}
