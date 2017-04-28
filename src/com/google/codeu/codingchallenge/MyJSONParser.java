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

import java.io.IOException;

final class MyJSONParser implements JSONParser {

    @Override
    public JSON parse(String in) throws IOException {
        // TODO: implement this
        MyJSON jsonObject = new MyJSON();

        int l = 0; // start from the one after "{"
        int r = 1;

        HashMap<String, ? extends Object> input = formMap(in, l, r);

        jsonObject.memo.putAll(input);
    }

    /**
     * split the input string in order
     * and extract the first part of the top level of the string
     *
     * @param input string to parse
     * @param l     left index of the substring
     * @param r     right index of the substring
     * @return A String that is extracted from the original string
     */
    public String findPart(String input, int l, int r) throws IOException {

        int count = 0;
        for (char c : input) {
            if (c == "{")
                count++;
            if (c == "}")
                count--;

            // count should already be 1 at the beginning cuz the first char is "{"
            // when count == 0, the index is on the top level in the string
            if (count == 0) {
                r = input.indexOf(c);
            }
        }
        return input.substring(l, r - l + 2);
    }

    /**
     * form a map structure with the given string
     *
     * @param input string to parse and put into a map
     * @param l     left index of the subpart
     * @param r     right index of the subpart
     * @return an hashmap object
     */
    public HashMap<String, HashMap> formMap(String input, int l, int r) throws IOException {
        HashMap<String, ? extends Object> ret = new HashMap<String, Object>();

        // when the index of "{" < 0, it means that no more nesting
        if (input.indexOf("{") < 0) {
            String[] record = input.split(":");
            ret.put(record[0], record[1]);
        } else {
            String str = input;

            // keep looping as long as the string is not empty
            while (l != str.size() - 1) {

                // extract the first part of the top level of the string
                String subpart = findPart(input, l, r);

                // using recursion to place multiple nesting in the hashmap
                for (String s : subpart.split(":")) {
                    ret.put(s, formMap(subpart, 1, 0));
                }

                l = r + 1; // start again from the remaining part
            }
        }
        return ret;
    }
}
