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
package com.example.securelogin.app.common.validation.rule;

import org.passay.HistoryRule;
import org.passay.PasswordData;
import org.springframework.security.crypto.password.PasswordEncoder;

public class EncodedPasswordHistoryRule extends HistoryRule {

    PasswordEncoder passwordEncoder;

    public EncodedPasswordHistoryRule(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected boolean matches(final String rawPassword, final PasswordData.Reference reference) {
        return passwordEncoder.matches(rawPassword, reference.getPassword());
    }
}
