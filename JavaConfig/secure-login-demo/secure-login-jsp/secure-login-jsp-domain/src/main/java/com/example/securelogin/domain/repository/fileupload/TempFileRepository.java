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
package com.example.securelogin.domain.repository.fileupload;

import java.time.LocalDateTime;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import com.example.securelogin.domain.model.TempFile;

@Repository
public interface TempFileRepository {

    TempFile findById(@Param("id") String id);

    boolean create(TempFile tempFile);

    int deleteById(@Param("id") String id);

    int deleteByToDate(@Param("date") LocalDateTime date);

}
