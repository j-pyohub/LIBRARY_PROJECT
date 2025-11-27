package com.erp.service;

import com.erp.dto.StoreMenuDTO;
import com.erp.repository.StoreMenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreMenuService {
    private final StoreMenuRepository storeMenuRepository;
}
