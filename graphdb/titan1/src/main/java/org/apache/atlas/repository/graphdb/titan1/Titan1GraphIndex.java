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

package org.apache.atlas.repository.graphdb.titan1;

import java.util.HashSet;
import java.util.Set;

import org.apache.atlas.repository.graphdb.AtlasGraphIndex;
import org.apache.atlas.repository.graphdb.AtlasPropertyKey;
import org.apache.tinkerpop.gremlin.structure.Edge;
import org.apache.tinkerpop.gremlin.structure.Vertex;

import com.thinkaurelius.titan.core.PropertyKey;
import com.thinkaurelius.titan.core.schema.TitanGraphIndex;

/**
 * Represents an Index in Titan 1.
 */
public class Titan1GraphIndex implements AtlasGraphIndex {

    private TitanGraphIndex wrapped;

    public Titan1GraphIndex(TitanGraphIndex toWrap) {
        this.wrapped = toWrap;
    }


    @Override
    public boolean isEdgeIndex() {
        return Edge.class.isAssignableFrom(wrapped.getIndexedElement());
    }

    @Override
    public boolean isVertexIndex() {
        return Vertex.class.isAssignableFrom(wrapped.getIndexedElement());
    }

    @Override
    public boolean isUnique() {
        return wrapped.isUnique();
    }


    @Override
    public Set<AtlasPropertyKey> getFieldKeys() {
        PropertyKey[] keys = wrapped.getFieldKeys();
        Set<AtlasPropertyKey> result = new HashSet<>();
        for(PropertyKey key  : keys) {
            result.add(GraphDbObjectFactory.createPropertyKey(key));
        }
        return result;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 37*result + wrapped.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Titan1GraphIndex)) {
            return false;
        }
        Titan1GraphIndex otherKey = (Titan1GraphIndex)other;
        return otherKey.wrapped.equals(wrapped);

    }


    @Override
    public boolean isMixedIndex() {
        return wrapped.isMixedIndex();
    }


    @Override
    public boolean isCompositeIndex() {
        return wrapped.isCompositeIndex();
    }


}
