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
package com.example.securelogin.app.common.validation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import com.example.securelogin.app.common.validation.DomainRestrictedEmail.List;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.Email;

@Documented
@Constraint(validatedBy = {DomainRestrictedEmailValidator.class})
@Target(FIELD)
@Retention(RUNTIME)
@Repeatable(List.class)
@Email
public @interface DomainRestrictedEmail {
    String message() default "{com.example.securelogin.app.common.validation.DomainRestrictedEmail.message}";

    Class<?>[] groups() default {};

    String[] allowedDomains() default {};

    boolean allowSubDomain() default false;

    @Target(FIELD)
    @Retention(RUNTIME)
    @Documented
    public @interface List {
        DomainRestrictedEmail[] value();
    }

    Class<? extends Payload>[] payload() default {};
}
