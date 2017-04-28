// Copyright 2017 Google Inc.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//    http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.codeu.codingchallenge;

import java.util.Collection;

final class MyJSON implements JSON {

    // list object that fills in the "object" part in the map - unused?
    private List<HashMap> object = new ArrayList<Hashmap>();

    // to memo all the data with a nesting structure
    // it is unknown type because it might be either hashmap or string
    private HashMap<String, ? extends Object> memo = new HashMap<String, Object>();

    /**
     * Get the value of the nested object with the given name.
     * If there is no nested object with that name, the method will return null.
     *
     * @param name name of the object
     * @return
     */
    @Override
    public JSON getObject(String name) {
        // TODO: implement this
        if (!memo.containsKey(name))
            return null; // return null if no key has name "name"

        else
            return memo.get(name); // else return the object corresponding to "name"
    }

    @Override
    public JSON setObject(String name, JSON value) {
        // TODO: implement this

        memo.put(name, value); // put the set in; will be overwritten if existed
        return this;
    }

    @Override
    public String getString(String name) {
        // TODO: implement this

        if (!memo.containsKey(name))
            return null; // return null if no key has name "name"
        else
            return memo.get(name); // else return the string corresponding to "name"
    }

    @Override
    public JSON setString(String name, String value) {
        // TODO: implement this

        memo.put(name, value); // put the set in; will be overwritten if existed
        return this;
    }

    @Override
    public void getObjects(Collection<String> names) {
        // TODO: implement this

        for (String key : memo.keySet()) {
            // add every key in the map into names if the object is a map (not string)
            if (!memo.get(key) instanceof String) {
                names.add(key);
            }
        }

    }

    @Override
    public void getStrings(Collection<String> names) {
        // TODO: implement this
        for (String key : memo.keySet()) {
            // add every key in the map into names if the object is a string
            if (memo.get(key) instanceof String) {
                names.add(key);
            }
        }

    }
}
