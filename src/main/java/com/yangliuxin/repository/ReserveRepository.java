package com.yangliuxin.repository;

import com.yangliuxin.domain.Reserve;

public interface ReserveRepository {

    Reserve save(Reserve reserve);

    Reserve getById(Long id);
}
