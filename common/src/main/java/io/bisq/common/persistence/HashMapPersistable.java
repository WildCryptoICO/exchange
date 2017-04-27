/*
 * This file is part of bisq.
 *
 * bisq is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at
 * your option) any later version.
 *
 * bisq is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public
 * License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with bisq. If not, see <http://www.gnu.org/licenses/>.
 */

package io.bisq.common.persistence;

import com.google.protobuf.Message;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Delegate;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class HashMapPersistable<K, V> implements Persistable {
    @Delegate
    @Getter
    private HashMap<K, V> hashMap = new HashMap<>();
    @Setter
    private Function<HashMap<K, V>, Message> toProto;

    public HashMapPersistable(HashMap<K, V> hashMap) {
        this.hashMap = hashMap;
    }

    public HashMapPersistable(HashMap<K, V> hashMap, Function<HashMap<K, V>, Message> toProto) {
        this(hashMap);
        setToProto(toProto);
    }

    @Override
    public Message toProto() {
        if(Objects.isNull(toProto)) {
            throw new NotImplementedException();
        }
        return toProto.apply(hashMap);
    }

}