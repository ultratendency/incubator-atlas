/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.atlas.repository.store.graph;

import org.apache.atlas.exception.AtlasBaseException;
import org.apache.atlas.model.instance.AtlasEntity;

public interface EntityGraphDiscovery {
    void init() throws AtlasBaseException;

    /*
     * Return list of resolved and unresolved references.
     * Resolved references already exist in the ATLAS repository and have an assigned unique GUID
     * Unresolved attribute references result in an error if they are not composite (managed by a parent entity)
     */
    EntityGraphDiscoveryContext discoverEntities() throws AtlasBaseException;

    void validateAndNormalize(AtlasEntity entity) throws AtlasBaseException;

    void validateAndNormalizeForUpdate(AtlasEntity entity) throws AtlasBaseException;

    void cleanUp() throws AtlasBaseException;
}
