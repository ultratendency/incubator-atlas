/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.atlas.model.discovery;


import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonValue;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import static org.codehaus.jackson.annotate.JsonAutoDetect.Visibility.NONE;
import static org.codehaus.jackson.annotate.JsonAutoDetect.Visibility.PUBLIC_ONLY;

@JsonAutoDetect(getterVisibility = PUBLIC_ONLY, setterVisibility = PUBLIC_ONLY, fieldVisibility = NONE)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchParameters {
    private String  query;
    private String  typeName;
    private String  classification;
    private boolean excludeDeletedEntities;
    private int     limit;
    private int     offset;

    private FilterCriteria entityFilters;
    private FilterCriteria tagFilters;
    private Set<String>    attributes;

    /**
     * @return The type of query
     */
    public String getQuery() {
        return query;
    }

    /**
     * Set query type
     * @param query type
     */
    public void setQuery(String query) {
        this.query = query;
    }

    /**
     * @return Type name to search on
     */
    public String getTypeName() {
        return typeName;
    }

    /**
     * Set the type name to search on
     * @param typeName type name
     */
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    /**
     *
     * @return Classification/tag to search on
     */
    public String getClassification() {
        return classification;
    }

    /**
     * Set the classification/tag to search on
     * @param classification classification/tag name
     */
    public void setClassification(String classification) {
        this.classification = classification;
    }

    /**
     * @return True iff deleted entities are excluded
     */
    public boolean getExcludeDeletedEntities() {
        return excludeDeletedEntities;
    }

    /**
     * Exclude deleted entities from search
     * @param excludeDeletedEntities boolean flag
     */
    public void setExcludeDeletedEntities(boolean excludeDeletedEntities) {
        this.excludeDeletedEntities = excludeDeletedEntities;
    }

    /**
     * @return Max number of results to be returned
     */
    public int getLimit() {
        return limit;
    }

    /**
     * Restrict the results to the specified limit
     * @param limit max number of results
     */
    public void setLimit(int limit) {
        this.limit = limit;
    }

    /**
     * @return Offset(pagination) of the results
     */
    public int getOffset() {
        return offset;
    }

    /**
     * @param offset
     */
    public void setOffset(int offset) {
        this.offset = offset;
    }

    /**
     * Entity attribute filters for the type (if type name is specified)
     * @return
     */
    public FilterCriteria getEntityFilters() {
        return entityFilters;
    }

    /**
     * Filter the entities on this criteria
     * @param entityFilters
     */
    public void setEntityFilters(FilterCriteria entityFilters) {
        this.entityFilters = entityFilters;
    }

    /**
     * Tag attribute filters for the classification/tag (if tag name is specified)
     * @return
     */
    public FilterCriteria getTagFilters() {
        return tagFilters;
    }

    /**
     * Filter the tag/classification on this criteria
     * @param tagFilters
     */
    public void setTagFilters(FilterCriteria tagFilters) {
        this.tagFilters = tagFilters;
    }

    /**
     * Attribute values included in the results
     * @return
     */
    public Set<String> getAttributes() {
        return attributes;
    }

    /**
     * Return these attributes in the result response
     * @param attributes
     */
    public void setAttributes(Set<String> attributes) {
        this.attributes = attributes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SearchParameters that = (SearchParameters) o;
        return excludeDeletedEntities == that.excludeDeletedEntities &&
                limit == that.limit &&
                offset == that.offset &&
                Objects.equals(query, that.query) &&
                Objects.equals(typeName, that.typeName) &&
                Objects.equals(classification, that.classification) &&
                Objects.equals(entityFilters, that.entityFilters) &&
                Objects.equals(tagFilters, that.tagFilters) &&
                Objects.equals(attributes, that.attributes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(query, typeName, classification, excludeDeletedEntities, limit, offset, entityFilters, tagFilters, attributes);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SearchParameters{");
        sb.append("query='").append(query).append('\'');
        sb.append(", typeName='").append(typeName).append('\'');
        sb.append(", classification='").append(classification).append('\'');
        sb.append(", excludeDeletedEntities=").append(excludeDeletedEntities);
        sb.append(", limit=").append(limit);
        sb.append(", offset=").append(offset);
        sb.append(", entityFilters=").append(entityFilters);
        sb.append(", tagFilters=").append(tagFilters);
        sb.append(", attributes=").append(attributes);
        sb.append('}');
        return sb.toString();
    }


    @JsonAutoDetect(getterVisibility = PUBLIC_ONLY, setterVisibility = PUBLIC_ONLY, fieldVisibility = NONE)
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class FilterCriteria {
        // Can be presented as a group of conditions or a single condition
        public enum Condition { AND, OR }

        // Single condition
        private String   attributeName;
        private Operator operator;
        private String   attributeValue;

        // Complex conditions
        private Condition            condition;
        private List<FilterCriteria> criterion;

        public String getAttributeName() {
            return attributeName;
        }

        public void setAttributeName(String attributeName) {
            this.attributeName = attributeName;
        }

        public Operator getOperator() {
            return operator;
        }

        public void setOperator(Operator operator) {
            this.operator = operator;
        }

        public String getAttributeValue() {
            return attributeValue;
        }

        public void setAttributeValue(String attributeValue) {
            this.attributeValue = attributeValue;
        }

        public Condition getCondition() {
            return condition;
        }

        public void setCondition(Condition condition) {
            this.condition = condition;
        }

        public List<FilterCriteria> getCriterion() {
            return criterion;
        }

        public void setCriterion(List<FilterCriteria> criterion) {
            this.criterion = criterion;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            FilterCriteria that = (FilterCriteria) o;
            return Objects.equals(attributeName, that.attributeName) &&
                    Objects.equals(operator, that.operator) &&
                    Objects.equals(attributeValue, that.attributeValue) &&
                    condition == that.condition &&
                    Objects.equals(criterion, that.criterion);
        }

        @Override
        public int hashCode() {
            return Objects.hash(attributeName, operator, attributeValue, condition, criterion);
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("FilterCriteria{");
            sb.append("attributeName='").append(attributeName).append('\'');
            sb.append(", operator=").append(operator);
            sb.append(", attributeValue='").append(attributeValue).append('\'');
            sb.append(", condition=").append(condition);
            sb.append(", criterion=").append(criterion);
            sb.append('}');
            return sb.toString();
        }
    }

    /**
     * Supported search operations
     * Logical comparision operators can only be used with numbers or dates
     * IN, LIKE, startsWith, endsWith, CONTAINS can only be used with strings or text
     */
    public enum Operator {
        LT(new String[]{"<", "lt"}),
        GT(new String[]{">", "gt"}),
        LTE(new String[]{"<=", "lte"}),
        GTE(new String[]{">=", "gte"}),
        EQ(new String[]{"eq", "="}),
        NEQ(new String[]{"neq", "!="}),
        IN(new String[]{"in", "IN"}),
        LIKE(new String[]{"like", "LIKE"}),
        STARTS_WITH(new String[]{"startsWith", "STARTSWITH", "begins_with", "BEGINS_WITH"}),
        ENDS_WITH(new String[]{"endsWith", "ENDSWITH", "ends_with", "BEGINS_WITH"}),
        CONTAINS(new String[]{"contains", "CONTAINS"})
        ;
        static final Map<String, Operator> operatorsMap = new HashMap<>();

        private String[] symbols;

        static  {
            for (Operator operator : Operator.values()) {
                for (String s : operator.symbols) {
                    operatorsMap.put(s, operator);
                }
            }
        }

        Operator(String[] symbols) {
            this.symbols = symbols;
        }

        @JsonCreator
        public static Operator fromString(String symbol) {
            return operatorsMap.get(symbol);
        }

        @JsonValue
        public String getSymbol() {
            return symbols[0];
        }

        public String[] getSymbols() {
            return symbols;
        }

        @Override
        public String toString() {
            return getSymbol();
        }
    }
}
