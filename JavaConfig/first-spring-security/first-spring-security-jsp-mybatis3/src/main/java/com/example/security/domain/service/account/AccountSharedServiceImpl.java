/*
 * Copyright(c) 2013 NTT Corporation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package com.example.security.domain.service.account;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.terasoluna.gfw.common.exception.ResourceNotFoundException;
import org.terasoluna.gfw.common.message.ResultMessage;
import org.terasoluna.gfw.common.message.ResultMessages;
import com.example.security.domain.model.Account;
import com.example.security.domain.repository.account.AccountRepository;
import jakarta.inject.Inject;

@Service
public class AccountSharedServiceImpl implements AccountSharedService {
    @Inject
    AccountRepository accountRepository;

    @Transactional(readOnly = true)
    @Override
    public Account findOne(String username) {
        // (1)
        Account account = accountRepository.findById(username);
        // (2)
        if (account == null) {
            ResultMessages messages = ResultMessages.error();
            messages.add(
                    ResultMessage.fromText("The given account is not found! username=" + username));
            throw new ResourceNotFoundException(messages);
        }
        return account;
    }

}
