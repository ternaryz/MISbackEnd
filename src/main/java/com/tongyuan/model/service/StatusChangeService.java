package com.tongyuan.model.service;

import com.tongyuan.exception.SqlNumberException;

/**
 * Created by Y470 on 2017/7/9.
 */

public interface StatusChangeService {
    void agree(Long id) throws SqlNumberException;
    void disagree(Long id);
    void updateNextStatus(Long instanceId, String sequence) throws SqlNumberException;
}
