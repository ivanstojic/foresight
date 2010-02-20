package com.ordecon.foresight.coredb;

import com.ordecon.foresight.coredb.objects.Instance;
import com.ordecon.foresight.coredb.objects.Schema;
import com.ordecon.foresight.coredb.strategies.Strategy;

/**
 * Created by: Ivan Stojic
 */

public final class InstanceFactory {
    public static Instance getInstance(String name) {
        Instance i = new Instance(name);
        Schema s = new Schema("default");

        i.getSchemas().add(s);

        return i;
    }
}
